package com.joyent.manta;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.File;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.KeyStore;
import java.security.NoSuchAlgorithmException;
import java.security.KeyStoreException;
import java.security.KeyManagementException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.util.HashMap;
import java.util.Set;
import java.util.TreeSet;
import java.util.HashSet;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.util.EntityUtils;

public class App {
    private static String URL_STRING;
    private static final String OUTPUT_FILE_PATH = "manta-monitor-metrics.out";
    private static volatile double lastGetRequestCountValue;
    private static volatile double lastPutRequestCountValue;
    private static volatile double lastDeleteRequestCountValue;
    private static final AtomicBoolean fileWriteStatus = new AtomicBoolean(false);
    private static final HostnameVerifier nullHostNameVerifier = new NullHostNameVerifier();

    public static void main (String args[]) throws InterruptedException {
        if (args.length == 0) {
            System.err.println("TestMantaMonitorEndPoint requires one parameter. "
                    + "The endpoint url where manta-monitor is exposing its metrics.");

            System.exit(1);
        }
        URL_STRING = args[0];
        final long appDuration = 60000;
        ScheduledExecutorService ses= Executors.newScheduledThreadPool(1);
        try {
            URL url = new URL(URL_STRING);
            if (url.getProtocol().equals("https")) {
                if (args.length < 3) {
                    System.err.println("manta-monitor-test-harness requires the arguments for "
                            + "the path to the PKCS12 keystore and the password to access the keystore"
                            + "in order to test the specified https endpoint.");

                    System.exit(1);
                }
                String sClientCertPath = args[1];
                String sPasswordCert = args[2];
                KeyStore keyStore = createKeystore(sClientCertPath, sPasswordCert);
                SSLContext sslContext = createCustomSSLContext(keyStore,
                        sPasswordCert, sClientCertPath);
                HttpClient httpClient = createHttpClient(sslContext);
                HttpGet httpGet = new HttpGet(URL_STRING);
                ses.scheduleAtFixedRate(new Runnable() {
                    @Override
                    public void run() {
                        verifyHTTPSCon(httpClient, httpGet);
                    }
                }, 0, 3, TimeUnit.SECONDS);
                Thread.sleep(appDuration);
            } else {
                ses.scheduleAtFixedRate(new Runnable() {
                    @Override
                    public void run() {
                        verifyHTTPCon(url, OUTPUT_FILE_PATH);
                    }
                }, 0, 3, TimeUnit.SECONDS);
                Thread.sleep(appDuration);
            }
        } catch(MalformedURLException mue){
            String message = String.format("Failed to create URL from the string %s.", URL_STRING);
            System.err.println(message);
            mue.printStackTrace();
        } finally {
            ses.shutdown();
        }

    }

