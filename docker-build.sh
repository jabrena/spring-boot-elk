mvn clean package
cd comment-service
docker build -t docker.io/jab/comment-service:0.1.0-SNAPSHOT .
cd ..
cd post-service
docker build -t docker.io/jab/post-service:0.1.0-SNAPSHOT .
