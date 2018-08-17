FROM alpine:edge
MAINTAINER Venkaiah Chowdary Koneru <koneru.chowdary@gmail.com>
RUN apk add --no-cache openjdk8
COPY target/code-test.jar /opt/lib/
ENTRYPOINT ["/usr/bin/java"]
CMD ["-Djava.security.egd=file:/dev/./urandom", "-jar", "/opt/lib/code-test.jar"]
EXPOSE 8080