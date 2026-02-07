# Dependencies Snapshot

Source: `./gradlew dependencies --no-daemon`
Project: `Front_project_FULL`
Generated: output captured from Gradle 9.0.0 wrapper

## Raw Dependency Graph (exact Gradle output)

```text
To honour the JVM settings for this build a single-use Daemon process will be forked. For more on this, please refer to https://docs.gradle.org/9.0.0/userguide/gradle_daemon.html#sec:disabling_the_daemon in the Gradle documentation.
Daemon will be stopped at the end of the build 

> Task :dependencies

------------------------------------------------------------
Root project 'Front_project_FULL'
------------------------------------------------------------

annotationProcessor - Annotation processors and their dependencies for source set 'main'.
\--- org.projectlombok:lombok -> 1.18.36

bootArchives - Configuration for Spring Boot archive artifacts. (n)
No dependencies

compileClasspath - Compile classpath for source set 'main'.
+--- org.projectlombok:lombok -> 1.18.36
+--- org.springframework.boot:spring-boot-starter-web -> 3.4.3
|    +--- org.springframework.boot:spring-boot-starter:3.4.3
|    |    +--- org.springframework.boot:spring-boot:3.4.3
|    |    |    +--- org.springframework:spring-core:6.2.3
|    |    |    |    \--- org.springframework:spring-jcl:6.2.3
|    |    |    \--- org.springframework:spring-context:6.2.3
|    |    |         +--- org.springframework:spring-aop:6.2.3
|    |    |         |    +--- org.springframework:spring-beans:6.2.3
|    |    |         |    |    \--- org.springframework:spring-core:6.2.3 (*)
|    |    |         |    \--- org.springframework:spring-core:6.2.3 (*)
|    |    |         +--- org.springframework:spring-beans:6.2.3 (*)
|    |    |         +--- org.springframework:spring-core:6.2.3 (*)
|    |    |         +--- org.springframework:spring-expression:6.2.3
|    |    |         |    \--- org.springframework:spring-core:6.2.3 (*)
|    |    |         \--- io.micrometer:micrometer-observation:1.14.4
|    |    |              \--- io.micrometer:micrometer-commons:1.14.4
|    |    +--- org.springframework.boot:spring-boot-autoconfigure:3.4.3
|    |    |    \--- org.springframework.boot:spring-boot:3.4.3 (*)
|    |    +--- org.springframework.boot:spring-boot-starter-logging:3.4.3
|    |    |    +--- ch.qos.logback:logback-classic:1.5.16
|    |    |    |    +--- ch.qos.logback:logback-core:1.5.16
|    |    |    |    \--- org.slf4j:slf4j-api:2.0.16
|    |    |    +--- org.apache.logging.log4j:log4j-to-slf4j:2.24.3
|    |    |    |    +--- org.apache.logging.log4j:log4j-api:2.24.3
|    |    |    |    \--- org.slf4j:slf4j-api:2.0.16
|    |    |    \--- org.slf4j:jul-to-slf4j:2.0.16
|    |    |         \--- org.slf4j:slf4j-api:2.0.16
|    |    +--- jakarta.annotation:jakarta.annotation-api:2.1.1
|    |    +--- org.springframework:spring-core:6.2.3 (*)
|    |    \--- org.yaml:snakeyaml:2.3
|    +--- org.springframework.boot:spring-boot-starter-json:3.4.3
|    |    +--- org.springframework.boot:spring-boot-starter:3.4.3 (*)
|    |    +--- org.springframework:spring-web:6.2.3
|    |    |    +--- org.springframework:spring-beans:6.2.3 (*)
|    |    |    +--- org.springframework:spring-core:6.2.3 (*)
|    |    |    \--- io.micrometer:micrometer-observation:1.14.4 (*)
|    |    +--- com.fasterxml.jackson.core:jackson-databind:2.18.2
|    |    |    +--- com.fasterxml.jackson.core:jackson-annotations:2.18.2
|    |    |    |    \--- com.fasterxml.jackson:jackson-bom:2.18.2
|    |    |    |         +--- com.fasterxml.jackson.core:jackson-annotations:2.18.2 (c)
|    |    |    |         +--- com.fasterxml.jackson.core:jackson-core:2.18.2 (c)
|    |    |    |         +--- com.fasterxml.jackson.core:jackson-databind:2.18.2 (c)
|    |    |    |         +--- com.fasterxml.jackson.datatype:jackson-datatype-jdk8:2.18.2 (c)
|    |    |    |         +--- com.fasterxml.jackson.datatype:jackson-datatype-jsr310:2.18.2 (c)
|    |    |    |         \--- com.fasterxml.jackson.module:jackson-module-parameter-names:2.18.2 (c)
|    |    |    +--- com.fasterxml.jackson.core:jackson-core:2.18.2
|    |    |    |    \--- com.fasterxml.jackson:jackson-bom:2.18.2 (*)
|    |    |    \--- com.fasterxml.jackson:jackson-bom:2.18.2 (*)
|    |    +--- com.fasterxml.jackson.datatype:jackson-datatype-jdk8:2.18.2
|    |    |    +--- com.fasterxml.jackson.core:jackson-core:2.18.2 (*)
|    |    |    +--- com.fasterxml.jackson.core:jackson-databind:2.18.2 (*)
|    |    |    \--- com.fasterxml.jackson:jackson-bom:2.18.2 (*)
|    |    +--- com.fasterxml.jackson.datatype:jackson-datatype-jsr310:2.18.2
|    |    |    +--- com.fasterxml.jackson.core:jackson-annotations:2.18.2 (*)
|    |    |    +--- com.fasterxml.jackson.core:jackson-core:2.18.2 (*)
|    |    |    +--- com.fasterxml.jackson.core:jackson-databind:2.18.2 (*)
|    |    |    \--- com.fasterxml.jackson:jackson-bom:2.18.2 (*)
|    |    \--- com.fasterxml.jackson.module:jackson-module-parameter-names:2.18.2
|    |         +--- com.fasterxml.jackson.core:jackson-core:2.18.2 (*)
|    |         +--- com.fasterxml.jackson.core:jackson-databind:2.18.2 (*)
|    |         \--- com.fasterxml.jackson:jackson-bom:2.18.2 (*)
|    +--- org.springframework.boot:spring-boot-starter-tomcat:3.4.3
|    |    +--- jakarta.annotation:jakarta.annotation-api:2.1.1
|    |    +--- org.apache.tomcat.embed:tomcat-embed-core:10.1.36
|    |    +--- org.apache.tomcat.embed:tomcat-embed-el:10.1.36
|    |    \--- org.apache.tomcat.embed:tomcat-embed-websocket:10.1.36
|    |         \--- org.apache.tomcat.embed:tomcat-embed-core:10.1.36
|    +--- org.springframework:spring-web:6.2.3 (*)
|    \--- org.springframework:spring-webmvc:6.2.3
|         +--- org.springframework:spring-aop:6.2.3 (*)
|         +--- org.springframework:spring-beans:6.2.3 (*)
|         +--- org.springframework:spring-context:6.2.3 (*)
|         +--- org.springframework:spring-core:6.2.3 (*)
|         +--- org.springframework:spring-expression:6.2.3 (*)
|         \--- org.springframework:spring-web:6.2.3 (*)
+--- org.springframework.boot:spring-boot-starter-validation -> 3.4.3
|    +--- org.springframework.boot:spring-boot-starter:3.4.3 (*)
|    +--- org.apache.tomcat.embed:tomcat-embed-el:10.1.36
|    \--- org.hibernate.validator:hibernate-validator:8.0.2.Final
|         +--- jakarta.validation:jakarta.validation-api:3.0.2
|         +--- org.jboss.logging:jboss-logging:3.4.3.Final -> 3.6.1.Final
|         \--- com.fasterxml:classmate:1.5.1 -> 1.7.0
+--- org.springframework.boot:spring-boot-starter-thymeleaf -> 3.4.3
|    +--- org.springframework.boot:spring-boot-starter:3.4.3 (*)
|    \--- org.thymeleaf:thymeleaf-spring6:3.1.3.RELEASE
|         +--- org.thymeleaf:thymeleaf:3.1.3.RELEASE
|         |    +--- org.attoparser:attoparser:2.0.7.RELEASE
|         |    +--- org.unbescape:unbescape:1.1.6.RELEASE
|         |    \--- org.slf4j:slf4j-api:2.0.16
|         \--- org.slf4j:slf4j-api:2.0.16
+--- org.springframework.boot:spring-boot-starter-security -> 3.4.3
|    +--- org.springframework.boot:spring-boot-starter:3.4.3 (*)
|    +--- org.springframework:spring-aop:6.2.3 (*)
|    +--- org.springframework.security:spring-security-config:6.4.3
|    |    +--- org.springframework.security:spring-security-core:6.4.3
|    |    |    +--- org.springframework.security:spring-security-crypto:6.4.3
|    |    |    +--- org.springframework:spring-aop:6.2.3 (*)
|    |    |    +--- org.springframework:spring-beans:6.2.3 (*)
|    |    |    +--- org.springframework:spring-context:6.2.3 (*)
|    |    |    +--- org.springframework:spring-core:6.2.3 (*)
|    |    |    +--- org.springframework:spring-expression:6.2.3 (*)
|    |    |    \--- io.micrometer:micrometer-observation:1.14.4 (*)
|    |    +--- org.springframework:spring-aop:6.2.3 (*)
|    |    +--- org.springframework:spring-beans:6.2.3 (*)
|    |    +--- org.springframework:spring-context:6.2.3 (*)
|    |    \--- org.springframework:spring-core:6.2.3 (*)
|    \--- org.springframework.security:spring-security-web:6.4.3
|         +--- org.springframework.security:spring-security-core:6.4.3 (*)
|         +--- org.springframework:spring-core:6.2.3 (*)
|         +--- org.springframework:spring-aop:6.2.3 (*)
|         +--- org.springframework:spring-beans:6.2.3 (*)
|         +--- org.springframework:spring-context:6.2.3 (*)
|         +--- org.springframework:spring-expression:6.2.3 (*)
|         \--- org.springframework:spring-web:6.2.3 (*)
\--- org.springframework.data:spring-data-commons -> 3.4.3
     +--- org.springframework:spring-core:6.2.3 (*)
     +--- org.springframework:spring-beans:6.2.3 (*)
     \--- org.slf4j:slf4j-api:2.0.2 -> 2.0.16

compileOnly - Compile-only dependencies for the 'main' feature. (n)
\--- org.projectlombok:lombok (n)

default - Configuration for default artifacts. (n)
No dependencies

developmentOnly - Configuration for development-only dependencies such as Spring Boot's DevTools.
No dependencies

implementation - Implementation dependencies for the 'main' feature. (n)
+--- org.springframework.boot:spring-boot-starter-web (n)
+--- org.springframework.boot:spring-boot-starter-validation (n)
+--- org.springframework.boot:spring-boot-starter-thymeleaf (n)
+--- org.springframework.boot:spring-boot-starter-security (n)
\--- org.springframework.data:spring-data-commons (n)

mainSourceElements - List of source directories contained in the Main SourceSet. (n)
No dependencies

productionRuntimeClasspath
+--- org.springframework.boot:spring-boot-starter-web -> 3.4.3
|    +--- org.springframework.boot:spring-boot-starter:3.4.3
|    |    +--- org.springframework.boot:spring-boot:3.4.3
|    |    |    +--- org.springframework:spring-core:6.2.3
|    |    |    |    \--- org.springframework:spring-jcl:6.2.3
|    |    |    \--- org.springframework:spring-context:6.2.3
|    |    |         +--- org.springframework:spring-aop:6.2.3
|    |    |         |    +--- org.springframework:spring-beans:6.2.3
|    |    |         |    |    \--- org.springframework:spring-core:6.2.3 (*)
|    |    |         |    \--- org.springframework:spring-core:6.2.3 (*)
|    |    |         +--- org.springframework:spring-beans:6.2.3 (*)
|    |    |         +--- org.springframework:spring-core:6.2.3 (*)
|    |    |         +--- org.springframework:spring-expression:6.2.3
|    |    |         |    \--- org.springframework:spring-core:6.2.3 (*)
|    |    |         \--- io.micrometer:micrometer-observation:1.14.4
|    |    |              \--- io.micrometer:micrometer-commons:1.14.4
|    |    +--- org.springframework.boot:spring-boot-autoconfigure:3.4.3
|    |    |    \--- org.springframework.boot:spring-boot:3.4.3 (*)
|    |    +--- org.springframework.boot:spring-boot-starter-logging:3.4.3
|    |    |    +--- ch.qos.logback:logback-classic:1.5.16
|    |    |    |    +--- ch.qos.logback:logback-core:1.5.16
|    |    |    |    \--- org.slf4j:slf4j-api:2.0.16
|    |    |    +--- org.apache.logging.log4j:log4j-to-slf4j:2.24.3
|    |    |    |    +--- org.apache.logging.log4j:log4j-api:2.24.3
|    |    |    |    \--- org.slf4j:slf4j-api:2.0.16
|    |    |    \--- org.slf4j:jul-to-slf4j:2.0.16
|    |    |         \--- org.slf4j:slf4j-api:2.0.16
|    |    +--- jakarta.annotation:jakarta.annotation-api:2.1.1
|    |    +--- org.springframework:spring-core:6.2.3 (*)
|    |    \--- org.yaml:snakeyaml:2.3
|    +--- org.springframework.boot:spring-boot-starter-json:3.4.3
|    |    +--- org.springframework.boot:spring-boot-starter:3.4.3 (*)
|    |    +--- org.springframework:spring-web:6.2.3
|    |    |    +--- org.springframework:spring-beans:6.2.3 (*)
|    |    |    +--- org.springframework:spring-core:6.2.3 (*)
|    |    |    \--- io.micrometer:micrometer-observation:1.14.4 (*)
|    |    +--- com.fasterxml.jackson.core:jackson-databind:2.18.2
|    |    |    +--- com.fasterxml.jackson.core:jackson-annotations:2.18.2
|    |    |    |    \--- com.fasterxml.jackson:jackson-bom:2.18.2
|    |    |    |         +--- com.fasterxml.jackson.core:jackson-annotations:2.18.2 (c)
|    |    |    |         +--- com.fasterxml.jackson.core:jackson-core:2.18.2 (c)
|    |    |    |         +--- com.fasterxml.jackson.core:jackson-databind:2.18.2 (c)
|    |    |    |         +--- com.fasterxml.jackson.datatype:jackson-datatype-jdk8:2.18.2 (c)
|    |    |    |         +--- com.fasterxml.jackson.datatype:jackson-datatype-jsr310:2.18.2 (c)
|    |    |    |         \--- com.fasterxml.jackson.module:jackson-module-parameter-names:2.18.2 (c)
|    |    |    +--- com.fasterxml.jackson.core:jackson-core:2.18.2
|    |    |    |    \--- com.fasterxml.jackson:jackson-bom:2.18.2 (*)
|    |    |    \--- com.fasterxml.jackson:jackson-bom:2.18.2 (*)
|    |    +--- com.fasterxml.jackson.datatype:jackson-datatype-jdk8:2.18.2
|    |    |    +--- com.fasterxml.jackson.core:jackson-core:2.18.2 (*)
|    |    |    +--- com.fasterxml.jackson.core:jackson-databind:2.18.2 (*)
|    |    |    \--- com.fasterxml.jackson:jackson-bom:2.18.2 (*)
|    |    +--- com.fasterxml.jackson.datatype:jackson-datatype-jsr310:2.18.2
|    |    |    +--- com.fasterxml.jackson.core:jackson-annotations:2.18.2 (*)
|    |    |    +--- com.fasterxml.jackson.core:jackson-core:2.18.2 (*)
|    |    |    +--- com.fasterxml.jackson.core:jackson-databind:2.18.2 (*)
|    |    |    \--- com.fasterxml.jackson:jackson-bom:2.18.2 (*)
|    |    \--- com.fasterxml.jackson.module:jackson-module-parameter-names:2.18.2
|    |         +--- com.fasterxml.jackson.core:jackson-core:2.18.2 (*)
|    |         +--- com.fasterxml.jackson.core:jackson-databind:2.18.2 (*)
|    |         \--- com.fasterxml.jackson:jackson-bom:2.18.2 (*)
|    +--- org.springframework.boot:spring-boot-starter-tomcat:3.4.3
|    |    +--- jakarta.annotation:jakarta.annotation-api:2.1.1
|    |    +--- org.apache.tomcat.embed:tomcat-embed-core:10.1.36
|    |    +--- org.apache.tomcat.embed:tomcat-embed-el:10.1.36
|    |    \--- org.apache.tomcat.embed:tomcat-embed-websocket:10.1.36
|    |         \--- org.apache.tomcat.embed:tomcat-embed-core:10.1.36
|    +--- org.springframework:spring-web:6.2.3 (*)
|    \--- org.springframework:spring-webmvc:6.2.3
|         +--- org.springframework:spring-aop:6.2.3 (*)
|         +--- org.springframework:spring-beans:6.2.3 (*)
|         +--- org.springframework:spring-context:6.2.3 (*)
|         +--- org.springframework:spring-core:6.2.3 (*)
|         +--- org.springframework:spring-expression:6.2.3 (*)
|         \--- org.springframework:spring-web:6.2.3 (*)
+--- org.springframework.boot:spring-boot-starter-validation -> 3.4.3
|    +--- org.springframework.boot:spring-boot-starter:3.4.3 (*)
|    +--- org.apache.tomcat.embed:tomcat-embed-el:10.1.36
|    \--- org.hibernate.validator:hibernate-validator:8.0.2.Final
|         +--- jakarta.validation:jakarta.validation-api:3.0.2
|         +--- org.jboss.logging:jboss-logging:3.4.3.Final -> 3.6.1.Final
|         \--- com.fasterxml:classmate:1.5.1 -> 1.7.0
+--- org.springframework.boot:spring-boot-starter-thymeleaf -> 3.4.3
|    +--- org.springframework.boot:spring-boot-starter:3.4.3 (*)
|    \--- org.thymeleaf:thymeleaf-spring6:3.1.3.RELEASE
|         +--- org.thymeleaf:thymeleaf:3.1.3.RELEASE
|         |    +--- org.attoparser:attoparser:2.0.7.RELEASE
|         |    +--- org.unbescape:unbescape:1.1.6.RELEASE
|         |    \--- org.slf4j:slf4j-api:2.0.16
|         \--- org.slf4j:slf4j-api:2.0.16
+--- org.springframework.boot:spring-boot-starter-security -> 3.4.3
|    +--- org.springframework.boot:spring-boot-starter:3.4.3 (*)
|    +--- org.springframework:spring-aop:6.2.3 (*)
|    +--- org.springframework.security:spring-security-config:6.4.3
|    |    +--- org.springframework.security:spring-security-core:6.4.3
|    |    |    +--- org.springframework.security:spring-security-crypto:6.4.3
|    |    |    +--- org.springframework:spring-aop:6.2.3 (*)
|    |    |    +--- org.springframework:spring-beans:6.2.3 (*)
|    |    |    +--- org.springframework:spring-context:6.2.3 (*)
|    |    |    +--- org.springframework:spring-core:6.2.3 (*)
|    |    |    +--- org.springframework:spring-expression:6.2.3 (*)
|    |    |    \--- io.micrometer:micrometer-observation:1.14.4 (*)
|    |    +--- org.springframework:spring-aop:6.2.3 (*)
|    |    +--- org.springframework:spring-beans:6.2.3 (*)
|    |    +--- org.springframework:spring-context:6.2.3 (*)
|    |    \--- org.springframework:spring-core:6.2.3 (*)
|    \--- org.springframework.security:spring-security-web:6.4.3
|         +--- org.springframework.security:spring-security-core:6.4.3 (*)
|         +--- org.springframework:spring-core:6.2.3 (*)
|         +--- org.springframework:spring-aop:6.2.3 (*)
|         +--- org.springframework:spring-beans:6.2.3 (*)
|         +--- org.springframework:spring-context:6.2.3 (*)
|         +--- org.springframework:spring-expression:6.2.3 (*)
|         \--- org.springframework:spring-web:6.2.3 (*)
+--- org.springframework.data:spring-data-commons -> 3.4.3
|    +--- org.springframework:spring-core:6.2.3 (*)
|    +--- org.springframework:spring-beans:6.2.3 (*)
|    \--- org.slf4j:slf4j-api:2.0.2 -> 2.0.16
\--- org.springframework.boot:spring-boot-starter-test -> 3.4.3
     +--- org.springframework.boot:spring-boot-starter:3.4.3 (*)
     +--- org.springframework.boot:spring-boot-test:3.4.3
     |    +--- org.springframework.boot:spring-boot:3.4.3 (*)
     |    \--- org.springframework:spring-test:6.2.3
     |         \--- org.springframework:spring-core:6.2.3 (*)
     +--- org.springframework.boot:spring-boot-test-autoconfigure:3.4.3
     |    +--- org.springframework.boot:spring-boot:3.4.3 (*)
     |    +--- org.springframework.boot:spring-boot-test:3.4.3 (*)
     |    \--- org.springframework.boot:spring-boot-autoconfigure:3.4.3 (*)
     +--- com.jayway.jsonpath:json-path:2.9.0
     |    +--- net.minidev:json-smart:2.5.0 -> 2.5.2
     |    |    \--- net.minidev:accessors-smart:2.5.2
     |    |         \--- org.ow2.asm:asm:9.7.1
     |    \--- org.slf4j:slf4j-api:2.0.11 -> 2.0.16
     +--- jakarta.xml.bind:jakarta.xml.bind-api:4.0.2
     |    \--- jakarta.activation:jakarta.activation-api:2.1.3
     +--- net.minidev:json-smart:2.5.2 (*)
     +--- org.assertj:assertj-core:3.26.3
     |    \--- net.bytebuddy:byte-buddy:1.14.18 -> 1.15.11
     +--- org.awaitility:awaitility:4.2.2
     |    \--- org.hamcrest:hamcrest:2.1 -> 2.2
     +--- org.hamcrest:hamcrest:2.2
     +--- org.junit.jupiter:junit-jupiter:5.11.4
     |    +--- org.junit:junit-bom:5.11.4
     |    |    +--- org.junit.jupiter:junit-jupiter:5.11.4 (c)
     |    |    +--- org.junit.jupiter:junit-jupiter-api:5.11.4 (c)
     |    |    +--- org.junit.jupiter:junit-jupiter-engine:5.11.4 (c)
     |    |    +--- org.junit.jupiter:junit-jupiter-params:5.11.4 (c)
     |    |    +--- org.junit.platform:junit-platform-engine:1.11.4 (c)
     |    |    +--- org.junit.platform:junit-platform-launcher:1.11.4 (c)
     |    |    \--- org.junit.platform:junit-platform-commons:1.11.4 (c)
     |    +--- org.junit.jupiter:junit-jupiter-api:5.11.4
     |    |    +--- org.junit:junit-bom:5.11.4 (*)
     |    |    +--- org.opentest4j:opentest4j:1.3.0
     |    |    \--- org.junit.platform:junit-platform-commons:1.11.4
     |    |         \--- org.junit:junit-bom:5.11.4 (*)
     |    +--- org.junit.jupiter:junit-jupiter-params:5.11.4
     |    |    +--- org.junit:junit-bom:5.11.4 (*)
     |    |    \--- org.junit.jupiter:junit-jupiter-api:5.11.4 (*)
     |    \--- org.junit.jupiter:junit-jupiter-engine:5.11.4
     |         +--- org.junit:junit-bom:5.11.4 (*)
     |         +--- org.junit.platform:junit-platform-engine:1.11.4
     |         |    +--- org.junit:junit-bom:5.11.4 (*)
     |         |    +--- org.opentest4j:opentest4j:1.3.0
     |         |    \--- org.junit.platform:junit-platform-commons:1.11.4 (*)
     |         \--- org.junit.jupiter:junit-jupiter-api:5.11.4 (*)
     +--- org.mockito:mockito-core:5.14.2
     |    +--- net.bytebuddy:byte-buddy:1.15.4 -> 1.15.11
     |    +--- net.bytebuddy:byte-buddy-agent:1.15.4 -> 1.15.11
     |    \--- org.objenesis:objenesis:3.3
     +--- org.mockito:mockito-junit-jupiter:5.14.2
     |    +--- org.mockito:mockito-core:5.14.2 (*)
     |    \--- org.junit.jupiter:junit-jupiter-api:5.11.2 -> 5.11.4 (*)
     +--- org.skyscreamer:jsonassert:1.5.3
     |    \--- com.vaadin.external.google:android-json:0.0.20131108.vaadin1
     +--- org.springframework:spring-core:6.2.3 (*)
     +--- org.springframework:spring-test:6.2.3 (*)
     +--- org.xmlunit:xmlunit-core:2.10.0
     \--- org.junit.platform:junit-platform-launcher -> 1.11.4
          +--- org.junit:junit-bom:5.11.4 (*)
          \--- org.junit.platform:junit-platform-engine:1.11.4 (*)

testCompileOnly - Compile only dependencies for source set 'test'. (n)
No dependencies

testImplementation - Implementation only dependencies for source set 'test'. (n)
\--- org.springframework.boot:spring-boot-starter-test (n)

testRuntimeClasspath - Runtime classpath of source set 'test'.
+--- org.springframework.boot:spring-boot-starter-web -> 3.4.3
|    +--- org.springframework.boot:spring-boot-starter:3.4.3
|    |    +--- org.springframework.boot:spring-boot:3.4.3
|    |    |    +--- org.springframework:spring-core:6.2.3
|    |    |    |    \--- org.springframework:spring-jcl:6.2.3
|    |    |    \--- org.springframework:spring-context:6.2.3
|    |    |         +--- org.springframework:spring-aop:6.2.3
|    |    |         |    +--- org.springframework:spring-beans:6.2.3
|    |    |         |    |    \--- org.springframework:spring-core:6.2.3 (*)
|    |    |         |    \--- org.springframework:spring-core:6.2.3 (*)
|    |    |         +--- org.springframework:spring-beans:6.2.3 (*)
|    |    |         +--- org.springframework:spring-core:6.2.3 (*)
|    |    |         +--- org.springframework:spring-expression:6.2.3
|    |    |         |    \--- org.springframework:spring-core:6.2.3 (*)
|    |    |         \--- io.micrometer:micrometer-observation:1.14.4
|    |    |              \--- io.micrometer:micrometer-commons:1.14.4
|    |    +--- org.springframework.boot:spring-boot-autoconfigure:3.4.3
|    |    |    \--- org.springframework.boot:spring-boot:3.4.3 (*)
|    |    +--- org.springframework.boot:spring-boot-starter-logging:3.4.3
|    |    |    +--- ch.qos.logback:logback-classic:1.5.16
|    |    |    |    +--- ch.qos.logback:logback-core:1.5.16
|    |    |    |    \--- org.slf4j:slf4j-api:2.0.16
|    |    |    +--- org.apache.logging.log4j:log4j-to-slf4j:2.24.3
|    |    |    |    +--- org.apache.logging.log4j:log4j-api:2.24.3
|    |    |    |    \--- org.slf4j:slf4j-api:2.0.16
|    |    |    \--- org.slf4j:jul-to-slf4j:2.0.16
|    |    |         \--- org.slf4j:slf4j-api:2.0.16
|    |    +--- jakarta.annotation:jakarta.annotation-api:2.1.1
|    |    +--- org.springframework:spring-core:6.2.3 (*)
|    |    \--- org.yaml:snakeyaml:2.3
|    +--- org.springframework.boot:spring-boot-starter-json:3.4.3
|    |    +--- org.springframework.boot:spring-boot-starter:3.4.3 (*)
|    |    +--- org.springframework:spring-web:6.2.3
|    |    |    +--- org.springframework:spring-beans:6.2.3 (*)
|    |    |    +--- org.springframework:spring-core:6.2.3 (*)
|    |    |    \--- io.micrometer:micrometer-observation:1.14.4 (*)
|    |    +--- com.fasterxml.jackson.core:jackson-databind:2.18.2
|    |    |    +--- com.fasterxml.jackson.core:jackson-annotations:2.18.2
|    |    |    |    \--- com.fasterxml.jackson:jackson-bom:2.18.2
|    |    |    |         +--- com.fasterxml.jackson.core:jackson-annotations:2.18.2 (c)
|    |    |    |         +--- com.fasterxml.jackson.core:jackson-core:2.18.2 (c)
|    |    |    |         +--- com.fasterxml.jackson.core:jackson-databind:2.18.2 (c)
|    |    |    |         +--- com.fasterxml.jackson.datatype:jackson-datatype-jdk8:2.18.2 (c)
|    |    |    |         +--- com.fasterxml.jackson.datatype:jackson-datatype-jsr310:2.18.2 (c)
|    |    |    |         \--- com.fasterxml.jackson.module:jackson-module-parameter-names:2.18.2 (c)
|    |    |    +--- com.fasterxml.jackson.core:jackson-core:2.18.2
|    |    |    |    \--- com.fasterxml.jackson:jackson-bom:2.18.2 (*)
|    |    |    \--- com.fasterxml.jackson:jackson-bom:2.18.2 (*)
|    |    +--- com.fasterxml.jackson.datatype:jackson-datatype-jdk8:2.18.2
|    |    |    +--- com.fasterxml.jackson.core:jackson-core:2.18.2 (*)
|    |    |    +--- com.fasterxml.jackson.core:jackson-databind:2.18.2 (*)
|    |    |    \--- com.fasterxml.jackson:jackson-bom:2.18.2 (*)
|    |    +--- com.fasterxml.jackson.datatype:jackson-datatype-jsr310:2.18.2
|    |    |    +--- com.fasterxml.jackson.core:jackson-annotations:2.18.2 (*)
|    |    |    +--- com.fasterxml.jackson.core:jackson-core:2.18.2 (*)
|    |    |    +--- com.fasterxml.jackson.core:jackson-databind:2.18.2 (*)
|    |    |    \--- com.fasterxml.jackson:jackson-bom:2.18.2 (*)
|    |    \--- com.fasterxml.jackson.module:jackson-module-parameter-names:2.18.2
|    |         +--- com.fasterxml.jackson.core:jackson-core:2.18.2 (*)
|    |         +--- com.fasterxml.jackson.core:jackson-databind:2.18.2 (*)
|    |         \--- com.fasterxml.jackson:jackson-bom:2.18.2 (*)
|    +--- org.springframework.boot:spring-boot-starter-tomcat:3.4.3
|    |    +--- jakarta.annotation:jakarta.annotation-api:2.1.1
|    |    +--- org.apache.tomcat.embed:tomcat-embed-core:10.1.36
|    |    +--- org.apache.tomcat.embed:tomcat-embed-el:10.1.36
|    |    \--- org.apache.tomcat.embed:tomcat-embed-websocket:10.1.36
|    |         \--- org.apache.tomcat.embed:tomcat-embed-core:10.1.36
|    +--- org.springframework:spring-web:6.2.3 (*)
|    \--- org.springframework:spring-webmvc:6.2.3
|         +--- org.springframework:spring-aop:6.2.3 (*)
|         +--- org.springframework:spring-beans:6.2.3 (*)
|         +--- org.springframework:spring-context:6.2.3 (*)
|         +--- org.springframework:spring-core:6.2.3 (*)
|         +--- org.springframework:spring-expression:6.2.3 (*)
|         \--- org.springframework:spring-web:6.2.3 (*)
+--- org.springframework.boot:spring-boot-starter-validation -> 3.4.3
|    +--- org.springframework.boot:spring-boot-starter:3.4.3 (*)
|    +--- org.apache.tomcat.embed:tomcat-embed-el:10.1.36
|    \--- org.hibernate.validator:hibernate-validator:8.0.2.Final
|         +--- jakarta.validation:jakarta.validation-api:3.0.2
|         +--- org.jboss.logging:jboss-logging:3.4.3.Final -> 3.6.1.Final
|         \--- com.fasterxml:classmate:1.5.1 -> 1.7.0
+--- org.springframework.boot:spring-boot-starter-thymeleaf -> 3.4.3
|    +--- org.springframework.boot:spring-boot-starter:3.4.3 (*)
|    \--- org.thymeleaf:thymeleaf-spring6:3.1.3.RELEASE
|         +--- org.thymeleaf:thymeleaf:3.1.3.RELEASE
|         |    +--- org.attoparser:attoparser:2.0.7.RELEASE
|         |    +--- org.unbescape:unbescape:1.1.6.RELEASE
|         |    \--- org.slf4j:slf4j-api:2.0.16
|         \--- org.slf4j:slf4j-api:2.0.16
+--- org.springframework.boot:spring-boot-starter-security -> 3.4.3
|    +--- org.springframework.boot:spring-boot-starter:3.4.3 (*)
|    +--- org.springframework:spring-aop:6.2.3 (*)
|    +--- org.springframework.security:spring-security-config:6.4.3
|    |    +--- org.springframework.security:spring-security-core:6.4.3
|    |    |    +--- org.springframework.security:spring-security-crypto:6.4.3
|    |    |    +--- org.springframework:spring-aop:6.2.3 (*)
|    |    |    +--- org.springframework:spring-beans:6.2.3 (*)
|    |    |    +--- org.springframework:spring-context:6.2.3 (*)
|    |    |    +--- org.springframework:spring-core:6.2.3 (*)
|    |    |    +--- org.springframework:spring-expression:6.2.3 (*)
|    |    |    \--- io.micrometer:micrometer-observation:1.14.4 (*)
|    |    +--- org.springframework:spring-aop:6.2.3 (*)
|    |    +--- org.springframework:spring-beans:6.2.3 (*)
|    |    +--- org.springframework:spring-context:6.2.3 (*)
|    |    \--- org.springframework:spring-core:6.2.3 (*)
|    \--- org.springframework.security:spring-security-web:6.4.3
|         +--- org.springframework.security:spring-security-core:6.4.3 (*)
|         +--- org.springframework:spring-core:6.2.3 (*)
|         +--- org.springframework:spring-aop:6.2.3 (*)
|         +--- org.springframework:spring-beans:6.2.3 (*)
|         +--- org.springframework:spring-context:6.2.3 (*)
|         +--- org.springframework:spring-expression:6.2.3 (*)
|         \--- org.springframework:spring-web:6.2.3 (*)
+--- org.springframework.data:spring-data-commons -> 3.4.3
|    +--- org.springframework:spring-core:6.2.3 (*)
|    +--- org.springframework:spring-beans:6.2.3 (*)
|    \--- org.slf4j:slf4j-api:2.0.2 -> 2.0.16
\--- org.springframework.boot:spring-boot-starter-test -> 3.4.3
     +--- org.springframework.boot:spring-boot-starter:3.4.3 (*)
     +--- org.springframework.boot:spring-boot-test:3.4.3
     |    +--- org.springframework.boot:spring-boot:3.4.3 (*)
     |    \--- org.springframework:spring-test:6.2.3
     |         \--- org.springframework:spring-core:6.2.3 (*)
     +--- org.springframework.boot:spring-boot-test-autoconfigure:3.4.3
     |    +--- org.springframework.boot:spring-boot:3.4.3 (*)
     |    +--- org.springframework.boot:spring-boot-test:3.4.3 (*)
     |    \--- org.springframework.boot:spring-boot-autoconfigure:3.4.3 (*)
     +--- com.jayway.jsonpath:json-path:2.9.0
     |    +--- net.minidev:json-smart:2.5.0 -> 2.5.2
     |    |    \--- net.minidev:accessors-smart:2.5.2
     |    |         \--- org.ow2.asm:asm:9.7.1
     |    \--- org.slf4j:slf4j-api:2.0.11 -> 2.0.16
     +--- jakarta.xml.bind:jakarta.xml.bind-api:4.0.2
     |    \--- jakarta.activation:jakarta.activation-api:2.1.3
     +--- net.minidev:json-smart:2.5.2 (*)
     +--- org.assertj:assertj-core:3.26.3
     |    \--- net.bytebuddy:byte-buddy:1.14.18 -> 1.15.11
     +--- org.awaitility:awaitility:4.2.2
     |    \--- org.hamcrest:hamcrest:2.1 -> 2.2
     +--- org.hamcrest:hamcrest:2.2
     +--- org.junit.jupiter:junit-jupiter:5.11.4
     |    +--- org.junit:junit-bom:5.11.4
     |    |    +--- org.junit.jupiter:junit-jupiter:5.11.4 (c)
     |    |    +--- org.junit.jupiter:junit-jupiter-api:5.11.4 (c)
     |    |    +--- org.junit.jupiter:junit-jupiter-engine:5.11.4 (c)
     |    |    +--- org.junit.jupiter:junit-jupiter-params:5.11.4 (c)
     |    |    +--- org.junit.platform:junit-platform-engine:1.11.4 (c)
     |    |    +--- org.junit.platform:junit-platform-launcher:1.11.4 (c)
     |    |    \--- org.junit.platform:junit-platform-commons:1.11.4 (c)
     |    +--- org.junit.jupiter:junit-jupiter-api:5.11.4
     |    |    +--- org.junit:junit-bom:5.11.4 (*)
     |    |    +--- org.opentest4j:opentest4j:1.3.0
     |    |    \--- org.junit.platform:junit-platform-commons:1.11.4
     |    |         \--- org.junit:junit-bom:5.11.4 (*)
     |    +--- org.junit.jupiter:junit-jupiter-params:5.11.4
     |    |    +--- org.junit:junit-bom:5.11.4 (*)
     |    |    \--- org.junit.jupiter:junit-jupiter-api:5.11.4 (*)
     |    \--- org.junit.jupiter:junit-jupiter-engine:5.11.4
     |         +--- org.junit:junit-bom:5.11.4 (*)
     |         +--- org.junit.platform:junit-platform-engine:1.11.4
     |         |    +--- org.junit:junit-bom:5.11.4 (*)
     |         |    +--- org.opentest4j:opentest4j:1.3.0
     |         |    \--- org.junit.platform:junit-platform-commons:1.11.4 (*)
     |         \--- org.junit.jupiter:junit-jupiter-api:5.11.4 (*)
     +--- org.mockito:mockito-core:5.14.2
     |    +--- net.bytebuddy:byte-buddy:1.15.4 -> 1.15.11
     |    +--- net.bytebuddy:byte-buddy-agent:1.15.4 -> 1.15.11
     |    \--- org.objenesis:objenesis:3.3
     +--- org.mockito:mockito-junit-jupiter:5.14.2
     |    +--- org.mockito:mockito-core:5.14.2 (*)
     |    \--- org.junit.jupiter:junit-jupiter-api:5.11.2 -> 5.11.4 (*)
     +--- org.skyscreamer:jsonassert:1.5.3
     |    \--- com.vaadin.external.google:android-json:0.0.20131108.vaadin1
     +--- org.springframework:spring-core:6.2.3 (*)
     +--- org.springframework:spring-test:6.2.3 (*)
     +--- org.xmlunit:xmlunit-core:2.10.0
     \--- org.junit.platform:junit-platform-launcher -> 1.11.4
          +--- org.junit:junit-bom:5.11.4 (*)
          \--- org.junit.platform:junit-platform-engine:1.11.4 (*)

testRuntimeOnly - Runtime only dependencies for source set 'test'. (n)
No dependencies

(c) - A dependency constraint, not a dependency. The dependency affected by the constraint occurs elsewhere in the tree.
(*) - Indicates repeated occurrences of a transitive dependency subtree. Gradle expands transitive dependency subtrees only once per project; repeat occurrences only display the root of the subtree, followed by this annotation.

(n) - A dependency or dependency configuration that cannot be resolved.

A web-based, searchable dependency report is available by adding the --scan option.

BUILD SUCCESSFUL in 11s
1 actionable task: 1 executed
Consider enabling configuration cache to speed up this build: https://docs.gradle.org/9.0.0/userguide/configuration_cache_enabling.html
```

