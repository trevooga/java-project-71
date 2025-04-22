#!/bin/bash

if [ "$EUID" -ne 0 ]; then
  echo "Запустите скрипт с правами root"
  exit 1
fi

count=0

lsof | grep deleted | awk '$5 == "REG" && $4 ~ /^[0-9]+[a-z]*$/ {print $2, $4}' | while read -r pid fd; do
  fdnum=$(echo "$fd" | sed 's/[a-z]*$//')
  file="/proc/$pid/fd/$fdnum"
  
  if [ -e "$file" ] && [ ! -d "$file" ]; then
    echo "[$((++count))] Обнуляем файл: PID=$pid FD=$fdnum"
    if cat /dev/null > "$file"; then
      echo "    Успешно"
    else
      echo "    Ошибка при обнулении"
    fi
  fi
done

if [ $count -eq 0 ]; then
  echo "Удалённых открытых файлов не найдено."
else
  echo "Всего обработано файлов: $count"
fi
