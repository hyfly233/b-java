plugins {
    id("java")
}

group = "com.hyfly"
version = "1.0-SNAPSHOT"

dependencies {
    // dolphinscheduler
    implementation("org.apache.httpcomponents:httpclient:4.5.13") {
        exclude(group = "commons-logging", module = "commons-logging")
    }
    implementation("org.apache.httpcomponents:httpmime:4.5.13") {
        exclude(group = "commons-logging", module = "commons-logging")
    }
    implementation("com.alibaba.fastjson2:fastjson2:2.0.54")
    implementation("com.google.guava:guava:31.1-jre")

    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}
