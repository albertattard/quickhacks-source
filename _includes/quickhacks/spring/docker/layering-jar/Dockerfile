FROM adoptopenjdk/openjdk11:x86_64-alpine-jdk-11.0.11_9 AS builder
WORKDIR /opt/app
COPY ./gradle gradle
COPY ./src src
COPY ./build.gradle build.gradle
COPY ./gradlew gradlew
COPY ./settings.gradle settings.gradle
RUN ./gradlew build
RUN java -Djarmode=layertools -jar build/libs/layering-jar.jar extract

FROM adoptopenjdk/openjdk11:x86_64-alpine-jre-11.0.11_9
WORKDIR /opt/app
COPY --from=builder /opt/app/dependencies ./
COPY --from=builder /opt/app/spring-boot-loader ./
COPY --from=builder /opt/app/snapshot-dependencies ./
COPY --from=builder /opt/app/application ./
ENTRYPOINT ["java", "org.springframework.boot.loader.JarLauncher"]
