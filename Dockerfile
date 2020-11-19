FROM openjdk:11.0-jre

WORKDIR /dest
COPY ./out/fib/assembly/dest/out.jar /dest
ENTRYPOINT ["bash", "out.jar"]