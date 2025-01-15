plugins {
    id("java")
    id("org.springframework.boot") version "3.4.1"
    id("io.spring.dependency-management") version "1.1.7"
}

group = "com.hyfly"
version = "1.0-SNAPSHOT"
description = "Demo project for Spring Boot"

allprojects {
    repositories {
        mavenLocal()
        maven("https://maven.aliyun.com/nexus/content/repositories/google")
        maven("https://maven.aliyun.com/nexus/content/groups/public")
        maven("https://maven.aliyun.com/nexus/content/repositories/jcenter")
        maven("https://plugins.gradle.org/m2/")
        mavenCentral()
    }
}

subprojects {
    apply {
        plugin("java")
        plugin("org.springframework.boot")
        plugin("io.spring.dependency-management")
    }

    version = "1.0-SNAPSHOT"

    dependencies {
        // spring
        implementation("org.springframework.boot:spring-boot-starter-web")
        implementation("org.springframework.boot:spring-boot-starter-tomcat")
        implementation("org.springframework.boot:spring-boot-starter-test")

        // lombok
        compileOnly("org.projectlombok:lombok")
        annotationProcessor("org.projectlombok:lombok")

        // junit
        testImplementation(platform("org.junit:junit-bom:5.10.0"))
        testImplementation("org.junit.platform:junit-platform-launcher")
        testImplementation("org.junit.jupiter:junit-jupiter")
        testImplementation("org.junit.platform:junit-platform-runner")
    }

    dependencyManagement {
        imports {
            mavenBom("org.springframework.boot:spring-boot-dependencies:3.4.1")
        }
    }

    tasks.test {
        useJUnitPlatform()
    }

}
