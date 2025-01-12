plugins {
    id("java")
}

group = "com.hyfly"
version = "1.0-SNAPSHOT"

dependencies {
    // dolphinscheduler
//    implementation("com.github.weaksloth:dolphinscheduler-sdk-java:3.2.0-RELEASE")
    implementation("org.apache.httpcomponents:httpclient:4.5.13")
    implementation("org.apache.httpcomponents:httpmime:4.5.13")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.9.10")
    implementation("com.google.guava:guava:31.1-jre")

    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}
