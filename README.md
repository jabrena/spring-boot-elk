# Spring Boot Observability

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

### Building the applications and creating Docker images

## Running and exploring this demo

- run `./mvnw clean install` to build the two Spring Boot applications (`comment-service` and `post-service`)
- create the dedicated docker network so that services can reach each other and infrastructure with `docker network create observability_poc`
- create the dedicated docker volume so that services can reach each other and infrastructure with `docker volume create observability_poc`

## Grafana infrastructure

- run `docker compose -f grafana/docker-compose.yml up -d` to start grafana in the background
- run the services with `docker compose up --build`
- call `http://localhost:8001/posts/resttemplate/1` or `http://localhost:8001/posts/webclient/1`
- open `http://localhost:3000/explore` in you browser while watching for the logs of the post-service.
- Notice the trace ids in the logs, second value into brackets. In grafana, go to the Explore View, select Tempo in the top left drop-down menu, and enter a Trace ID to see its content.

## Elastic infrastructure

- run `docker compose -f kibana/docker-compose.yml up -d` to start kibana in the background
- run the services with `docker compose up --build`
- call `http://localhost:8001/posts/resttemplate/1` or `http://localhost:8001/posts/webclient/1`
- open `http://localhost:3000/explore` in you browser while watching for the logs of the post-service.





