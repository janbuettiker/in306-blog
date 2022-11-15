# IN306 - Verteilte Systeme

## Blog Project

This project will help us get an overview of the Quarkus Java Framework toeasily create backend services.
The topics that we learn during guided lessons will be implemented in the dev branch of this project.

## Disclaimer

My blog only runs in Quarkus Dev services (at least with a decupled database) and there are several other features
that would be required, but I left out as I had no time to fix it. I would probably need to rewrite the whole project
from scratch and I definitely can't be bothered to do that for the time being..

Following features do not work

- Auth Token, authentication did work but only through httpie, the JWT token was not accepted in DEV mode.
- Also an external instance of Keycloak could have been used, but also did not get it to work.
- There are some remains where I tried to do with Azure AD but I was too stupid to get it to work.
- You will find a docker compose file, but that does not work as well, but at least I tried
- Kafka messaging works, but you cannot see the queue in dev mode for whatever reason. (Also, the dev containers need to be started in a specific order to work)

Under the section "Running the application" I have documented the necessary steps to at least get dev mode to run..

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

## Running the application

As this abomination of an application does not work in prod (which is the default for Java code I've heard. Either its used in school or never in actual production), I will just describe, how to start up the app in dev mode.

### Download the repositories

1. First download the blog and navigate to the reactive branch

- ```bash
  git clone git@github.com:janbuettiker/in306-blog.git
  git checkout reactive
  ```

2. Then, download the messaging micro service

- ```bash
  git clone git@github.com:janbuettiker/in306-text-validator.git
  ```

### Start up dev mode

1. First, we need the SQL container, which we will deploy with the default config, because I've got no inspiration.

- ```bash
  docker run --name in306-mysql -p 3306:3306 -e MYSQL_ROOT_PASSWORD=root -e MYSQL_USER=dbuser -e MYSQL_PASSWORD=dbuser -e MYSQL_DATABASE=blogdb -d mysql:latest
  ```

2. Next, start the validator in dev mode (important order to get it to work!)

- ```bash
  cd */in306-text-validator
  ./mvnw quarkus:dev
  ```

3. Lastly, start the blog itself

- ```bash
  cd */in306-blog
  ./mvnw quarkus:dev
  ```
