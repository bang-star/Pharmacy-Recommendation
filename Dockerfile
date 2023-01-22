# jar 파일 빌드
FROM openjdk:17 as builder

COPY gradlew .
COPY gradle gradle
COPY build.gradle .
COPY settings.gradle .
COPY src src
RUN chmod +x ./gradlew
RUN ./gradlew bootjar

# jar 실행
FROM openjdk:17 as runtime
RUN addgroup --system --gid 1000 worker
RUN adduser --system --uid 1000 --ingroup worker --disabled-password worker
USER worker:worker

COPY --from=builder build/libs/*.jar app.jar

ENV SPRING_DATASOURCE_USERNAME ${SPRING_DATASOURCE_USERNAME}
ENV SPRING_DATASOURCE_PASSWORD ${SPRING_DATASOURCE_PASSWORD}
ENV SPRING_PROFILES_ACTIVE ${SPRING_PROFILES_ACTIVE}
ENV KAKAO_REST_API_KEY ${KAKAO_REST_API_KEY}

EXPOSE 8080

ENV TZ=Asiz/Seoul
ENTRYPOINT ["java", "-Dspring.profiles.active=${SPRING_PROFILES_ACTIVE}", "-jar", "./app.jar"]