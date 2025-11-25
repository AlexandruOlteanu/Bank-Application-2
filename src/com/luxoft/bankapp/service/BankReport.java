package com.luxoft.bankapp.service;

import java.util.*;
import com.luxoft.bankapp.domain.*;

public class BankReport {

	private final Bank bank;

	public BankReport(Bank bank) {
		this.bank = bank;
	}

	public Bank getBank() {
		return bank;
	}

	/* Returns the number of bank clients */
	public int getNumberOfClients() {
		return bank.getClients().size();
	}

	/* Returns the total number of accounts of all bank clients */
	public int getNumberOfAccounts() {
		Set<Account> allAccounts = new HashSet<>();
		for (Client client : bank.getClients()) {
			allAccounts.addAll(client.getAccounts());
		}
		return allAccounts.size();
	}

	/* Returns the set of clients sorted alphabetically by name */
	public SortedSet<Client> getClientsSorted() {
		SortedSet<Client> sorted = new TreeSet<>(Comparator.comparing(Client::getName));
		sorted.addAll(bank.getClients());
		return sorted;
	}

	/* Returns the total sum of balances in all accounts */
	public double getTotalSumInAccounts() {
		double sum = 0.0;
		for (Client client : bank.getClients()) {
			for (Account account : client.getAccounts()) {
				sum += account.getBalance();
			}
		}
		return Math.round(sum * 100) / 100d;
	}

	/* Returns all accounts sorted by current balance */
	public SortedSet<Account> getAccountsSortedBySum() {
		SortedSet<Account> sorted = new TreeSet<>(
				Comparator.comparingDouble(Account::getBalance)
		);
		for (Client client : bank.getClients()) {
			sorted.addAll(client.getAccounts());
		}
		return sorted;
	}

	/* Returns the total credit sum for CheckingAccount with negative balance */
	public double getBankCreditSum() {
		double totalCredit = 0.0;
		for (Client client : bank.getClients()) {
			for (Account account : client.getAccounts()) {
				if (account instanceof CheckingAccount && account.getBalance() < 0) {
					totalCredit += -account.getBalance();
				}
			}
		}
		return Math.round(totalCredit * 100) / 100d;
	}

	/* Returns a map of Client -> Collection<Account> */
	public Map<Client, Collection<Account>> getCustomerAccounts() {
		Map<Client, Collection<Account>> result = new HashMap<>();
		for (Client client : bank.getClients()) {
			result.put(client, client.getAccounts());
		}
		return result;
	}

	/* Returns Map<City, List<Client>>, sorted by city name */
	public Map<String, List<Client>> getClientsByCity() {
		Map<String, List<Client>> result = new TreeMap<>();

		for (Client client : bank.getClients()) {
			String city = client.getCity();
			if (city == null) {
				city = "Unknown";
			}

			result.putIfAbsent(city, new ArrayList<>());
			result.get(city).add(client);
		}

		// Sort clients in each city by name
		for (List<Client> list : result.values()) {
			list.sort(Comparator.comparing(Client::getName));
		}

		return result;
	}
}
