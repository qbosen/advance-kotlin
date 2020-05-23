plugins {
    java
    kotlin("jvm") version "1.3.72"
}

group = "top.abosen"
version = "1.0-SNAPSHOT"

repositories {
//    maven { url("http://maven.aliyun.com/nexus/content/groups/public") }
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    testCompile("junit", "junit", "4.12")
}

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_1_8
}
tasks {
    compileKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
    compileTestKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
}