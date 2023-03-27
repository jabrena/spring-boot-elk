# Spring Boot ELK

For this example, let's consider we are creating a blog engine and we have the following microservices:

- _Post service_: Manages details related to posts.
- _Comment service_: Manages details related to the comments of each post.

Each microservice is a Spring Boot application, exposing a HTTP API. As we intend to focus on _log aggregation_, let's keep it simple when it comes to the services architecture: One service will simply invoke the other service directly.

And, for demonstration purposes, all data handled by the services is stored in memory and only `GET` requests are supported. When a representation of post is requested, the post service will perform a `GET` request to the comment service to get a representation of the comments for that post. The post service will aggregate the results and return a representation of the post with comments to the client.

Let's see how to build the source code, spin up the Docker containers, produce some log data and then visualize the logs in Kibana.

Before starting, ensure you at least Java 11, Maven 3.x and Docker set up. Then clone the [repository][repo] from GitHub:

```bash
git clone https://github.com/francois-poirier/spring-boot-elk-micros.git
```

### Building the applications and creating Docker images

Both post and comment services use the [`dockerfile-maven`][dockerfile-maven] plugin from Spotify to make the Docker build process integrate with the Maven build process. So when we build a Spring Boot artifact, we'll also build a Docker image for it. For more details, check the `Dockerfile` and the `pox.xml` of each service.

To build the Spring Boot applications and their Docker images:

- Change to the `comment-service` folder: `cd comment-service`
- Build the application and create a Docker image: `mvn clean install`
- Change back to the parent folder: `cd ..`

- Change to the `post-service` folder: `cd post-service`
- Build the application and create a Docker image: `mvn clean install`
- Change back to the parent folder: `cd ..`

### Spinning up the containers

In the root folder of our project, where the `docker-compose.yml` resides, spin up the Docker containers running `docker-compose up -d`.

- **Kibana**

  `Kibana` can be accessed at http://localhost:5601

  > **Note**: in order to see demo-logs-api logs in Kibana, you must run the application as Docker container

  _Configuration_

    - Access `Kibana` website
    - Click `Explore on my own`
    - On the main page, click the _"burger"_ menu icon, then click `Discover`
    - Click `Create index pattern` button
    - In the `Create index pattern` form
        - Set `filebeat-*` fot the `Name` field
        - Select `@timestamp` for the `Timestamp field` combo-box
        - Click `Create index pattern` button
    - Click the _"burger"_ menu icon again and then click `Discover` to start performing searches

    - Perform a `GET` request to `http://localhost:8001/posts/1` to generate some log data. Wait a few seconds and then click the _Refresh_ button. You will be able to see logs from the requests. The logs will contain tracing details, such as _trace.trace_id_ and _trace.span id_.

    - In the left-hand side, there's a list of fields available. Hover over the list of fields and an _Add_ button will be shown for each field. Add a few fields such as `app`, `logger`, `level`, `stack`, `msg`, `trace`, `span` and `parent`.

    - Now let's see how to trace a request. Pick a trace id from the logs and, in the filter box, input `trace: "<value>"` where `<value>` is the trace id you want to use as filter criteria. Then click the _Update_ button and you will able to see logs that match that trace id.
 
- To stop the containers, use `docker-compose down`.


