plugins {
    application
    jacoco
    checkstyle
    id("com.adarshr.test-logger") version "4.0.0"

}

application {
    mainClass.set("hexlet.code.App")
}

group = "hexlet.code"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation ("io.javalin:javalin:5.6.3")
    testImplementation ("org.slf4j:slf4j-simple:2.0.9")
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()

    testLogging {
        showStandardStreams = true
    }
}

tasks.jacocoTestReport {
    reports {
        xml.required = true
    }
}