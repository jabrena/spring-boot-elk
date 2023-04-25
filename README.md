# Spring Boot ELK

For this example, let's consider we are creating a blog engine and we have the following microservices:

- _Post service_: Manages details related to posts.
- _Comment service_: Manages details related to the comments of each post.

Each microservice is a Spring Boot application, exposing a HTTP API. As we intend to focus on _log aggregation_, let's keep it simple when it comes to the services architecture: One service will simply invoke the other service directly.

And, for demonstration purposes, all data handled by the services is stored in memory and only `GET` requests are supported. When a representation of post is requested, the post service will perform a `GET` request to the comment service to get a representation of the comments for that post. The post service will aggregate the results and return a representation of the post with comments to the client.

Let's see how to build the source code, spin up the Docker containers, produce some log data and then visualize the logs in Kibana.

Before starting, ensure you at least Java 17, Maven 3.x and Docker set up. Then clone the repository from GitHub:

```bash
git clone https://github.com/jabrena/spring-boot-elk.git
```

## Building the applications and creating Docker images

Both post and comment services use the docker maven plugin from Jib to make the Docker build process integrate with the Maven build process. So when we build a Spring Boot artifact, we'll also build a Docker image for it.

To build the Spring Boot applications and their Docker images:

in root folder execute: 

```
./docker-cleanup.sh
./docker-build.sh
docker network create shared-volume
docker volume create shared-volume
docker compose up -d
docker stats

curl http://localhost:8001/posts/resttemplate/1
curl http://localhost:8001/posts/webclient/1
curl http://localhost:8001/katakroker

./test-apis.sh

http:localhost:5601

docker compose down
```

## Features

- [Logging](./LOGGING.md)