    private static void verifyHTTPSCon(HttpClient httpClient, HttpGet httpGet) {
        try {
            HttpResponse response = httpClient.execute(httpGet);
            int responseCode = response.getStatusLine().getStatusCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                String message = String.format("The provided certificate and key is accepted "
                + "by the manta-monitor server at %s", URL_STRING);
                System.out.println(message);
                HttpEntity httpEntity = response.getEntity();
                String httpsResponse = null;
                if (httpEntity != null) {
                    httpsResponse = EntityUtils.toString(httpEntity, "utf-8");
                    BufferedReader in = new BufferedReader(new StringReader(httpsResponse));
                    HashMap<String, Double> metrics = addMetricsToMap(in);
                    if (fileWriteStatus.compareAndSet(false, true)) {
                        String msg = String.format("Writing all the found metrics to %s at once",
                                new File(OUTPUT_FILE_PATH).getAbsolutePath());
                        System.out.println(msg);
                        System.out.println(System.lineSeparator());
                        addMetricsToFile(new TreeSet<>(metrics.keySet()), OUTPUT_FILE_PATH);
                    }
                    checkGetRequestMetrics(metrics);
                    checkDeleteRequestMetrics(metrics);
                    checkPutRequestMetrics(metrics);
                    checkRetriesMetrics(metrics);
                    checkRequestCounters(metrics);
                    System.out.println(System.lineSeparator());
                } else {
                    String error = String.format("Endpoint %s is not available", URL_STRING);
                    System.err.println(error);
                    System.exit(1);
                }
            }
        }
        catch(IOException io) {
            String message = String.format("Failed to add the https response to metrics map "
            + "in order to write the metrics to file %s", OUTPUT_FILE_PATH);
            throw new RuntimeException(message, io);
        }
    }

    private static void verifyHTTPCon(URL url, String outputFilePath) {
        try {
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            int responseCode = con.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                String message = String.format("Manta-monitor endpoint %s is alive.", URL_STRING);
                System.out.println(message);
                System.out.println(System.lineSeparator());
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(con.getInputStream()));
                HashMap<String, Double> metrics = addMetricsToMap(in);
                if (fileWriteStatus.compareAndSet(false, true)) {
                    String msg = String.format("Writing all the found metrics to %s at once",
                            new File(outputFilePath).getAbsolutePath());
                    System.out.println(msg);
                    System.out.println(System.lineSeparator());
                    addMetricsToFile(new TreeSet<>(metrics.keySet()), outputFilePath);
                }
                checkGetRequestMetrics(metrics);
                checkDeleteRequestMetrics(metrics);
                checkPutRequestMetrics(metrics);
                checkRetriesMetrics(metrics);
                checkRequestCounters(metrics);
                System.out.println(System.lineSeparator());
            } else {
                String message = String.format("Endpoint %s is not available",
                        URL_STRING);
                System.err.println(message);
                System.exit(1);
            }
        } catch (Exception e) {
            String message = String.format("Failed to connect to the manta-monitor endpoint: %s",
                    URL_STRING);
            System.err.println(message);
            e.printStackTrace();
            System.exit(1);
        }

    }

    private static KeyStore createKeystore(String sClientCertPath, String sPasswordCert) {
        KeyStore keyStore;
        FileInputStream fis = null;
        try {
            keyStore = KeyStore.getInstance("pkcs12");
        } catch(KeyStoreException kse) {
            throw new RuntimeException("Failed to get the keystore instance", kse);
        }

        try {
            fis = new FileInputStream(sClientCertPath);
            char[] password = sPasswordCert.toCharArray();
            keyStore.load(fis, password);
        } catch(NoSuchAlgorithmException nsa) {
            String message = String.format("Failed to load the keystore with the given keystore path: %s "
                    + "and keystore password: %s", sClientCertPath, sPasswordCert);
            throw new IllegalArgumentException(message, nsa);
        } catch(CertificateException ce) {
            String message = String.format("Failed to load the certificate from the keystore: %s",
                    sClientCertPath);
            throw new RuntimeException(message, ce);
        } catch(IOException io) {
            String message = String.format("Failed to load the keystore with the given keystore path: %s "
                    + "and keystore password: %s", sClientCertPath, sPasswordCert);
            throw new IllegalArgumentException(message, io);
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch(IOException ie) {
                    System.out.println(ie.getMessage());
                }
            }
        }
        return keyStore;
    }

    private static SSLContext createCustomSSLContext(KeyStore keyStore,
                                                     String sPasswordCert,
                                                     String sClientCertPath) {
        SSLContext sslContext;
        try {
             sslContext = SSLContexts.custom()
                    .loadKeyMaterial(keyStore, sPasswordCert.toCharArray())
                    .loadTrustMaterial(null, new TrustSelfSignedStrategy())
                    .build();
        } catch(NoSuchAlgorithmException nsa) {
            String message = String.format("Failed to load the keystore with the given keystore path: %s "
                    + "and keystore password: %s", sClientCertPath, sPasswordCert);
            throw new IllegalArgumentException(message, nsa);
        } catch(KeyStoreException | UnrecoverableKeyException sslContextException) {
            String message = String.format("Failed to build the SSLContext from the keystore: %s",
                    sClientCertPath);
            throw new RuntimeException(message, sslContextException);

        } catch(KeyManagementException kme) {
            throw new RuntimeException(kme);

        }

        return sslContext;
    }

    private static HttpClient createHttpClient (SSLContext sslContext) {
        return HttpClients.custom()
                .setSSLContext(sslContext)
                .setSSLHostnameVerifier(nullHostNameVerifier)
                .build();
    }

    private static HashMap<String, Double> addMetricsToMap(BufferedReader in) throws IOException {
        String readLine;
        HashMap<String, Double> metrics = new HashMap<>();
        while ((readLine = in .readLine()) != null) {
            if (!readLine.startsWith("#") &&
                    (readLine.startsWith("requests")
                            || readLine.startsWith("retries")
                            || readLine.startsWith("manta"))) {
                String[] metric = readLine.split(" ");
                metrics.put(metric[0], Double.parseDouble(metric[1]));
            }
        } in .close();

        return metrics;
    }

    private static void addMetricsToFile(Set<String> metricsKeySet, String outputFilePath) throws IOException{
        if (outputFilePath.isEmpty()) {
            String message = String.format("Path %s to write the manta-monitor-metrics-file cannot be empty",
                    outputFilePath);
            System.err.println(message);
            System.exit(1);
        }
        BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilePath, false));

        for (String key : metricsKeySet) {
            writer.write(key);
            writer.newLine();
        }

        writer.close();
    }

    private static void checkGetRequestMetrics(HashMap<String, Double> metrics) {
        Set<String> getMetricSet = collectRequestMetricsSet(metrics.keySet(), "get");
        if (getMetricSet.size() == 9) {
            StringBuilder getMetrics = new StringBuilder("Found all the Requests-Get metrics");
            getMetrics.append(System.lineSeparator());
            for (String metric : getMetricSet) {
                getMetrics.append(metric).append(System.lineSeparator());
            }
            System.out.println(getMetrics);
        } else {
            String message = String.format("Expected %d metrics for Requests-Get but found %d",
                    9, getMetricSet.size());
            System.err.println(message);
        }
    }

    private static void checkDeleteRequestMetrics(HashMap<String, Double> metrics) {
        Set<String> deleteMetricSet = collectRequestMetricsSet(metrics.keySet(), "delete");
        if (deleteMetricSet.size() == 9) {
            StringBuilder deleteMetrics = new StringBuilder("Found all the Requests-Delete metrics");
            deleteMetrics.append(System.lineSeparator());
            for (String metric : deleteMetricSet) {
                deleteMetrics.append(metric).append(System.lineSeparator());
            }
            System.out.println(deleteMetrics);
        } else {
            String message = String.format("Expected %d metrics for Requests-Delete but found %d",
                    9, deleteMetricSet.size());
            System.err.println(message);
        }
    }

    private static void checkPutRequestMetrics(HashMap<String, Double> metrics) {
        Set<String> putMetricSet = collectRequestMetricsSet(metrics.keySet(), "requests_put");
        if (putMetricSet.size() == 9) {
            StringBuilder putMetrics = new StringBuilder("Found all the Requests-Put metrics");
            putMetrics.append(System.lineSeparator());
            for (String metric : putMetricSet) {
                putMetrics.append(metric).append(System.lineSeparator());
            }
            System.out.println(putMetrics);
        } else {
            String message = String.format("Expected %d metrics for Requests-Put but found %d",
                    9, putMetricSet.size());
            System.err.println(message);
        }
    }

    private static void checkRetriesMetrics(HashMap<String, Double> metrics) {
        Set<String> retriesMetricSet = collectRequestMetricsSet(metrics.keySet(), "retries");
        if (retriesMetricSet.size() == 2) {
            StringBuilder putMetrics = new StringBuilder("Found all the Retries metrics");
            putMetrics.append(System.lineSeparator());
            for (String metric : retriesMetricSet) {
                putMetrics.append(metric).append(System.lineSeparator());
            }
            System.out.println(putMetrics);
        } else {
            String message = String.format("Expected %d metrics for Requests-Put but found %d",
                    7, retriesMetricSet.size());
            System.err.println(message);
        }
    }

    private static Set<String> collectRequestMetricsSet(Set<String> keySet, String requestMethod) {
        Set<String> requestMetricsSet = new HashSet<>();
        for (String key : keySet) {
            if (key.contains(requestMethod)) {
                requestMetricsSet.add(key);
            }
        }
        return requestMetricsSet;
    }

    private static void checkRequestCounters(HashMap<String, Double> metrics) {
        if (!metrics.isEmpty()) {
            if (lastGetRequestCountValue == 0) {
                lastGetRequestCountValue = metrics.get("requests_get_count");
                System.out.println("Start Request-Get count is: "+Math.round(lastGetRequestCountValue));
            } else {
                if (Math.abs(metrics.get("requests_get_count") - lastGetRequestCountValue) > 0) {
                    String message = String.format("Request-Get count increased by: %d. " +
                                    "Last count was %d and current count is %d",
                            Math.round(metrics.get("requests_get_count") - lastGetRequestCountValue),
                            Math.round(lastGetRequestCountValue),
                            Math.round(metrics.get("requests_get_count")));
                    System.out.println(message);
                    lastGetRequestCountValue = metrics.get("requests_get_count");
                } else {
                    System.out.println("Request-Get count not incremented yet");
                }
            }

            if (lastPutRequestCountValue == 0) {
                lastPutRequestCountValue = metrics.get("requests_put_count");
                System.out.println("Start Request-Put count is: "
                        + Math.round(lastPutRequestCountValue));
            } else {
                if (Math.abs(metrics.get("requests_put_count") - lastPutRequestCountValue) > 0) {
                    String message = String.format("Request-Put count increased by: %d. " +
                                    "Last count was %d and current count is %d",
                            Math.round(metrics.get("requests_put_count") - lastPutRequestCountValue),
                            Math.round(lastPutRequestCountValue),
                            Math.round(metrics.get("requests_put_count")));
                    System.out.println(message);
                    lastPutRequestCountValue = metrics.get("requests_put_count");
                } else {
                    System.out.println("Request-Put count not incremented yet");
                }
            }

            if (lastDeleteRequestCountValue == 0) {
                lastDeleteRequestCountValue = metrics.get("requests_delete_count");
                System.out.println("Start Request-Delete count is: "
                        + Math.round(lastDeleteRequestCountValue));
            } else {
                if (Math.abs(metrics.get("requests_delete_count") - lastDeleteRequestCountValue) > 0) {
                    String message = String.format("Request-Delete count increased by: %d. " +
                                    "Last count was %d and current count is %d",
                            Math.round(metrics.get("requests_delete_count") - lastDeleteRequestCountValue),
                            Math.round(lastDeleteRequestCountValue),
                            Math.round(metrics.get("requests_delete_count")));
                    System.out.println(message);
                    lastDeleteRequestCountValue = metrics.get("requests_delete_count");
                } else {
                    System.out.println("Request-Delete count not incremented yet");
                }
            }
        } else {
            throw new RuntimeException("Failed to get the counter value for requests metrics");
        }
    }

}