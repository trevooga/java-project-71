.DEFAULT_GOAL := run-dist

run-dist:
	@./build/install/app/bin/app $(file1) $(file2)

setup:
	@./gradlew wrapper --gradle-version 8.9

clean:
	@./gradlew clean

build:
	@./gradlew clean installDist

report:
	@./gradlew jacocoTestReport -o

lint:
	@./gradlew checkstyleMain

lintTest:
	@./gradlew checkstyleTest

.PHONY: build