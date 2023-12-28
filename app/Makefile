install:
	./gradlew clean install

lint:
	./gradlew checkstyleMain --scan
	./gradlew checkstyleTest --scan

run-dist:
	@./build/install/app/bin/app

run:
	./gradlew run

test:
	./gradlew test

report:
	./gradlew jacocoTestReport

build:
	./gradlew clean build

.PHONY: build