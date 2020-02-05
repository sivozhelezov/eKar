# Test APp


## Running Locally

Make sure you have Java and Maven installed. 

```sh
$ git clone https://github.com/sivozhelezov/eKar.git
$ mvn clean install
$ start docker with db: docker-compose -f ./db/docker-compose.yml up -d
$ Running in dev mode: `mvn spring-boot:run`
```

API doc is available at [/swagger-ui/index.html](http://localhost:8181/swagger-ui/index.html)