plugins {
    id("java")
}

group = "com.hyfly"
version = "1.0-SNAPSHOT"

dependencies {
    implementation("mysql:mysql-connector-java:8.0.26")

    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}
