package com.currencyconverter;

public class CurrencyConverter {

    public static double convert(double amount, String from, String to) throws Exception {
        double rate = ApiClient.getExchangeRate(from, to);
        return amount * rate;
    }
}