# IN306 - Verteilte Systeme

## Blog Project

This project will help us get an overview of the Quarkus Java Framework to
easily create backend services.
The topics that we learn during guided lessons will be implemented in the dev branch of this project.

## Running the application in dev mode

We can run this application in dev mode using:

```shell script
./mvnw compile quarkus:dev
```

This will enable live coding and makes our api available under
http://localhost:8080

We can also check out the openapi documentation to interact with our api
http://localhost:8080/q/swagger-ui/
or GET http://localhost:8080/q/openapi/

## Packaging and running the application

The application can be packaged using:

```shell script
./mvnw package
```

It produces the `quarkus-run.jar` file in the `target/quarkus-app/` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `target/quarkus-app/lib/` directory.

The application is now runnable using `java -jar target/quarkus-app/quarkus-run.jar`.

If you want to build an _über-jar_, execute the following command:

```shell script
./mvnw package -Dquarkus.package.type=uber-jar
```

The application, packaged as an _über-jar_, is now runnable using `java -jar target/*-runner.jar`.

## Creating a native executable

You can create a native executable using:

```shell script
./mvnw package -Pnative
```

Or, if you don't have GraalVM installed, you can run the native executable build in a container using:

```shell script
./mvnw package -Pnative -Dquarkus.native.container-build=true
```

You can then execute your native executable with: `./target/in306-blog-0.1-runner`

If you want to learn more about building native executables, please consult https://quarkus.io/guides/maven-tooling.
