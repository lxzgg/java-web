package com.web.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.security.*;
import java.security.cert.CertificateException;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;


class HttpClientTest {
    private static ObjectMapper mapper = new ObjectMapper();

    @Test
    void get() throws IOException, InterruptedException {
        long start = System.currentTimeMillis();

        var client = HttpClient.newHttpClient();
        long endClient = System.currentTimeMillis();

        var request = HttpRequest.newBuilder();
        long endRequest = System.currentTimeMillis();

        var httpRequest = request.uri(URI.create("https://cnodejs.org/api/v1/topic_collect/alsotang")).build();
        long endhttpRequest = System.currentTimeMillis();

        HttpResponse<String> response = client.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        long endresponse = System.currentTimeMillis();

        mapper.readValue(response.body(), HashMap.class);
        long json = System.currentTimeMillis();

        System.out.println("all:" + String.valueOf(json - start));
        System.out.println("Client:" + String.valueOf(endClient - start));
        System.out.println("Request:" + String.valueOf(endRequest - endClient));
        System.out.println("httpRequest:" + String.valueOf(endhttpRequest - endRequest));
        System.out.println("response:" + String.valueOf(endresponse - endhttpRequest));
        System.out.println("json:" + String.valueOf(json - endresponse));
    }

    @Test
    void getAsync() {
        try (InputStream inputStream = this.getClass().getResourceAsStream("/key.keystore")) {
            String password = "123456";

            KeyStore keyStore = KeyStore.getInstance("PKCS12");
            keyStore.load(inputStream, password.toCharArray());

            KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
            keyManagerFactory.init(keyStore, password.toCharArray());

            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(keyManagerFactory.getKeyManagers(), null, new SecureRandom());

            var client = HttpClient.newBuilder().sslContext(sslContext).build();
            var request = HttpRequest.newBuilder();
            var httpRequest = request.uri(URI.create("https://cnodejs.org/api/v1/topic_collect/alsotang")).build();

            var result = client.sendAsync(httpRequest, HttpResponse.BodyHandlers.ofString()).thenApply(HttpResponse::body);
            result.get();
        } catch (NoSuchAlgorithmException | KeyManagementException | CertificateException | ExecutionException | KeyStoreException | InterruptedException | IOException | UnrecoverableKeyException e) {
            System.out.println("error");
        }
    }
}