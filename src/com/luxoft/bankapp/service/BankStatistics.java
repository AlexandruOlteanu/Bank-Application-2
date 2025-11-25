package com.luxoft.bankapp.service;

import com.luxoft.bankapp.domain.Bank;
import com.luxoft.bankapp.domain.Client;
import com.luxoft.bankapp.domain.Account;

import java.util.Map;
import java.util.List;
import java.util.SortedSet;

public class BankStatistics {

    private final BankReport report;

    public BankStatistics(Bank bank) {
        this.report = new BankReport(bank);
    }

    /** Print all bank statistics to console */
    public void printStatistics() {

        System.out.println("====== BANK STATISTICS ======\n");

        // 1. Number of clients
        System.out.println("Total number of clients: " + report.getNumberOfClients());

        // 2. Number of accounts
        System.out.println("Total number of accounts: " + report.getNumberOfAccounts());

        // 3. Total sum of all accounts
        System.out.println("Total sum in accounts: " + report.getTotalSumInAccounts());

        // 4. Total credit debt
        System.out.println("Total bank credit sum: " + report.getBankCreditSum());

        // 5. Clients by name order
        System.out.println("\n--- Clients sorted by name ---");
        SortedSet<Client> sortedClients = report.getClientsSorted();
        for (Client c : sortedClients) {
            System.out.println("* " + c.getName());
        }

        // 6. Accounts sorted by balance
        System.out.println("\n--- Accounts sorted by balance ---");
        SortedSet<Account> sortedAccounts = report.getAccountsSortedBySum();
        for (Account acc : sortedAccounts) {
            System.out.println(
                    acc.getClass().getSimpleName()
                            + " (" + acc.getId() + ") - balance: " + acc.getBalance()
            );
        }

        // 7. Clients by city
        System.out.println("\n--- Clients by city ---");
        Map<String, List<Client>> byCity = report.getClientsByCity();

        for (String city : byCity.keySet()) {
            System.out.println(city + ":");
            for (Client c : byCity.get(city)) {
                System.out.println("  - " + c.getName());
            }
        }

        // 8. Map of clients and accounts
        System.out.println("\n--- Accounts per client ---");
        Map<Client, java.util.Collection<Account>> customerAccounts = report.getCustomerAccounts();
        for (Client client : customerAccounts.keySet()) {
            System.out.println(client.getName() + ":");
            for (Account acc : customerAccounts.get(client)) {
                System.out.println("  - " + acc.getClass().getSimpleName()
                        + " #" + acc.getId()
                        + " balance: " + acc.getBalance());
            }
        }

        System.out.println("\n====== END OF STATISTICS ======");
    }
}
