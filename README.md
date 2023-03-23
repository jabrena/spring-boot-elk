# Spring Boot ELK

```
docker-compose up -d
docker-compose ps
docker-compose stop

./mvnw clean spring-boot:build-image

docker build -t jab/demo .

docker run --rm --name demo-log-api -p 8080:8080 \
  --network=spring-boot-elk_default \
  docker.io/library/spring-boot-elk:0.1.0-SNAPSHOT
```

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
  