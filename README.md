## Backend Java Test

### Initial requiremetns and implemenations understandings
This project was built using JDK v 17


### How to run this project
From command line use the Gradle Wrapper
```
./gradlew bootRun
```

#### Want to see the data store on the DB?
http://localhost:5000/h2-console
user:sa
password:sa


#### Want to use curl?

```
curl http://localhost:5000/product/1 -H 'Content-Type: application/json'

curl http://localhost:5000/product/10 -H 'Content-Type: application/json'

curl http://localhost:5000/healthcheck -H 'Content-Type: application/json'
```


### How tests were run
The idea is to use the base test project as reference, so let's include it

```
git submodule add git@github.com:dalogax/backendDevTest.git original
```

from original folder, execute the step from the readme [dalogax](original/readme.md)
```
docker-compose up --build --force-recreate -d simulado influxdb grafana
docker-compose run --rm k6 run scripts/test.js
```
