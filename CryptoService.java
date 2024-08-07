package org.example.cryptodata;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.concurrent.Service;
import javafx.concurrent.Task;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class CryptoService extends Service<CryptoCurrency> {

    private static final String API_URL = "https://cryptocurrency-markets.p.rapidapi.com/v1/crypto/holders";
    private static final String API_KEY = "a2536e39b8msha4f2db97e703ca3p1be905jsn32b36467c4a2";
    private static final String API_HOST = "cryptocurrency-markets.p.rapidapi.com";
    private String searchKey;

    public CryptoService(String searchKey) {
        this.searchKey = searchKey;
    }

    @Override
    protected Task<CryptoCurrency> createTask() {
        return new Task<>() {
            @Override
            protected CryptoCurrency call() {
                try {
                    return fetchCryptoCurrencyData();
                } catch (IOException | InterruptedException e) {
                    throw new RuntimeException("Exception: " + e.getMessage(), e);
                }
            }
        };
    }

    private CryptoCurrency fetchCryptoCurrencyData() throws IOException, InterruptedException {
        CryptoCurrency cryptoCurrency = null;

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(API_URL + "?key=" + searchKey))
                .header("x-rapidapi-key", API_KEY)
                .header("x-rapidapi-host", API_HOST)
                .GET()
                .build();

        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200) { // HTTP_OK
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(response.body());
            JsonNode metaNode = root.path("meta");

            String key = metaNode.path("key").asText();
            int holderCount = metaNode.path("holderCount").asInt();
            int dailyActive = metaNode.path("dailyActive").asInt();
            int total = metaNode.path("total").asInt();

            cryptoCurrency = new CryptoCurrency(key, holderCount, dailyActive, total);
        } else {
            throw new IOException("Error: " + response.body());
        }

        return cryptoCurrency;
    }
}
