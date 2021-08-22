# AutoParams 의존성 참조 이슈 재현

의도치 않게 AutoParams가 junit-jupiter-params:5.7.2를 참조하는 이슈를 재현한다.

## 패키지 의존 그래프

```shell
$ ./gradlew :dependencies

------------------------------------------------------------
Root project 'AutoParamsDependencyIssue'
------------------------------------------------------------

annotationProcessor - Annotation processors and their dependencies for source set 'main'.
No dependencies

api - API dependencies for compilation 'main' (target  (jvm)). (n)
No dependencies

apiDependenciesMetadata
No dependencies

apiElements - API elements for main. (n)
No dependencies

apiElements-published (n)
No dependencies

archives - Configuration for archive artifacts. (n)
No dependencies

bootArchives - Configuration for Spring Boot archive artifacts. (n)
No dependencies

compileClasspath - Compile classpath for compilation 'main' (target  (jvm)).
+--- org.springframework.boot:spring-boot-starter -> 2.5.4
|    +--- org.springframework.boot:spring-boot:2.5.4
|    |    +--- org.springframework:spring-core:5.3.9
|    |    |    \--- org.springframework:spring-jcl:5.3.9
|    |    \--- org.springframework:spring-context:5.3.9
|    |         +--- org.springframework:spring-aop:5.3.9
|    |         |    +--- org.springframework:spring-beans:5.3.9
|    |         |    |    \--- org.springframework:spring-core:5.3.9 (*)
|    |         |    \--- org.springframework:spring-core:5.3.9 (*)
|    |         +--- org.springframework:spring-beans:5.3.9 (*)
|    |         +--- org.springframework:spring-core:5.3.9 (*)
|    |         \--- org.springframework:spring-expression:5.3.9
|    |              \--- org.springframework:spring-core:5.3.9 (*)
|    +--- org.springframework.boot:spring-boot-autoconfigure:2.5.4
|    |    \--- org.springframework.boot:spring-boot:2.5.4 (*)
|    +--- org.springframework.boot:spring-boot-starter-logging:2.5.4
|    |    +--- ch.qos.logback:logback-classic:1.2.5
|    |    |    +--- ch.qos.logback:logback-core:1.2.5
|    |    |    \--- org.slf4j:slf4j-api:1.7.31 -> 1.7.32
|    |    +--- org.apache.logging.log4j:log4j-to-slf4j:2.14.1
|    |    |    +--- org.slf4j:slf4j-api:1.7.25 -> 1.7.32
|    |    |    \--- org.apache.logging.log4j:log4j-api:2.14.1
|    |    \--- org.slf4j:jul-to-slf4j:1.7.32
|    |         \--- org.slf4j:slf4j-api:1.7.32
|    +--- jakarta.annotation:jakarta.annotation-api:1.3.5
|    +--- org.springframework:spring-core:5.3.9 (*)
|    \--- org.yaml:snakeyaml:1.28
+--- org.jetbrains.kotlin:kotlin-reflect:1.5.21
|    \--- org.jetbrains.kotlin:kotlin-stdlib:1.5.21
|         +--- org.jetbrains:annotations:13.0
|         \--- org.jetbrains.kotlin:kotlin-stdlib-common:1.5.21
\--- org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.5.21
     +--- org.jetbrains.kotlin:kotlin-stdlib:1.5.21 (*)
     \--- org.jetbrains.kotlin:kotlin-stdlib-jdk7:1.5.21
          \--- org.jetbrains.kotlin:kotlin-stdlib:1.5.21 (*)

compileOnly - Compile only dependencies for compilation 'main' (target  (jvm)).
No dependencies

compileOnlyDependenciesMetadata
No dependencies

default - Configuration for default artifacts. (n)
No dependencies

developmentOnly - Configuration for development-only dependencies such as Spring Boot's DevTools.
No dependencies

implementation - Implementation only dependencies for compilation 'main' (target  (jvm)). (n)
+--- org.springframework.boot:spring-boot-starter (n)
+--- org.jetbrains.kotlin:kotlin-reflect:1.5.21 (n)
\--- org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.5.21 (n)

implementationDependenciesMetadata
+--- org.springframework.boot:spring-boot-starter -> 2.5.4
|    +--- org.springframework.boot:spring-boot:2.5.4
|    |    +--- org.springframework:spring-core:5.3.9
|    |    |    \--- org.springframework:spring-jcl:5.3.9
|    |    \--- org.springframework:spring-context:5.3.9
|    |         +--- org.springframework:spring-aop:5.3.9
|    |         |    +--- org.springframework:spring-beans:5.3.9
|    |         |    |    \--- org.springframework:spring-core:5.3.9 (*)
|    |         |    \--- org.springframework:spring-core:5.3.9 (*)
|    |         +--- org.springframework:spring-beans:5.3.9 (*)
|    |         +--- org.springframework:spring-core:5.3.9 (*)
|    |         \--- org.springframework:spring-expression:5.3.9
|    |              \--- org.springframework:spring-core:5.3.9 (*)
|    +--- org.springframework.boot:spring-boot-autoconfigure:2.5.4
|    |    \--- org.springframework.boot:spring-boot:2.5.4 (*)
|    +--- org.springframework.boot:spring-boot-starter-logging:2.5.4
|    |    +--- ch.qos.logback:logback-classic:1.2.5
|    |    |    +--- ch.qos.logback:logback-core:1.2.5
|    |    |    \--- org.slf4j:slf4j-api:1.7.31 -> 1.7.32
|    |    +--- org.apache.logging.log4j:log4j-to-slf4j:2.14.1
|    |    |    +--- org.slf4j:slf4j-api:1.7.25 -> 1.7.32
|    |    |    \--- org.apache.logging.log4j:log4j-api:2.14.1
|    |    \--- org.slf4j:jul-to-slf4j:1.7.32
|    |         \--- org.slf4j:slf4j-api:1.7.32
|    +--- jakarta.annotation:jakarta.annotation-api:1.3.5
|    +--- org.springframework:spring-core:5.3.9 (*)
|    \--- org.yaml:snakeyaml:1.28
+--- org.jetbrains.kotlin:kotlin-reflect:1.5.21
|    \--- org.jetbrains.kotlin:kotlin-stdlib:1.5.21
|         +--- org.jetbrains:annotations:13.0
|         \--- org.jetbrains.kotlin:kotlin-stdlib-common:1.5.21
\--- org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.5.21
     +--- org.jetbrains.kotlin:kotlin-stdlib:1.5.21 (*)
     \--- org.jetbrains.kotlin:kotlin-stdlib-jdk7:1.5.21
          \--- org.jetbrains.kotlin:kotlin-stdlib:1.5.21 (*)

intransitiveDependenciesMetadata
No dependencies

kotlinCompilerClasspath
\--- org.jetbrains.kotlin:kotlin-compiler-embeddable:1.5.21
     +--- org.jetbrains.kotlin:kotlin-stdlib:1.5.21
     |    +--- org.jetbrains:annotations:13.0
     |    \--- org.jetbrains.kotlin:kotlin-stdlib-common:1.5.21
     +--- org.jetbrains.kotlin:kotlin-script-runtime:1.5.21
     +--- org.jetbrains.kotlin:kotlin-reflect:1.5.21
     |    \--- org.jetbrains.kotlin:kotlin-stdlib:1.5.21 (*)
     +--- org.jetbrains.kotlin:kotlin-daemon-embeddable:1.5.21
     \--- org.jetbrains.intellij.deps:trove4j:1.0.20181211

kotlinCompilerPluginClasspath
No dependencies

kotlinCompilerPluginClasspathMain - Kotlin compiler plugins for compilation 'main' (target  (jvm))
+--- org.jetbrains.kotlin:kotlin-scripting-compiler-embeddable:1.5.21
|    +--- org.jetbrains.kotlin:kotlin-scripting-compiler-impl-embeddable:1.5.21
|    |    +--- org.jetbrains.kotlin:kotlin-scripting-common:1.5.21
|    |    |    +--- org.jetbrains.kotlin:kotlin-stdlib:1.5.21
|    |    |    |    +--- org.jetbrains:annotations:13.0
|    |    |    |    \--- org.jetbrains.kotlin:kotlin-stdlib-common:1.5.21
|    |    |    \--- org.jetbrains.kotlinx:kotlinx-coroutines-core:1.3.8 -> 1.5.1
|    |    |         \--- org.jetbrains.kotlinx:kotlinx-coroutines-core-jvm:1.5.1
|    |    |              +--- org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.5.20 -> 1.5.21
|    |    |              |    +--- org.jetbrains.kotlin:kotlin-stdlib:1.5.21 (*)
|    |    |              |    \--- org.jetbrains.kotlin:kotlin-stdlib-jdk7:1.5.21
|    |    |              |         \--- org.jetbrains.kotlin:kotlin-stdlib:1.5.21 (*)
|    |    |              \--- org.jetbrains.kotlin:kotlin-stdlib-common:1.5.20 -> 1.5.21
|    |    +--- org.jetbrains.kotlin:kotlin-scripting-jvm:1.5.21
|    |    |    +--- org.jetbrains.kotlin:kotlin-script-runtime:1.5.21
|    |    |    +--- org.jetbrains.kotlin:kotlin-stdlib:1.5.21 (*)
|    |    |    \--- org.jetbrains.kotlin:kotlin-scripting-common:1.5.21 (*)
|    |    +--- org.jetbrains.kotlin:kotlin-stdlib:1.5.21 (*)
|    |    \--- org.jetbrains.kotlinx:kotlinx-coroutines-core:1.3.8 -> 1.5.1 (*)
|    \--- org.jetbrains.kotlin:kotlin-stdlib:1.5.21 (*)
\--- org.jetbrains.kotlin:kotlin-allopen:1.5.21
     +--- org.jetbrains.kotlin:kotlin-gradle-plugin-api:1.5.21
     |    +--- org.jetbrains.kotlin:kotlin-native-utils:1.5.21
     |    |    \--- org.jetbrains.kotlin:kotlin-util-io:1.5.21
     |    \--- org.jetbrains.kotlin:kotlin-project-model:1.5.21
     +--- org.jetbrains.kotlin:kotlin-gradle-plugin-model:1.5.21
     \--- org.jetbrains.kotlin:kotlin-stdlib:1.5.21 (*)

kotlinCompilerPluginClasspathTest - Kotlin compiler plugins for compilation 'test' (target  (jvm))
+--- org.jetbrains.kotlin:kotlin-scripting-compiler-embeddable:1.5.21
|    +--- org.jetbrains.kotlin:kotlin-scripting-compiler-impl-embeddable:1.5.21
|    |    +--- org.jetbrains.kotlin:kotlin-scripting-common:1.5.21
|    |    |    +--- org.jetbrains.kotlin:kotlin-stdlib:1.5.21
|    |    |    |    +--- org.jetbrains:annotations:13.0
|    |    |    |    \--- org.jetbrains.kotlin:kotlin-stdlib-common:1.5.21
|    |    |    \--- org.jetbrains.kotlinx:kotlinx-coroutines-core:1.3.8 -> 1.5.1
|    |    |         \--- org.jetbrains.kotlinx:kotlinx-coroutines-core-jvm:1.5.1
|    |    |              +--- org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.5.20 -> 1.5.21
|    |    |              |    +--- org.jetbrains.kotlin:kotlin-stdlib:1.5.21 (*)
|    |    |              |    \--- org.jetbrains.kotlin:kotlin-stdlib-jdk7:1.5.21
|    |    |              |         \--- org.jetbrains.kotlin:kotlin-stdlib:1.5.21 (*)
|    |    |              \--- org.jetbrains.kotlin:kotlin-stdlib-common:1.5.20 -> 1.5.21
|    |    +--- org.jetbrains.kotlin:kotlin-scripting-jvm:1.5.21
|    |    |    +--- org.jetbrains.kotlin:kotlin-script-runtime:1.5.21
|    |    |    +--- org.jetbrains.kotlin:kotlin-stdlib:1.5.21 (*)
|    |    |    \--- org.jetbrains.kotlin:kotlin-scripting-common:1.5.21 (*)
|    |    +--- org.jetbrains.kotlin:kotlin-stdlib:1.5.21 (*)
|    |    \--- org.jetbrains.kotlinx:kotlinx-coroutines-core:1.3.8 -> 1.5.1 (*)
|    \--- org.jetbrains.kotlin:kotlin-stdlib:1.5.21 (*)
\--- org.jetbrains.kotlin:kotlin-allopen:1.5.21
     +--- org.jetbrains.kotlin:kotlin-gradle-plugin-api:1.5.21
     |    +--- org.jetbrains.kotlin:kotlin-native-utils:1.5.21
     |    |    \--- org.jetbrains.kotlin:kotlin-util-io:1.5.21
     |    \--- org.jetbrains.kotlin:kotlin-project-model:1.5.21
     +--- org.jetbrains.kotlin:kotlin-gradle-plugin-model:1.5.21
     \--- org.jetbrains.kotlin:kotlin-stdlib:1.5.21 (*)

kotlinKlibCommonizerClasspath
\--- org.jetbrains.kotlin:kotlin-klib-commonizer-embeddable:1.5.21
     +--- org.jetbrains.kotlin:kotlin-stdlib:1.5.21
     |    +--- org.jetbrains:annotations:13.0
     |    \--- org.jetbrains.kotlin:kotlin-stdlib-common:1.5.21
     \--- org.jetbrains.kotlin:kotlin-compiler-embeddable:1.5.21
          +--- org.jetbrains.kotlin:kotlin-stdlib:1.5.21 (*)
          +--- org.jetbrains.kotlin:kotlin-script-runtime:1.5.21
          +--- org.jetbrains.kotlin:kotlin-reflect:1.5.21
          |    \--- org.jetbrains.kotlin:kotlin-stdlib:1.5.21 (*)
          +--- org.jetbrains.kotlin:kotlin-daemon-embeddable:1.5.21
          \--- org.jetbrains.intellij.deps:trove4j:1.0.20181211

kotlinNativeCompilerPluginClasspath
No dependencies

kotlinScriptDef - Script filename extensions discovery classpath configuration
No dependencies

kotlinScriptDefExtensions
No dependencies

productionRuntimeClasspath
+--- org.springframework.boot:spring-boot-starter -> 2.5.4
|    +--- org.springframework.boot:spring-boot:2.5.4
|    |    +--- org.springframework:spring-core:5.3.9
|    |    |    \--- org.springframework:spring-jcl:5.3.9
|    |    \--- org.springframework:spring-context:5.3.9
|    |         +--- org.springframework:spring-aop:5.3.9
|    |         |    +--- org.springframework:spring-beans:5.3.9
|    |         |    |    \--- org.springframework:spring-core:5.3.9 (*)
|    |         |    \--- org.springframework:spring-core:5.3.9 (*)
|    |         +--- org.springframework:spring-beans:5.3.9 (*)
|    |         +--- org.springframework:spring-core:5.3.9 (*)
|    |         \--- org.springframework:spring-expression:5.3.9
|    |              \--- org.springframework:spring-core:5.3.9 (*)
|    +--- org.springframework.boot:spring-boot-autoconfigure:2.5.4
|    |    \--- org.springframework.boot:spring-boot:2.5.4 (*)
|    +--- org.springframework.boot:spring-boot-starter-logging:2.5.4
|    |    +--- ch.qos.logback:logback-classic:1.2.5
|    |    |    +--- ch.qos.logback:logback-core:1.2.5
|    |    |    \--- org.slf4j:slf4j-api:1.7.31 -> 1.7.32
|    |    +--- org.apache.logging.log4j:log4j-to-slf4j:2.14.1
|    |    |    +--- org.slf4j:slf4j-api:1.7.25 -> 1.7.32
|    |    |    \--- org.apache.logging.log4j:log4j-api:2.14.1
|    |    \--- org.slf4j:jul-to-slf4j:1.7.32
|    |         \--- org.slf4j:slf4j-api:1.7.32
|    +--- jakarta.annotation:jakarta.annotation-api:1.3.5
|    +--- org.springframework:spring-core:5.3.9 (*)
|    \--- org.yaml:snakeyaml:1.28
+--- org.jetbrains.kotlin:kotlin-reflect:1.5.21
|    \--- org.jetbrains.kotlin:kotlin-stdlib:1.5.21
|         +--- org.jetbrains:annotations:13.0
|         \--- org.jetbrains.kotlin:kotlin-stdlib-common:1.5.21
\--- org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.5.21
     +--- org.jetbrains.kotlin:kotlin-stdlib:1.5.21 (*)
     \--- org.jetbrains.kotlin:kotlin-stdlib-jdk7:1.5.21
          \--- org.jetbrains.kotlin:kotlin-stdlib:1.5.21 (*)

runtimeClasspath - Runtime classpath of compilation 'main' (target  (jvm)).
+--- org.springframework.boot:spring-boot-starter -> 2.5.4
|    +--- org.springframework.boot:spring-boot:2.5.4
|    |    +--- org.springframework:spring-core:5.3.9
|    |    |    \--- org.springframework:spring-jcl:5.3.9
|    |    \--- org.springframework:spring-context:5.3.9
|    |         +--- org.springframework:spring-aop:5.3.9
|    |         |    +--- org.springframework:spring-beans:5.3.9
|    |         |    |    \--- org.springframework:spring-core:5.3.9 (*)
|    |         |    \--- org.springframework:spring-core:5.3.9 (*)
|    |         +--- org.springframework:spring-beans:5.3.9 (*)
|    |         +--- org.springframework:spring-core:5.3.9 (*)
|    |         \--- org.springframework:spring-expression:5.3.9
|    |              \--- org.springframework:spring-core:5.3.9 (*)
|    +--- org.springframework.boot:spring-boot-autoconfigure:2.5.4
|    |    \--- org.springframework.boot:spring-boot:2.5.4 (*)
|    +--- org.springframework.boot:spring-boot-starter-logging:2.5.4
|    |    +--- ch.qos.logback:logback-classic:1.2.5
|    |    |    +--- ch.qos.logback:logback-core:1.2.5
|    |    |    \--- org.slf4j:slf4j-api:1.7.31 -> 1.7.32
|    |    +--- org.apache.logging.log4j:log4j-to-slf4j:2.14.1
|    |    |    +--- org.slf4j:slf4j-api:1.7.25 -> 1.7.32
|    |    |    \--- org.apache.logging.log4j:log4j-api:2.14.1
|    |    \--- org.slf4j:jul-to-slf4j:1.7.32
|    |         \--- org.slf4j:slf4j-api:1.7.32
|    +--- jakarta.annotation:jakarta.annotation-api:1.3.5
|    +--- org.springframework:spring-core:5.3.9 (*)
|    \--- org.yaml:snakeyaml:1.28
+--- org.jetbrains.kotlin:kotlin-reflect:1.5.21
|    \--- org.jetbrains.kotlin:kotlin-stdlib:1.5.21
|         +--- org.jetbrains:annotations:13.0
|         \--- org.jetbrains.kotlin:kotlin-stdlib-common:1.5.21
\--- org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.5.21
     +--- org.jetbrains.kotlin:kotlin-stdlib:1.5.21 (*)
     \--- org.jetbrains.kotlin:kotlin-stdlib-jdk7:1.5.21
          \--- org.jetbrains.kotlin:kotlin-stdlib:1.5.21 (*)

runtimeElements - Elements of runtime for main. (n)
No dependencies

runtimeElements-published (n)
No dependencies

runtimeOnly - Runtime only dependencies for compilation 'main' (target  (jvm)). (n)
No dependencies

runtimeOnlyDependenciesMetadata
No dependencies

sourceArtifacts (n)
No dependencies

testAnnotationProcessor - Annotation processors and their dependencies for source set 'test'.
No dependencies

testApi - API dependencies for compilation 'test' (target  (jvm)). (n)
No dependencies

testApiDependenciesMetadata
No dependencies

testCompileClasspath - Compile classpath for compilation 'test' (target  (jvm)).
+--- org.springframework.boot:spring-boot-starter -> 2.5.4
|    +--- org.springframework.boot:spring-boot:2.5.4
|    |    +--- org.springframework:spring-core:5.3.9
|    |    |    \--- org.springframework:spring-jcl:5.3.9
|    |    \--- org.springframework:spring-context:5.3.9
|    |         +--- org.springframework:spring-aop:5.3.9
|    |         |    +--- org.springframework:spring-beans:5.3.9
|    |         |    |    \--- org.springframework:spring-core:5.3.9 (*)
|    |         |    \--- org.springframework:spring-core:5.3.9 (*)
|    |         +--- org.springframework:spring-beans:5.3.9 (*)
|    |         +--- org.springframework:spring-core:5.3.9 (*)
|    |         \--- org.springframework:spring-expression:5.3.9
|    |              \--- org.springframework:spring-core:5.3.9 (*)
|    +--- org.springframework.boot:spring-boot-autoconfigure:2.5.4
|    |    \--- org.springframework.boot:spring-boot:2.5.4 (*)
|    +--- org.springframework.boot:spring-boot-starter-logging:2.5.4
|    |    +--- ch.qos.logback:logback-classic:1.2.5
|    |    |    +--- ch.qos.logback:logback-core:1.2.5
|    |    |    \--- org.slf4j:slf4j-api:1.7.31 -> 1.7.32
|    |    +--- org.apache.logging.log4j:log4j-to-slf4j:2.14.1
|    |    |    +--- org.slf4j:slf4j-api:1.7.25 -> 1.7.32
|    |    |    \--- org.apache.logging.log4j:log4j-api:2.14.1
|    |    \--- org.slf4j:jul-to-slf4j:1.7.32
|    |         \--- org.slf4j:slf4j-api:1.7.32
|    +--- jakarta.annotation:jakarta.annotation-api:1.3.5
|    +--- org.springframework:spring-core:5.3.9 (*)
|    \--- org.yaml:snakeyaml:1.28
+--- org.jetbrains.kotlin:kotlin-reflect:1.5.21
|    \--- org.jetbrains.kotlin:kotlin-stdlib:1.5.21
|         +--- org.jetbrains:annotations:13.0
|         \--- org.jetbrains.kotlin:kotlin-stdlib-common:1.5.21
+--- org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.5.21
|    +--- org.jetbrains.kotlin:kotlin-stdlib:1.5.21 (*)
|    \--- org.jetbrains.kotlin:kotlin-stdlib-jdk7:1.5.21
|         \--- org.jetbrains.kotlin:kotlin-stdlib:1.5.21 (*)
+--- org.springframework.boot:spring-boot-starter-test -> 2.5.4
|    +--- org.springframework.boot:spring-boot-starter:2.5.4 (*)
|    +--- org.springframework.boot:spring-boot-test:2.5.4
|    |    \--- org.springframework.boot:spring-boot:2.5.4 (*)
|    +--- org.springframework.boot:spring-boot-test-autoconfigure:2.5.4
|    |    +--- org.springframework.boot:spring-boot:2.5.4 (*)
|    |    +--- org.springframework.boot:spring-boot-test:2.5.4 (*)
|    |    \--- org.springframework.boot:spring-boot-autoconfigure:2.5.4 (*)
|    +--- com.jayway.jsonpath:json-path:2.5.0
|    |    +--- net.minidev:json-smart:2.3 -> 2.4.7
|    |    |    \--- net.minidev:accessors-smart:2.4.7
|    |    |         \--- org.ow2.asm:asm:9.1
|    |    \--- org.slf4j:slf4j-api:1.7.30 -> 1.7.32
|    +--- jakarta.xml.bind:jakarta.xml.bind-api:2.3.3
|    |    \--- jakarta.activation:jakarta.activation-api:1.2.2
|    +--- org.assertj:assertj-core:3.19.0
|    +--- org.hamcrest:hamcrest:2.2
|    +--- org.junit.jupiter:junit-jupiter:5.7.2
|    |    +--- org.junit:junit-bom:5.7.2
|    |    |    +--- org.junit.jupiter:junit-jupiter:5.7.2 (c)
|    |    |    +--- org.junit.jupiter:junit-jupiter-api:5.7.2 (c)
|    |    |    +--- org.junit.jupiter:junit-jupiter-params:5.7.2 (c)
|    |    |    \--- org.junit.platform:junit-platform-commons:1.7.2 (c)
|    |    +--- org.junit.jupiter:junit-jupiter-api:5.7.2
|    |    |    +--- org.junit:junit-bom:5.7.2 (*)
|    |    |    +--- org.apiguardian:apiguardian-api:1.1.0
|    |    |    +--- org.opentest4j:opentest4j:1.2.0
|    |    |    \--- org.junit.platform:junit-platform-commons:1.7.2
|    |    |         +--- org.junit:junit-bom:5.7.2 (*)
|    |    |         \--- org.apiguardian:apiguardian-api:1.1.0
|    |    \--- org.junit.jupiter:junit-jupiter-params:5.7.2
|    |         +--- org.junit:junit-bom:5.7.2 (*)
|    |         +--- org.apiguardian:apiguardian-api:1.1.0
|    |         \--- org.junit.jupiter:junit-jupiter-api:5.7.2 (*)
|    +--- org.mockito:mockito-core:3.9.0
|    |    +--- net.bytebuddy:byte-buddy:1.10.20 -> 1.10.22
|    |    +--- net.bytebuddy:byte-buddy-agent:1.10.20 -> 1.10.22
|    |    \--- org.objenesis:objenesis:3.2
|    +--- org.mockito:mockito-junit-jupiter:3.9.0
|    |    \--- org.mockito:mockito-core:3.9.0 (*)
|    +--- org.skyscreamer:jsonassert:1.5.0
|    |    \--- com.vaadin.external.google:android-json:0.0.20131108.vaadin1
|    +--- org.springframework:spring-core:5.3.9 (*)
|    +--- org.springframework:spring-test:5.3.9
|    |    \--- org.springframework:spring-core:5.3.9 (*)
|    \--- org.xmlunit:xmlunit-core:2.8.2
\--- io.github.javaunit:autoparams:0.2.8
     +--- org.junit.jupiter:junit-jupiter-api:5.6.0 -> 5.7.2 (*)
     +--- org.junit.jupiter:junit-jupiter-params:5.6.0 -> 5.7.2 (*)
     +--- javax.validation:validation-api:2.0.1.Final
     \--- com.google.code.findbugs:jsr305:3.0.2

testCompileOnly - Compile only dependencies for compilation 'test' (target  (jvm)).
No dependencies

testCompileOnlyDependenciesMetadata
No dependencies

testImplementation - Implementation only dependencies for compilation 'test' (target  (jvm)). (n)
+--- org.springframework.boot:spring-boot-starter-test (n)
\--- io.github.javaunit:autoparams:0.2.8 (n)

testImplementationDependenciesMetadata
+--- org.springframework.boot:spring-boot-starter -> 2.5.4
|    +--- org.springframework.boot:spring-boot:2.5.4
|    |    +--- org.springframework:spring-core:5.3.9
|    |    |    \--- org.springframework:spring-jcl:5.3.9
|    |    \--- org.springframework:spring-context:5.3.9
|    |         +--- org.springframework:spring-aop:5.3.9
|    |         |    +--- org.springframework:spring-beans:5.3.9
|    |         |    |    \--- org.springframework:spring-core:5.3.9 (*)
|    |         |    \--- org.springframework:spring-core:5.3.9 (*)
|    |         +--- org.springframework:spring-beans:5.3.9 (*)
|    |         +--- org.springframework:spring-core:5.3.9 (*)
|    |         \--- org.springframework:spring-expression:5.3.9
|    |              \--- org.springframework:spring-core:5.3.9 (*)
|    +--- org.springframework.boot:spring-boot-autoconfigure:2.5.4
|    |    \--- org.springframework.boot:spring-boot:2.5.4 (*)
|    +--- org.springframework.boot:spring-boot-starter-logging:2.5.4
|    |    +--- ch.qos.logback:logback-classic:1.2.5
|    |    |    +--- ch.qos.logback:logback-core:1.2.5
|    |    |    \--- org.slf4j:slf4j-api:1.7.31 -> 1.7.32
|    |    +--- org.apache.logging.log4j:log4j-to-slf4j:2.14.1
|    |    |    +--- org.slf4j:slf4j-api:1.7.25 -> 1.7.32
|    |    |    \--- org.apache.logging.log4j:log4j-api:2.14.1
|    |    \--- org.slf4j:jul-to-slf4j:1.7.32
|    |         \--- org.slf4j:slf4j-api:1.7.32
|    +--- jakarta.annotation:jakarta.annotation-api:1.3.5
|    +--- org.springframework:spring-core:5.3.9 (*)
|    \--- org.yaml:snakeyaml:1.28
+--- org.jetbrains.kotlin:kotlin-reflect:1.5.21
|    \--- org.jetbrains.kotlin:kotlin-stdlib:1.5.21
|         +--- org.jetbrains:annotations:13.0
|         \--- org.jetbrains.kotlin:kotlin-stdlib-common:1.5.21
+--- org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.5.21
|    +--- org.jetbrains.kotlin:kotlin-stdlib:1.5.21 (*)
|    \--- org.jetbrains.kotlin:kotlin-stdlib-jdk7:1.5.21
|         \--- org.jetbrains.kotlin:kotlin-stdlib:1.5.21 (*)
+--- org.springframework.boot:spring-boot-starter-test -> 2.5.4
|    +--- org.springframework.boot:spring-boot-starter:2.5.4 (*)
|    +--- org.springframework.boot:spring-boot-test:2.5.4
|    |    \--- org.springframework.boot:spring-boot:2.5.4 (*)
|    +--- org.springframework.boot:spring-boot-test-autoconfigure:2.5.4
|    |    +--- org.springframework.boot:spring-boot:2.5.4 (*)
|    |    +--- org.springframework.boot:spring-boot-test:2.5.4 (*)
|    |    \--- org.springframework.boot:spring-boot-autoconfigure:2.5.4 (*)
|    +--- com.jayway.jsonpath:json-path:2.5.0
|    |    +--- net.minidev:json-smart:2.3 -> 2.4.7
|    |    |    \--- net.minidev:accessors-smart:2.4.7
|    |    |         \--- org.ow2.asm:asm:9.1
|    |    \--- org.slf4j:slf4j-api:1.7.30 -> 1.7.32
|    +--- jakarta.xml.bind:jakarta.xml.bind-api:2.3.3
|    |    \--- jakarta.activation:jakarta.activation-api:1.2.2
|    +--- org.assertj:assertj-core:3.19.0
|    +--- org.hamcrest:hamcrest:2.2
|    +--- org.junit.jupiter:junit-jupiter:5.7.2
|    |    +--- org.junit:junit-bom:5.7.2
|    |    |    +--- org.junit.jupiter:junit-jupiter:5.7.2 (c)
|    |    |    +--- org.junit.jupiter:junit-jupiter-api:5.7.2 (c)
|    |    |    +--- org.junit.jupiter:junit-jupiter-params:5.7.2 (c)
|    |    |    \--- org.junit.platform:junit-platform-commons:1.7.2 (c)
|    |    +--- org.junit.jupiter:junit-jupiter-api:5.7.2
|    |    |    +--- org.junit:junit-bom:5.7.2 (*)
|    |    |    +--- org.apiguardian:apiguardian-api:1.1.0
|    |    |    +--- org.opentest4j:opentest4j:1.2.0
|    |    |    \--- org.junit.platform:junit-platform-commons:1.7.2
|    |    |         +--- org.junit:junit-bom:5.7.2 (*)
|    |    |         \--- org.apiguardian:apiguardian-api:1.1.0
|    |    \--- org.junit.jupiter:junit-jupiter-params:5.7.2
|    |         +--- org.junit:junit-bom:5.7.2 (*)
|    |         +--- org.apiguardian:apiguardian-api:1.1.0
|    |         \--- org.junit.jupiter:junit-jupiter-api:5.7.2 (*)
|    +--- org.mockito:mockito-core:3.9.0
|    |    +--- net.bytebuddy:byte-buddy:1.10.20 -> 1.10.22
|    |    +--- net.bytebuddy:byte-buddy-agent:1.10.20 -> 1.10.22
|    |    \--- org.objenesis:objenesis:3.2
|    +--- org.mockito:mockito-junit-jupiter:3.9.0
|    |    \--- org.mockito:mockito-core:3.9.0 (*)
|    +--- org.skyscreamer:jsonassert:1.5.0
|    |    \--- com.vaadin.external.google:android-json:0.0.20131108.vaadin1
|    +--- org.springframework:spring-core:5.3.9 (*)
|    +--- org.springframework:spring-test:5.3.9
|    |    \--- org.springframework:spring-core:5.3.9 (*)
|    \--- org.xmlunit:xmlunit-core:2.8.2
\--- io.github.javaunit:autoparams:0.2.8
     +--- org.junit.jupiter:junit-jupiter-api:5.6.0 -> 5.7.2 (*)
     +--- org.junit.jupiter:junit-jupiter-params:5.6.0 -> 5.7.2 (*)
     +--- javax.validation:validation-api:2.0.1.Final
     \--- com.google.code.findbugs:jsr305:3.0.2

testIntransitiveDependenciesMetadata
No dependencies

testKotlinScriptDef - Script filename extensions discovery classpath configuration
No dependencies

testKotlinScriptDefExtensions
No dependencies

testRuntimeClasspath - Runtime classpath of compilation 'test' (target  (jvm)).
+--- org.springframework.boot:spring-boot-starter -> 2.5.4
|    +--- org.springframework.boot:spring-boot:2.5.4
|    |    +--- org.springframework:spring-core:5.3.9
|    |    |    \--- org.springframework:spring-jcl:5.3.9
|    |    \--- org.springframework:spring-context:5.3.9
|    |         +--- org.springframework:spring-aop:5.3.9
|    |         |    +--- org.springframework:spring-beans:5.3.9
|    |         |    |    \--- org.springframework:spring-core:5.3.9 (*)
|    |         |    \--- org.springframework:spring-core:5.3.9 (*)
|    |         +--- org.springframework:spring-beans:5.3.9 (*)
|    |         +--- org.springframework:spring-core:5.3.9 (*)
|    |         \--- org.springframework:spring-expression:5.3.9
|    |              \--- org.springframework:spring-core:5.3.9 (*)
|    +--- org.springframework.boot:spring-boot-autoconfigure:2.5.4
|    |    \--- org.springframework.boot:spring-boot:2.5.4 (*)
|    +--- org.springframework.boot:spring-boot-starter-logging:2.5.4
|    |    +--- ch.qos.logback:logback-classic:1.2.5
|    |    |    +--- ch.qos.logback:logback-core:1.2.5
|    |    |    \--- org.slf4j:slf4j-api:1.7.31 -> 1.7.32
|    |    +--- org.apache.logging.log4j:log4j-to-slf4j:2.14.1
|    |    |    +--- org.slf4j:slf4j-api:1.7.25 -> 1.7.32
|    |    |    \--- org.apache.logging.log4j:log4j-api:2.14.1
|    |    \--- org.slf4j:jul-to-slf4j:1.7.32
|    |         \--- org.slf4j:slf4j-api:1.7.32
|    +--- jakarta.annotation:jakarta.annotation-api:1.3.5
|    +--- org.springframework:spring-core:5.3.9 (*)
|    \--- org.yaml:snakeyaml:1.28
+--- org.jetbrains.kotlin:kotlin-reflect:1.5.21
|    \--- org.jetbrains.kotlin:kotlin-stdlib:1.5.21
|         +--- org.jetbrains:annotations:13.0
|         \--- org.jetbrains.kotlin:kotlin-stdlib-common:1.5.21
+--- org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.5.21
|    +--- org.jetbrains.kotlin:kotlin-stdlib:1.5.21 (*)
|    \--- org.jetbrains.kotlin:kotlin-stdlib-jdk7:1.5.21
|         \--- org.jetbrains.kotlin:kotlin-stdlib:1.5.21 (*)
+--- org.springframework.boot:spring-boot-starter-test -> 2.5.4
|    +--- org.springframework.boot:spring-boot-starter:2.5.4 (*)
|    +--- org.springframework.boot:spring-boot-test:2.5.4
|    |    \--- org.springframework.boot:spring-boot:2.5.4 (*)
|    +--- org.springframework.boot:spring-boot-test-autoconfigure:2.5.4
|    |    +--- org.springframework.boot:spring-boot:2.5.4 (*)
|    |    +--- org.springframework.boot:spring-boot-test:2.5.4 (*)
|    |    \--- org.springframework.boot:spring-boot-autoconfigure:2.5.4 (*)
|    +--- com.jayway.jsonpath:json-path:2.5.0
|    |    +--- net.minidev:json-smart:2.3 -> 2.4.7
|    |    |    \--- net.minidev:accessors-smart:2.4.7
|    |    |         \--- org.ow2.asm:asm:9.1
|    |    \--- org.slf4j:slf4j-api:1.7.30 -> 1.7.32
|    +--- jakarta.xml.bind:jakarta.xml.bind-api:2.3.3
|    |    \--- jakarta.activation:jakarta.activation-api:1.2.2
|    +--- org.assertj:assertj-core:3.19.0
|    +--- org.hamcrest:hamcrest:2.2
|    +--- org.junit.jupiter:junit-jupiter:5.7.2
|    |    +--- org.junit:junit-bom:5.7.2
|    |    |    +--- org.junit.jupiter:junit-jupiter:5.7.2 (c)
|    |    |    +--- org.junit.jupiter:junit-jupiter-api:5.7.2 (c)
|    |    |    +--- org.junit.jupiter:junit-jupiter-engine:5.7.2 (c)
|    |    |    +--- org.junit.jupiter:junit-jupiter-params:5.7.2 (c)
|    |    |    +--- org.junit.platform:junit-platform-commons:1.7.2 (c)
|    |    |    \--- org.junit.platform:junit-platform-engine:1.7.2 (c)
|    |    +--- org.junit.jupiter:junit-jupiter-api:5.7.2
|    |    |    +--- org.junit:junit-bom:5.7.2 (*)
|    |    |    +--- org.apiguardian:apiguardian-api:1.1.0
|    |    |    +--- org.opentest4j:opentest4j:1.2.0
|    |    |    \--- org.junit.platform:junit-platform-commons:1.7.2
|    |    |         +--- org.junit:junit-bom:5.7.2 (*)
|    |    |         \--- org.apiguardian:apiguardian-api:1.1.0
|    |    +--- org.junit.jupiter:junit-jupiter-params:5.7.2
|    |    |    +--- org.junit:junit-bom:5.7.2 (*)
|    |    |    +--- org.apiguardian:apiguardian-api:1.1.0
|    |    |    \--- org.junit.jupiter:junit-jupiter-api:5.7.2 (*)
|    |    \--- org.junit.jupiter:junit-jupiter-engine:5.7.2
|    |         +--- org.junit:junit-bom:5.7.2 (*)
|    |         +--- org.apiguardian:apiguardian-api:1.1.0
|    |         +--- org.junit.platform:junit-platform-engine:1.7.2
|    |         |    +--- org.junit:junit-bom:5.7.2 (*)
|    |         |    +--- org.apiguardian:apiguardian-api:1.1.0
|    |         |    +--- org.opentest4j:opentest4j:1.2.0
|    |         |    \--- org.junit.platform:junit-platform-commons:1.7.2 (*)
|    |         \--- org.junit.jupiter:junit-jupiter-api:5.7.2 (*)
|    +--- org.mockito:mockito-core:3.9.0
|    |    +--- net.bytebuddy:byte-buddy:1.10.20 -> 1.10.22
|    |    +--- net.bytebuddy:byte-buddy-agent:1.10.20 -> 1.10.22
|    |    \--- org.objenesis:objenesis:3.2
|    +--- org.mockito:mockito-junit-jupiter:3.9.0
|    |    +--- org.mockito:mockito-core:3.9.0 (*)
|    |    \--- org.junit.jupiter:junit-jupiter-api:5.7.1 -> 5.7.2 (*)
|    +--- org.skyscreamer:jsonassert:1.5.0
|    |    \--- com.vaadin.external.google:android-json:0.0.20131108.vaadin1
|    +--- org.springframework:spring-core:5.3.9 (*)
|    +--- org.springframework:spring-test:5.3.9
|    |    \--- org.springframework:spring-core:5.3.9 (*)
|    \--- org.xmlunit:xmlunit-core:2.8.2
\--- io.github.javaunit:autoparams:0.2.8
     +--- org.junit.jupiter:junit-jupiter-api:5.6.0 -> 5.7.2 (*)
     +--- org.junit.jupiter:junit-jupiter-params:5.6.0 -> 5.7.2 (*)
     +--- javax.validation:validation-api:2.0.1.Final
     \--- com.google.code.findbugs:jsr305:3.0.2

testRuntimeOnly - Runtime only dependencies for compilation 'test' (target  (jvm)). (n)
No dependencies

testRuntimeOnlyDependenciesMetadata
No dependencies

(c) - dependency constraint
(*) - dependencies omitted (listed previously)

(n) - Not resolved (configuration is not meant to be resolved)

A web-based, searchable dependency report is available by adding the --scan option.

BUILD SUCCESSFUL in 11s
1 actionable task: 1 executed
```