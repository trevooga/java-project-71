plugins {
    id("java")
    application
    jacoco
    id("checkstyle")
    id("io.freefair.lombok") version "6.5.1"
}

group = "org.example"
version = "1.0-SNAPSHOT"


repositories {
    mavenCentral()
}
application {
    mainClass.set("hexlet.code.App")
}
dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation("info.picocli:picocli:4.7.6")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.17.2")
    implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-yaml:2.17.2")
    implementation("org.projectlombok:lombok:1.18.32")
    annotationProcessor("org.projectlombok:lombok:1.18.32")
}

checkstyle {
    toolVersion = "8.45.1"
    configFile = file("${rootProject.projectDir}/config/checkstyle/checkstyle.xml")
}

jacoco {
    toolVersion = "0.8.11"
    reportsDirectory = layout.buildDirectory.dir("reports/jacoco/")
}

tasks.test{
    useJUnitPlatform()
}

tasks.jacocoTestReport {
    dependsOn(tasks.test)
    reports {
        xml.required.set(true)
        html.required.set(true)
    }
}


