# IN306 - Verteilte Systeme

## Blog Project

This project will help us get an overview of the Quarkus Java Framework to
easily create backend services.
The topics that we learn during guided lessons will be implemented in the dev branch of this project.

## API Design

### Entries

Entries are in its basic form just some blog posts.
These posts contain a title, description as well as the actual content.
Entries should be able to be created, edited and removed through the backend API.

#### GET

- Simple GET to the "entries" endpoint will list all entries.
- Using the Query Param "?search=searchtext", we can filter the returned entries.
- GET on "entries/{id}" will list the specific entry
- If entry does not exist, an error message will be returned to the API user.

#### POST

- Create a new entry by submitting a valid JSON object to the api
- Check the Entry JSON object reference :)

#### PATCH

- By sending a valid JSON object to the endpoint with the reference of the to-be-updated entry with "entries/{id}", the object will be updated with the newly submitted information

#### DELETE

- Calling the "entries/{id}" endpoint, the specified entry can be deleted
- Delete only works, if the correct authKey has been submitted through the request header.
- Header value for authorization == "auth"
- In our super-secure demo case, that would be any string starting with "elevated"

#### Entry JSON

JSON reference object for creating your entries through the API

```json
{
  "title": "My First Blog",
  "description": "This is a super insightful description",
  "content": "Blogging is super cool, but not for me.. But here we are anyways, my disappointment is immeasurable and my day is ruined."
}
```

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

# Reactive Quarkus

## Introduction

For example user events are a traditional use case for reactive programming.
eg. - When a user clicks a button it should execute a specific function.
So reactive means, we are reacting on actions, here a user clicking a button.

This paradigm is especially useful and helps with following characteristics:

- High data scale
- High usage scale
- Cloud based costs

## How to scale up

- Vertical Scaling
- Add more power to your server
- Horizontal Scaling
- Adding more servers

To be able to scale horizontally, well written code needs to be produced or legacy code needs to be optimized.

For example, a function that receives a user and its preferences over two different services.
Code that is unecessarily sequential would cause the code to wait for the first service to finish.
This is blocking code further raising cost in performance and needed compute costs.
