# manta-monitor-test-harness
Test and validate manta-monitor endpoint and its metrics

To build and run this application you need 
* [Java 1.8](http://www.oracle.com/technetwork/java/javase/downloads/index.html)
* [Maven 3.1.x](https://maven.apache.org/)

## Build
Checkout the project from github then, from command line, go to the project source directory
```
$ mvn clean install
```
The above command will produce 2 jar files in the target directory as:
* manta-monitor-test-harness-1.0-SNAPSHOT.jar
* manta-monitor-test-harness-1.0-SNAPSHOT-jar-with-dependencies.jar

We will use the manta-monitor-test-harness-1.0-SNAPSHOT-jar-with-dependencies.jar to run the app.

## Run the app to validate http endpoint
From the project source directory
```
$ java -jar target/manta-monitor-test-harness-1.0-SNAPSHOT-jar-with-dependencies.jar "http://localhost:8090/metrics"

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
requests_get_FifteenMinuteRate
requests_get_95thPercentile
requests_get_OneMinuteRate
requests_get_99thPercentile
requests_get_75thPercentile
requests_get_FiveMinuteRate
requests_get_count
requests_get_50thPercentile

Found all the Requests-Delete metrics
requests_delete_50thPercentile
requests_delete_99thPercentile
requests_delete_FiveMinuteRate
requests_delete_75thPercentile
requests_delete_OneMinuteRate
requests_delete_count
requests_delete_mean
requests_delete_FifteenMinuteRate
requests_delete_95thPercentile

Found all the Requests-Put metrics
requests_put_75thPercentile
requests_put_FiveMinuteRate
requests_put_OneMinuteRate
requests_put_mean
requests_put_50thPercentile
requests_put_count
requests_put_99thPercentile
requests_put_95thPercentile
requests_put_FifteenMinuteRate

Found Put Request Latency metrics
manta_monitor_put_request_latency_seconds_FileUploadGetDeleteChain_bucket{le="0.25",}
manta_monitor_put_request_latency_seconds_FileUploadGetDeleteChain_bucket{le="0.1",}
manta_monitor_put_request_latency_seconds_FileUploadGetDeleteChain_sum
manta_monitor_put_request_latency_seconds_FileUploadGetDeleteChain_bucket{le="0.75",}
manta_monitor_put_request_latency_seconds_FileUploadGetDeleteChain_count
manta_monitor_put_request_latency_seconds_FileUploadGetDeleteChain_bucket{le="0.5",}
manta_monitor_put_request_latency_seconds_FileUploadGetDeleteChain_bucket{le="0.05",}
manta_monitor_put_request_latency_seconds_FileUploadGetDeleteChain_bucket{le="+Inf",}
manta_monitor_put_request_latency_seconds_FileUploadGetDeleteChain_bucket{le="0.01",}
manta_monitor_put_request_latency_seconds_FileUploadGetDeleteChain_bucket{le="0.005",}
manta_monitor_put_request_latency_seconds_FileUploadGetDeleteChain_bucket{le="2.5",}
manta_monitor_put_request_latency_seconds_FileUploadGetDeleteChain_bucket{le="1.0",}
manta_monitor_put_request_latency_seconds_FileUploadGetDeleteChain_bucket{le="0.075",}
manta_monitor_put_request_latency_seconds_FileUploadGetDeleteChain_bucket{le="5.0",}
manta_monitor_put_request_latency_seconds_FileUploadGetDeleteChain_bucket{le="0.025",}
manta_monitor_put_request_latency_seconds_FileUploadGetDeleteChain_bucket{le="7.5",}
manta_monitor_put_request_latency_seconds_FileUploadGetDeleteChain_bucket{le="10.0",}

Found all the Retries metrics
retries_count
retries_mean_rate

Found Put Request Latency metrics
exceptions_no_http_response_FifteenMinuteRate
exceptions_connection_closed_FifteenMinuteRate
exceptions_socket_time_out_OneMinuteRate
exceptions_socket_time_out_FiveMinuteRate
exceptions_connection_closed_FiveMinuteRate
exceptions_socket_time_out_count
exceptions_no_http_response_count
exceptions_connection_closed_OneMinuteRate
exceptions_no_http_response_FiveMinuteRate
exceptions_connection_closed_count
exceptions_no_http_response_OneMinuteRate
exceptions_socket_time_out_FifteenMinuteRate

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

Process finished with exit code 0
```

OR (If testing for https endpoint by providing the truststore path and password)

```
The provided certificate and key is accepted by the manta-monitor server at https://localhost:8443/metrics
Writing all the found metrics to /Users/sachingupta/Documents/Joyent/java-manta-monitor/manta-monitor-test-harness/manta-monitor-metrics.out at once


Found all the Requests-Get metrics
requests_get_mean
requests_get_FifteenMinuteRate
requests_get_95thPercentile
requests_get_OneMinuteRate
requests_get_99thPercentile
requests_get_75thPercentile
requests_get_FiveMinuteRate
requests_get_count
requests_get_50thPercentile

Found all the Requests-Delete metrics
requests_delete_50thPercentile
requests_delete_99thPercentile
requests_delete_FiveMinuteRate
requests_delete_75thPercentile
requests_delete_OneMinuteRate
requests_delete_count
requests_delete_mean
requests_delete_FifteenMinuteRate
requests_delete_95thPercentile

Found all the Requests-Put metrics
requests_put_75thPercentile
requests_put_FiveMinuteRate
requests_put_OneMinuteRate
requests_put_mean
requests_put_50thPercentile
requests_put_count
requests_put_99thPercentile
requests_put_95thPercentile
requests_put_FifteenMinuteRate

Found Put Request Latency metrics
manta_monitor_put_request_latency_seconds_FileUploadGetDeleteChain_bucket{le="0.25",}
manta_monitor_put_request_latency_seconds_FileUploadGetDeleteChain_bucket{le="0.1",}
manta_monitor_put_request_latency_seconds_FileUploadGetDeleteChain_sum
manta_monitor_put_request_latency_seconds_FileUploadGetDeleteChain_bucket{le="0.75",}
manta_monitor_put_request_latency_seconds_FileUploadGetDeleteChain_count
manta_monitor_put_request_latency_seconds_FileUploadGetDeleteChain_bucket{le="0.5",}
manta_monitor_put_request_latency_seconds_FileUploadGetDeleteChain_bucket{le="0.05",}
manta_monitor_put_request_latency_seconds_FileUploadGetDeleteChain_bucket{le="+Inf",}
manta_monitor_put_request_latency_seconds_FileUploadGetDeleteChain_bucket{le="0.01",}
manta_monitor_put_request_latency_seconds_FileUploadGetDeleteChain_bucket{le="0.005",}
manta_monitor_put_request_latency_seconds_FileUploadGetDeleteChain_bucket{le="2.5",}
manta_monitor_put_request_latency_seconds_FileUploadGetDeleteChain_bucket{le="1.0",}
manta_monitor_put_request_latency_seconds_FileUploadGetDeleteChain_bucket{le="0.075",}
manta_monitor_put_request_latency_seconds_FileUploadGetDeleteChain_bucket{le="5.0",}
manta_monitor_put_request_latency_seconds_FileUploadGetDeleteChain_bucket{le="0.025",}
manta_monitor_put_request_latency_seconds_FileUploadGetDeleteChain_bucket{le="7.5",}
manta_monitor_put_request_latency_seconds_FileUploadGetDeleteChain_bucket{le="10.0",}

Found all the Retries metrics
retries_count
retries_mean_rate

Found Exceptions metrics
exceptions_no_http_response_FifteenMinuteRate
exceptions_connection_closed_FifteenMinuteRate
exceptions_socket_time_out_OneMinuteRate
exceptions_socket_time_out_FiveMinuteRate
exceptions_connection_closed_FiveMinuteRate
exceptions_socket_time_out_count
exceptions_no_http_response_count
exceptions_connection_closed_OneMinuteRate
exceptions_no_http_response_FiveMinuteRate
exceptions_connection_closed_count
exceptions_no_http_response_OneMinuteRate
exceptions_socket_time_out_FifteenMinuteRate

Start Request-Get count is: 5
Start Request-Put count is: 35
Start Request-Delete count is: 19

```

## Run the app to validate https endpoint.
For this, you will require the following three things before you can start the app:
* A valid manta-monitor https endpoint. For eg: https://localhost:8443/metrics
* A keystore in the PKCS12, which is added to the manta-monitor truststore. Refer [here](https://github.com/joyent/manta-monitor#run)
  for more details.
* A password for the above keystore (manta-monitor truststore).

```
java -jar target/manta-monitor-test-harness-1.0-SNAPSHOT-jar-with-dependencies.jar "https://localhost:8443/metrics" "keystore" "Password"
```

Replace the keystore and password with the absolute path to the keystore and the password of the keystore respectively.