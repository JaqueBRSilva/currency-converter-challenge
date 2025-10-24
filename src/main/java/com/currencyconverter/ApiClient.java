package com.currencyconverter;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class ApiClient {
    private static final String API_KEY = Config.get("api.key");
    private static final String BASE_URL = "https://v6.exchangerate-api.com/v6/";

    public static double getExchangeRate(String fromCurrency, String toCurrency) throws Exception {
        if (API_KEY == null || API_KEY.isEmpty()) {
            throw new IllegalStateException("( ! ) :: A 'API_KEY' não foi configurada\n");
        }

        URL url = new URL(BASE_URL + API_KEY + "/latest/" + fromCurrency);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        if (connection.getResponseCode() != 200) {
            throw new RuntimeException("Erro na API: " + connection.getResponseCode());
        }

        InputStreamReader reader = new InputStreamReader(connection.getInputStream());
        JsonObject jsonResponse = JsonParser.parseReader(reader).getAsJsonObject();

        reader.close();
        connection.disconnect();

        JsonObject conversionRates = jsonResponse.getAsJsonObject("conversion_rates");
        return conversionRates.get(toCurrency).getAsDouble();
    }
}