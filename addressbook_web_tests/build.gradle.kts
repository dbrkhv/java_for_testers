val implementation: Unit = Unit

plugins {
    id("java")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation("org.seleniumhq.selenium:selenium-java:4.29.0")

    implementation ("org.jcommander:jcommander:2.0")
    implementation ("com.fasterxml.jackson.core:jackson-databind:2.18.2")
    implementation ("com.fasterxml.jackson.dataformat:jackson-dataformat-yaml:2.14.2")
    implementation ("com.fasterxml.jackson.dataformat:jackson-dataformat-xml:2.18.1")
}

tasks.test {
    useJUnitPlatform()
    if (project.hasProperty("browser")){
        systemProperty("browser", project.property("browser")!!)
    }
    if (project.hasProperty("target")){
        systemProperty("target", project.property("target")!!)
    }
}