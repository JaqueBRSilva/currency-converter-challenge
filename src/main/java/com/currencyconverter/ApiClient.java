package com.currencyconverter;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Objects;

public class ApiClient {
    private static final String BASE_URL = "https://v6.exchangerate-api.com/v6/";
    private static final String API_KEY = Config.get("api.key");
    private static final String PUBLIC_KEY = Config.get("api.public-key");

    private static final HttpClient client = HttpClient.newHttpClient();

    public static double getExchangeRate(String fromCurrency, String toCurrency) throws Exception {

        String url;

        if (!Objects.equals(API_KEY, "SUA_KEY_AQUI")) {
            url = BASE_URL + API_KEY + "/latest/" + fromCurrency;

        } else if (PUBLIC_KEY != null) {
            url = BASE_URL + PUBLIC_KEY + "/latest/" + fromCurrency;

        } else {
            throw new IllegalStateException("( ! ) :: Nenhuma das chaves de acesso foi encontrada ou configurada\n");
        }

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        HttpResponse<String> response;

        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Erro ao se conectar à API: " + e.getMessage(), e);
        }

        if (response.statusCode() != 200) {
            throw new RuntimeException("Erro na API: HTTP " + response.statusCode());
        }

        JsonObject jsonResponse = JsonParser.parseString(response.body()).getAsJsonObject();
        JsonObject conversionRates = jsonResponse.getAsJsonObject("conversion_rates");

        return conversionRates.get(toCurrency).getAsDouble();
    }
}