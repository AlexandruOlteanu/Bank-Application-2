package com.luxoft.bankapp.service;

import com.luxoft.bankapp.domain.Bank;

import java.util.Scanner;

public class BankApplication {

    public static void main(String[] args) {

        Bank bank = new Bank();
        loadTestData(bank); // <-- your method for populating data

        // Check for "-statistics" command line argument
        if (args.length > 0 && args[0].equalsIgnoreCase("-statistics")) {
            System.out.println("Starting BankApplication in STATISTICS MODE...");
            BankStatistics stats = new BankStatistics(bank);
            stats.printStatistics();
            return; // Exit application after showing statistics
        }

        // Normal interactive mode
        runConsole(bank);
    }

    /** Regular console mode */
    private static void runConsole(Bank bank) {
        Scanner scanner = new Scanner(System.in);
        BankStatistics stats = new BankStatistics(bank);

        while (true) {
            System.out.println("\nEnter a command:");
            System.out.println("1. display statistics");
            System.out.println("2. exit");
            System.out.print("> ");

            String command = scanner.nextLine().trim().toLowerCase();

            switch (command) {
                case "display statistics":
                    stats.printStatistics();
                    break;

                case "exit":
                    System.out.println("Exiting program...");
                    return;

                default:
                    System.out.println("Unknown command.");
            }
        }
    }

    /** Stub: replace with your real bank data loader */
    private static void loadTestData(Bank bank) {
        // your existing logic that populates the bank with clients and accounts
    }
}