## Interpretation Summary

### Direct Dependencies (declared in build.gradle)
- Spring Boot starters: `spring-boot-starter-web`, `spring-boot-starter-validation`, `spring-boot-starter-thymeleaf`, `spring-boot-starter-security`
- Spring Data: `spring-data-commons`
- Lombok: `org.projectlombok:lombok` (compileOnly + annotationProcessor)
- Test bundle: `spring-boot-starter-test`

### Key Resolved Versions
- Spring Boot: 3.4.3
- Spring Framework: 6.2.3
- Spring Security: 6.4.3
- Jackson BOM: 2.18.2 (aligns jackson-core/annotations/databind/etc.)
- Tomcat Embed: 10.1.36
- Hibernate Validator: 8.0.2.Final
- Thymeleaf: 3.1.3.RELEASE
- SLF4J: 2.0.16 (aligned by BOM, overrides older transitive versions)

### Notable Transitive Groups
- Web stack: Spring MVC + embedded Tomcat + Jackson JSON
- Validation: Hibernate Validator + Jakarta Validation API
- Security: spring-security-config/web/core + crypto
- Observability: Micrometer observation + commons
- Testing: JUnit 5, Mockito, AssertJ, Hamcrest, Awaitility, JSONassert, XMLUnit

### Configurations Explained (what the graph shows)
- `compileClasspath`: what main source code compiles against.
- `runtimeClasspath` / `productionRuntimeClasspath`: runtime dependencies for the app.
- `testRuntimeClasspath`: runtime dependencies for tests (includes app deps + test libs).
- `annotationProcessor`: Lombok used during compilation only.
- `compileOnly`: Lombok on compile-time classpath only, not packaged.

### Resolution Notes
- Many entries show `->` where Gradle upgraded versions due to BOM alignment.
- `(c)` marks constraints from BOMs (e.g., Jackson BOM, JUnit BOM).
- `(*)` indicates repeated transitive subtrees collapsed for readability.

