# Not using openjdk:11-jre-bullseye since we to install openjre-11-jdk anyways for GUI libraries
FROM debian:bullseye-slim

RUN apt-get update && \
    apt-get install --yes --no-install-recommends \
        openjdk-11-jre && \
    apt-get clean && \
    rm -rf /var/lib/apt/lists/*

WORKDIR /Stephens-Bootleg-Osu/
COPY BootlegOsu.jar .
COPY res/ res/
ENV MUTE 1

CMD ["java", "-jar", "BootlegOsu.jar"]
