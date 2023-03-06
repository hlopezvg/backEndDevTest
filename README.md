## Backend Java Test

### Initial requirements
This project was built using JDK v 17


### Implementation

During the project construction we started from a very basic to a more elaborated code. We are going to describe 
what we did and what we accomplished on each project phase

**Phase 1**

1. Basic controller and service, standard Spring Boot one
2. No recoverable no

**Phase 2**
1. Besides, the basic construction, we also try to add more elegant handling on the service
side by implementing a functionl style Either
2. We add a callable annotation on our controller hoping to improve the performance on our tests


**Phase 3**
1. Out of the box, the Callable implementation has not much to offer, we then try to add a more 
meaningful performance, so we add:
   1. Flux object to work with sink and states
   2. We updated the controller with a very basic example for streaming to generate
   a single object per invocation
   3. We also added a retryable and recoverable (the last one just as an example on
   in what we can do later, send it to queue, or notify an external servie, etc.)

      
### Tests results
Our test show some improvements from phase 1 to 3, on phase 2 the performance was worse than the initial 
configuration we used. Here is the most relevant 

#### *Callable*
http_req_blocked...........: **avg=173.66µs** min=1.25µs   med=3.83µs   max=83.48ms  
http_req_sending...........: **avg=29.79µs**  min=4.38µs   med=14.29µs  max=82.08ms  
iteration_duration.........: **avg=510.44ms** min=500.37ms med=502.24ms max=1.13s    

#### *FLUX*
http_req_blocked...........: **avg=48.79µs**  min=990ns    med=3.24µs   max=89.67ms     
http_req_sending...........: **avg=18.34µs**  min=3.7µs    med=11.04µs  max=3.66ms   
iteration_duration.........: **avg=507.18ms** min=500.19ms med=501.73ms max=896.22ms

Overall we were able to reduce at lest 2.5 seconds, improving a bit the performance.


### How to run this project
From command line use the Gradle Wrapper
```
./gradlew bootRun
```

Check if project is alive
```bash
curl  http://localhost:5000/healthcheck -H 'Content-Type: application/json'
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
The idea is to use the base test project as reference

```
git submodule add git@github.com:dalogax/backendDevTest.git original
```

from original folder, execute the step from the readme [dalogax](original/readme.md)
```
docker-compose up --build --force-recreate -d simulado influxdb grafana
docker-compose run --rm k6 run scripts/test.js
```
