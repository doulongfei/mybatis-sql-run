plugins {
    id("java")
    id("org.jetbrains.kotlin.jvm") version "1.9.22"
    id("org.jetbrains.intellij") version "1.17.2"
}

group = "org.dou"
version = "1.0"

repositories {
    google()
    maven("https://mirrors.cloud.tencent.com/nexus/repository/maven-public/")
    mavenCentral()
}


dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.9.2")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.9.2")
    implementation("com.alibaba:fastjson:2.0.25")
    implementation(dependencyNotation = "mysql:mysql-connector-java:8.0.30")
    implementation(dependencyNotation = "ognl:ognl:3.0.4")
    implementation("com.alibaba:druid:1.2.15")
    implementation("com.fifesoft:rsyntaxtextarea:3.1.1")
    implementation("com.github.vertical-blank:sql-formatter:2.0.3")
    implementation(dependencyNotation = "net.datafaker:datafaker:1.8.1")
    implementation("com.github.mifmif:generex:1.0.1")
    implementation("com.github.jsqlparser:jsqlparser:4.5")
    implementation(dependencyNotation = "ch.qos.logback:logback-classic:1.3.0")
    implementation("jfree:jfreechart:1.0.13")
}

// Configure Gradle IntelliJ Plugin
// Read more: https://plugins.jetbrains.com/docs/intellij/tools-gradle-intellij-plugin.html
intellij {
//    version.set("2023.2.5")
//    type.set("IC") // Target IDE Platform

    // 使用本地idea
    localPath.set("D:\\devtools\\JetBrains\\IntelliJ IDEA 2024.1")
    plugins.set(listOf("com.intellij.database", "com.intellij.spring"))
}

tasks {
    // Set the JVM compatibility versions
    withType<JavaCompile> {
        options.encoding = "UTF-8"
        sourceCompatibility = "17"
        targetCompatibility = "17"
    }
    withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions.jvmTarget = "17"
    }

     // 添加以下内容，解决运行时控制台中文乱码
    withType<JavaExec> {
        jvmArgs = listOf("-Dfile.encoding=UTF-8", "-Dsun.stdout.encoding=UTF-8", "-Dsun.stderr.encoding=UTF-8")
    }

    patchPluginXml {
        sinceBuild.set("232")
        untilBuild.set("242.*")
    }

    signPlugin {
        certificateChain.set(System.getenv("CERTIFICATE_CHAIN"))
        privateKey.set(System.getenv("PRIVATE_KEY"))
        password.set(System.getenv("PRIVATE_KEY_PASSWORD"))
    }

    publishPlugin {
        token.set(System.getenv("PUBLISH_TOKEN"))
    }
}
