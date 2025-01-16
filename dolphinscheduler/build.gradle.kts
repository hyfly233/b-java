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
    implementation("org.apache.commons:commons-collections4:4.3")
    implementation("org.apache.commons:commons-lang3:3.12.0")
    implementation("commons-beanutils:commons-beanutils:1.9.4")

    implementation("com.alibaba.fastjson2:fastjson2:2.0.54")
    implementation("com.google.guava:guava:31.1-jre")
}

tasks.test {
    useJUnitPlatform()
}
