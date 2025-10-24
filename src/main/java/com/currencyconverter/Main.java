package com.currencyconverter;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner userInput = new Scanner(System.in);

        System.out.println("-------------------------------");
        System.out.println("----- CONVERSOR DE MOEDAS -----");
        System.out.println("-------------------------------");

        while (true) {
            System.out.println("Escolha a conversão desejada:");
            System.out.println("1 - USD (Dólar) >> BRL (Real)");
            System.out.println("2 - BRL (Real) >> USD (Dólar)");
            System.out.println("3 - BRL (Real) >> EUR (Euro)");
            System.out.println("4 - USD (Dólar) >> EUR (Euro)");
            System.out.println("5 - EUR (Euro) >> USD (Dólar)");
            System.out.println("6 - EUR (Euro) >> BRL (Real)");
            System.out.println("7 - Sair");

            System.out.print("* Digite uma opção [ 1 - 7 ]: ");
            int option = userInput.nextInt();

            if (option == 7) {
                System.out.println("Encerrando o programa. Até logo!");
                break;
            }

            if (option < 1 || option > 7) {
                System.out.println("Opção inválida! Tente novamente.\n");
                continue;
            }

            System.out.print("Digite o valor a ser convertido: ");
            double value = userInput.nextDouble();

            try {
                double valueConverted = 0.0;
                switch (option) {
                    case 1 -> valueConverted = CurrencyConverter.convert(value, "USD", "BRL");
                    case 2 -> valueConverted = CurrencyConverter.convert(value, "BRL", "USD");
                    case 4 -> valueConverted = CurrencyConverter.convert(value, "BRL", "EUR");
                    case 5 -> valueConverted = CurrencyConverter.convert(value, "USD", "EUR");
                    case 6 -> valueConverted = CurrencyConverter.convert(value, "EUR", "USD");
                    case 3 -> valueConverted = CurrencyConverter.convert(value, "EUR", "BRL");
                    default -> System.out.println("Opção inválida!");
                }
                System.out.printf("Resultado: %.2f%n", valueConverted);
                System.out.println("\n");

            } catch (Exception e) {
                System.out.println("Erro: " + e.getMessage());
            }
        }

        userInput.close();
    }
}