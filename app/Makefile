.DEFAULT_GOAL := run-dist

run-dist:
	@./build/install/app/bin/app $(file1) $(file2)

clean:
	@./gradlew clean

build:
	@./gradlew clean installDist

report:
	@./gradlew jacocoTestReport

lint:
	@./gradlew checkstyleMain

lintTest:
	@./gradlew checkstyleTest

.PHONY: build