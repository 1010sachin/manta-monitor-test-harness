# manta-monitor-test-harness
Test and validate manta-monitor endpoint and its metrics

To run this application you need [Java 1.8](http://www.oracle.com/technetwork/java/javase/downloads/index.html)

Compile and run using the following commands
```
$ $JAVA_HOME/bin/javac TestMantaMonitorEndpoint.java
$ $JAVA_HOME/bin/java TestMantaMonitorEndpoint http://localhost:8090/metrics
```
The application requires one parameter to run, i.e. the complete URL of the manta-monitor endpoint such as 'http://<ip-address:port>/metrics'.

The app will run for 1 minute during which it will perform the following checks multiple times:
1. Check if the provided endpoint can be reached.
2. Check if the all the expected metrics are exposed.
3. Check if all the 'requests' count metrics are getting incremented.

The output will look like:
```
Manta-monitor endpoint http://localhost:8090/metrics is alive.


Writing all the found metrics to manta-monitor-test-harness/manta-monitor-metrics.out at once


Found all the Requests-Get metrics
requests_get_mean
requests_get_95thPercentile
requests_get_99thPercentile
requests_get_75thPercentile
requests_get_50thPercentile
requests_get_count

Found all the Requests-Delete metrics
requests_delete_50thPercentile
requests_delete_99thPercentile
requests_delete_75thPercentile
requests_delete_count
requests_delete_mean
requests_delete_95thPercentile

Found all the Requests-Put metrics
requests_put_75thPercentile
requests_put_mean
requests_put_50thPercentile
requests_put_count
requests_put_99thPercentile
requests_put_95thPercentile

Found all the Retries metrics
retries_count
retries_mean_rate

Start Request-Get count is: 150388
Start Request-Put count is: 2857360
Start Request-Delete count is: 2706916


Manta-monitor endpoint http://localhost:8090/metrics is alive.


Found all the Requests-Get metrics
requests_get_mean
requests_get_95thPercentile
requests_get_99thPercentile
requests_get_75thPercentile
requests_get_50thPercentile
requests_get_count

Found all the Requests-Delete metrics
requests_delete_50thPercentile
requests_delete_99thPercentile
requests_delete_75thPercentile
requests_delete_count
requests_delete_mean
requests_delete_95thPercentile

Found all the Requests-Put metrics
requests_put_75thPercentile
requests_put_mean
requests_put_50thPercentile
requests_put_count
requests_put_99thPercentile
requests_put_95thPercentile

Found all the Retries metrics
retries_count
retries_mean_rate

Request-Get count increased by: 2. Last count was 150388 and current count is 150390
Request-Put count increased by: 18. Last count was 2857360 and current count is 2857378
Request-Delete count increased by: 44. Last count was 2706916 and current count is 2706960


Manta-monitor endpoint http://localhost:8090/metrics is alive.


Found all the Requests-Get metrics
requests_get_mean
requests_get_95thPercentile
requests_get_99thPercentile
requests_get_75thPercentile
requests_get_50thPercentile
requests_get_count

Found all the Requests-Delete metrics
requests_delete_50thPercentile
requests_delete_99thPercentile
requests_delete_75thPercentile
requests_delete_count
requests_delete_mean
requests_delete_95thPercentile

Found all the Requests-Put metrics
requests_put_75thPercentile
requests_put_mean
requests_put_50thPercentile
requests_put_count
requests_put_99thPercentile
requests_put_95thPercentile

Found all the Retries metrics
retries_count
retries_mean_rate

Request-Get count not incremented yet
Request-Put count increased by: 36. Last count was 2857378 and current count is 2857414
Request-Delete count increased by: 24. Last count was 2706960 and current count is 2706984


Manta-monitor endpoint http://localhost:8090/metrics is alive.


Found all the Requests-Get metrics
requests_get_mean
requests_get_95thPercentile
requests_get_99thPercentile
requests_get_75thPercentile
requests_get_50thPercentile
requests_get_count

Found all the Requests-Delete metrics
requests_delete_50thPercentile
requests_delete_99thPercentile
requests_delete_75thPercentile
requests_delete_count
requests_delete_mean
requests_delete_95thPercentile

Found all the Requests-Put metrics
requests_put_75thPercentile
requests_put_mean
requests_put_50thPercentile
requests_put_count
requests_put_99thPercentile
requests_put_95thPercentile

Found all the Retries metrics
retries_count
retries_mean_rate

Request-Get count increased by: 3. Last count was 150390 and current count is 150393
Request-Put count increased by: 40. Last count was 2857414 and current count is 2857454
Request-Delete count increased by: 17. Last count was 2706984 and current count is 2707001


Manta-monitor endpoint http://localhost:8090/metrics is alive.


Found all the Requests-Get metrics
requests_get_mean
requests_get_95thPercentile
requests_get_99thPercentile
requests_get_75thPercentile
requests_get_50thPercentile
requests_get_count

Found all the Requests-Delete metrics
requests_delete_50thPercentile
requests_delete_99thPercentile
requests_delete_75thPercentile
requests_delete_count
requests_delete_mean
requests_delete_95thPercentile

Found all the Requests-Put metrics
requests_put_75thPercentile
requests_put_mean
requests_put_50thPercentile
requests_put_count
requests_put_99thPercentile
requests_put_95thPercentile

Found all the Retries metrics
retries_count
retries_mean_rate

Request-Get count increased by: 2. Last count was 150393 and current count is 150395
Request-Put count increased by: 17. Last count was 2857454 and current count is 2857471
Request-Delete count increased by: 46. Last count was 2707001 and current count is 2707047


Manta-monitor endpoint http://localhost:8090/metrics is alive.


Found all the Requests-Get metrics
requests_get_mean
requests_get_95thPercentile
requests_get_99thPercentile
requests_get_75thPercentile
requests_get_50thPercentile
requests_get_count

Found all the Requests-Delete metrics
requests_delete_50thPercentile
requests_delete_99thPercentile
requests_delete_75thPercentile
requests_delete_count
requests_delete_mean
requests_delete_95thPercentile

Found all the Requests-Put metrics
requests_put_75thPercentile
requests_put_mean
requests_put_50thPercentile
requests_put_count
requests_put_99thPercentile
requests_put_95thPercentile

Found all the Retries metrics
retries_count
retries_mean_rate

Request-Get count not incremented yet
Request-Put count increased by: 32. Last count was 2857471 and current count is 2857503
Request-Delete count increased by: 27. Last count was 2707047 and current count is 2707074


Manta-monitor endpoint http://localhost:8090/metrics is alive.


Found all the Requests-Get metrics
requests_get_mean
requests_get_95thPercentile
requests_get_99thPercentile
requests_get_75thPercentile
requests_get_50thPercentile
requests_get_count

Found all the Requests-Delete metrics
requests_delete_50thPercentile
requests_delete_99thPercentile
requests_delete_75thPercentile
requests_delete_count
requests_delete_mean
requests_delete_95thPercentile

Found all the Requests-Put metrics
requests_put_75thPercentile
requests_put_mean
requests_put_50thPercentile
requests_put_count
requests_put_99thPercentile
requests_put_95thPercentile

Found all the Retries metrics
retries_count
retries_mean_rate

Request-Get count increased by: 3. Last count was 150395 and current count is 150398
Request-Put count increased by: 44. Last count was 2857503 and current count is 2857547
Request-Delete count increased by: 11. Last count was 2707074 and current count is 2707085


Manta-monitor endpoint http://localhost:8090/metrics is alive.


Found all the Requests-Get metrics
requests_get_mean
requests_get_95thPercentile
requests_get_99thPercentile
requests_get_75thPercentile
requests_get_50thPercentile
requests_get_count

Found all the Requests-Delete metrics
requests_delete_50thPercentile
requests_delete_99thPercentile
requests_delete_75thPercentile
requests_delete_count
requests_delete_mean
requests_delete_95thPercentile

Found all the Requests-Put metrics
requests_put_75thPercentile
requests_put_mean
requests_put_50thPercentile
requests_put_count
requests_put_99thPercentile
requests_put_95thPercentile

Found all the Retries metrics
retries_count
retries_mean_rate

Request-Get count increased by: 2. Last count was 150398 and current count is 150400
Request-Put count increased by: 18. Last count was 2857547 and current count is 2857565
Request-Delete count increased by: 44. Last count was 2707085 and current count is 2707129


Manta-monitor endpoint http://localhost:8090/metrics is alive.


Found all the Requests-Get metrics
requests_get_mean
requests_get_95thPercentile
requests_get_99thPercentile
requests_get_75thPercentile
requests_get_50thPercentile
requests_get_count

Found all the Requests-Delete metrics
requests_delete_50thPercentile
requests_delete_99thPercentile
requests_delete_75thPercentile
requests_delete_count
requests_delete_mean
requests_delete_95thPercentile

Found all the Requests-Put metrics
requests_put_75thPercentile
requests_put_mean
requests_put_50thPercentile
requests_put_count
requests_put_99thPercentile
requests_put_95thPercentile

Found all the Retries metrics
retries_count
retries_mean_rate

Request-Get count not incremented yet
Request-Put count increased by: 27. Last count was 2857565 and current count is 2857592
Request-Delete count increased by: 32. Last count was 2707129 and current count is 2707161


Manta-monitor endpoint http://localhost:8090/metrics is alive.


Found all the Requests-Get metrics
requests_get_mean
requests_get_95thPercentile
requests_get_99thPercentile
requests_get_75thPercentile
requests_get_50thPercentile
requests_get_count

Found all the Requests-Delete metrics
requests_delete_50thPercentile
requests_delete_99thPercentile
requests_delete_75thPercentile
requests_delete_count
requests_delete_mean
requests_delete_95thPercentile

Found all the Requests-Put metrics
requests_put_75thPercentile
requests_put_mean
requests_put_50thPercentile
requests_put_count
requests_put_99thPercentile
requests_put_95thPercentile

Found all the Retries metrics
retries_count
retries_mean_rate

Request-Get count increased by: 3. Last count was 150400 and current count is 150403
Request-Put count increased by: 48. Last count was 2857592 and current count is 2857640
Request-Delete count increased by: 10. Last count was 2707161 and current count is 2707171


Manta-monitor endpoint http://localhost:8090/metrics is alive.


Found all the Requests-Get metrics
requests_get_mean
requests_get_95thPercentile
requests_get_99thPercentile
requests_get_75thPercentile
requests_get_50thPercentile
requests_get_count

Found all the Requests-Delete metrics
requests_delete_50thPercentile
requests_delete_99thPercentile
requests_delete_75thPercentile
requests_delete_count
requests_delete_mean
requests_delete_95thPercentile

Found all the Requests-Put metrics
requests_put_75thPercentile
requests_put_mean
requests_put_50thPercentile
requests_put_count
requests_put_99thPercentile
requests_put_95thPercentile

Found all the Retries metrics
retries_count
retries_mean_rate

Request-Get count increased by: 2. Last count was 150403 and current count is 150405
Request-Put count increased by: 18. Last count was 2857640 and current count is 2857658
Request-Delete count increased by: 43. Last count was 2707171 and current count is 2707214


Manta-monitor endpoint http://localhost:8090/metrics is alive.


Found all the Requests-Get metrics
requests_get_mean
requests_get_95thPercentile
requests_get_99thPercentile
requests_get_75thPercentile
requests_get_50thPercentile
requests_get_count

Found all the Requests-Delete metrics
requests_delete_50thPercentile
requests_delete_99thPercentile
requests_delete_75thPercentile
requests_delete_count
requests_delete_mean
requests_delete_95thPercentile

Found all the Requests-Put metrics
requests_put_75thPercentile
requests_put_mean
requests_put_50thPercentile
requests_put_count
requests_put_99thPercentile
requests_put_95thPercentile

Found all the Retries metrics
retries_count
retries_mean_rate

Request-Get count not incremented yet
Request-Put count increased by: 24. Last count was 2857658 and current count is 2857682
Request-Delete count increased by: 33. Last count was 2707214 and current count is 2707247


Manta-monitor endpoint http://localhost:8090/metrics is alive.


Found all the Requests-Get metrics
requests_get_mean
requests_get_95thPercentile
requests_get_99thPercentile
requests_get_75thPercentile
requests_get_50thPercentile
requests_get_count

Found all the Requests-Delete metrics
requests_delete_50thPercentile
requests_delete_99thPercentile
requests_delete_75thPercentile
requests_delete_count
requests_delete_mean
requests_delete_95thPercentile

Found all the Requests-Put metrics
requests_put_75thPercentile
requests_put_mean
requests_put_50thPercentile
requests_put_count
requests_put_99thPercentile
requests_put_95thPercentile

Found all the Retries metrics
retries_count
retries_mean_rate

Request-Get count increased by: 1. Last count was 150405 and current count is 150406
Request-Put count increased by: 46. Last count was 2857682 and current count is 2857728
Request-Delete count increased by: 11. Last count was 2707247 and current count is 2707258


Manta-monitor endpoint http://localhost:8090/metrics is alive.


Found all the Requests-Get metrics
requests_get_mean
requests_get_95thPercentile
requests_get_99thPercentile
requests_get_75thPercentile
requests_get_50thPercentile
requests_get_count

Found all the Requests-Delete metrics
requests_delete_50thPercentile
requests_delete_99thPercentile
requests_delete_75thPercentile
requests_delete_count
requests_delete_mean
requests_delete_95thPercentile

Found all the Requests-Put metrics
requests_put_75thPercentile
requests_put_mean
requests_put_50thPercentile
requests_put_count
requests_put_99thPercentile
requests_put_95thPercentile

Found all the Retries metrics
retries_count
retries_mean_rate

Request-Get count increased by: 3. Last count was 150406 and current count is 150409
Request-Put count increased by: 24. Last count was 2857728 and current count is 2857752
Request-Delete count increased by: 34. Last count was 2707258 and current count is 2707292


Manta-monitor endpoint http://localhost:8090/metrics is alive.


Found all the Requests-Get metrics
requests_get_mean
requests_get_95thPercentile
requests_get_99thPercentile
requests_get_75thPercentile
requests_get_50thPercentile
requests_get_count

Found all the Requests-Delete metrics
requests_delete_50thPercentile
requests_delete_99thPercentile
requests_delete_75thPercentile
requests_delete_count
requests_delete_mean
requests_delete_95thPercentile

Found all the Requests-Put metrics
requests_put_75thPercentile
requests_put_mean
requests_put_50thPercentile
requests_put_count
requests_put_99thPercentile
requests_put_95thPercentile

Found all the Retries metrics
retries_count
retries_mean_rate

Request-Get count increased by: 1. Last count was 150409 and current count is 150410
Request-Put count increased by: 21. Last count was 2857752 and current count is 2857773
Request-Delete count increased by: 41. Last count was 2707292 and current count is 2707333


Manta-monitor endpoint http://localhost:8090/metrics is alive.


Found all the Requests-Get metrics
requests_get_mean
requests_get_95thPercentile
requests_get_99thPercentile
requests_get_75thPercentile
requests_get_50thPercentile
requests_get_count

Found all the Requests-Delete metrics
requests_delete_50thPercentile
requests_delete_99thPercentile
requests_delete_75thPercentile
requests_delete_count
requests_delete_mean
requests_delete_95thPercentile

Found all the Requests-Put metrics
requests_put_75thPercentile
requests_put_mean
requests_put_50thPercentile
requests_put_count
requests_put_99thPercentile
requests_put_95thPercentile

Found all the Retries metrics
retries_count
retries_mean_rate

Request-Get count increased by: 1. Last count was 150410 and current count is 150411
Request-Put count increased by: 44. Last count was 2857773 and current count is 2857817
Request-Delete count increased by: 13. Last count was 2707333 and current count is 2707346


Manta-monitor endpoint http://localhost:8090/metrics is alive.


Found all the Requests-Get metrics
requests_get_mean
requests_get_95thPercentile
requests_get_99thPercentile
requests_get_75thPercentile
requests_get_50thPercentile
requests_get_count

Found all the Requests-Delete metrics
requests_delete_50thPercentile
requests_delete_99thPercentile
requests_delete_75thPercentile
requests_delete_count
requests_delete_mean
requests_delete_95thPercentile

Found all the Requests-Put metrics
requests_put_75thPercentile
requests_put_mean
requests_put_50thPercentile
requests_put_count
requests_put_99thPercentile
requests_put_95thPercentile

Found all the Retries metrics
retries_count
retries_mean_rate

Request-Get count increased by: 2. Last count was 150411 and current count is 150413
Request-Put count increased by: 27. Last count was 2857817 and current count is 2857844
Request-Delete count increased by: 33. Last count was 2707346 and current count is 2707379


Manta-monitor endpoint http://localhost:8090/metrics is alive.


Found all the Requests-Get metrics
requests_get_mean
requests_get_95thPercentile
requests_get_99thPercentile
requests_get_75thPercentile
requests_get_50thPercentile
requests_get_count

Found all the Requests-Delete metrics
requests_delete_50thPercentile
requests_delete_99thPercentile
requests_delete_75thPercentile
requests_delete_count
requests_delete_mean
requests_delete_95thPercentile

Found all the Requests-Put metrics
requests_put_75thPercentile
requests_put_mean
requests_put_50thPercentile
requests_put_count
requests_put_99thPercentile
requests_put_95thPercentile

Found all the Retries metrics
retries_count
retries_mean_rate

Request-Get count increased by: 2. Last count was 150413 and current count is 150415
Request-Put count increased by: 20. Last count was 2857844 and current count is 2857864
Request-Delete count increased by: 41. Last count was 2707379 and current count is 2707420


Manta-monitor endpoint http://localhost:8090/metrics is alive.


Found all the Requests-Get metrics
requests_get_mean
requests_get_95thPercentile
requests_get_99thPercentile
requests_get_75thPercentile
requests_get_50thPercentile
requests_get_count

Found all the Requests-Delete metrics
requests_delete_50thPercentile
requests_delete_99thPercentile
requests_delete_75thPercentile
requests_delete_count
requests_delete_mean
requests_delete_95thPercentile

Found all the Requests-Put metrics
requests_put_75thPercentile
requests_put_mean
requests_put_50thPercentile
requests_put_count
requests_put_99thPercentile
requests_put_95thPercentile

Found all the Retries metrics
retries_count
retries_mean_rate

Request-Get count not incremented yet
Request-Put count increased by: 41. Last count was 2857864 and current count is 2857905
Request-Delete count increased by: 14. Last count was 2707420 and current count is 2707434


Manta-monitor endpoint http://localhost:8090/metrics is alive.


Found all the Requests-Get metrics
requests_get_mean
requests_get_95thPercentile
requests_get_99thPercentile
requests_get_75thPercentile
requests_get_50thPercentile
requests_get_count

Found all the Requests-Delete metrics
requests_delete_50thPercentile
requests_delete_99thPercentile
requests_delete_75thPercentile
requests_delete_count
requests_delete_mean
requests_delete_95thPercentile

Found all the Requests-Put metrics
requests_put_75thPercentile
requests_put_mean
requests_put_50thPercentile
requests_put_count
requests_put_99thPercentile
requests_put_95thPercentile

Found all the Retries metrics
retries_count
retries_mean_rate

Request-Get count increased by: 3. Last count was 150415 and current count is 150418
Request-Put count increased by: 30. Last count was 2857905 and current count is 2857935
Request-Delete count increased by: 31. Last count was 2707434 and current count is 2707465


Manta-monitor endpoint http://localhost:8090/metrics is alive.


Found all the Requests-Get metrics
requests_get_mean
requests_get_95thPercentile
requests_get_99thPercentile
requests_get_75thPercentile
requests_get_50thPercentile
requests_get_count

Found all the Requests-Delete metrics
requests_delete_50thPercentile
requests_delete_99thPercentile
requests_delete_75thPercentile
requests_delete_count
requests_delete_mean
requests_delete_95thPercentile

Found all the Requests-Put metrics
requests_put_75thPercentile
requests_put_mean
requests_put_50thPercentile
requests_put_count
requests_put_99thPercentile
requests_put_95thPercentile

Found all the Retries metrics
retries_count
retries_mean_rate

Request-Get count increased by: 2. Last count was 150418 and current count is 150420
Request-Put count increased by: 21. Last count was 2857935 and current count is 2857956
Request-Delete count increased by: 42. Last count was 2707465 and current count is 2707507



Process finished with exit code 0
```
