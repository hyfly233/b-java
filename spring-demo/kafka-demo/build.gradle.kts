plugins {
    id("java")
}

group = "com.hyfly"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    // kafka
    implementation("org.springframework.kafka:spring-kafka")
    implementation("org.apache.kafka:kafka-streams")

    // logging
    implementation("org.springframework.boot:spring-boot-starter-logging")

    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}
