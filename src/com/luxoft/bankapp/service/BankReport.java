package com.luxoft.bankapp.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

import com.luxoft.bankapp.domain.Account;
import com.luxoft.bankapp.domain.Bank;
import com.luxoft.bankapp.domain.CheckingAccount;
import com.luxoft.bankapp.domain.Client;

public class BankReport {

	private final Bank bank;

	public BankReport(Bank bank) {
		this.bank = bank;
	}

	public Bank getBank() {
		return bank;
	}

	/** Returns the number of bank clients */
	public int getNumberOfClients() {
		return bank.getClients().size();
	}

	/** Returns the total number of accounts for all bank clients */
	public int getNumberOfAccounts() {
		Set<Account> accounts = new HashSet<>();
		for (Client client : bank.getClients()) {
			accounts.addAll(client.getAccounts());
		}
		return accounts.size();
	}

	/** Returns the set of clients sorted by name */
	public SortedSet<Client> getClientsSorted() {
		SortedSet<Client> clients = new TreeSet<>(Comparator.comparing(Client::getName,
				Comparator.nullsLast(String::compareTo)));
		clients.addAll(bank.getClients());
		return clients;
	}

	/** Returns the total balance of all accounts */
	public double getTotalSumInAccounts() {
		double sum = 0.0;
		Set<Account> accounts = new HashSet<>();
		for (Client client : bank.getClients()) {
			accounts.addAll(client.getAccounts());
		}

		for (Account account : accounts) {
			sum += account.getBalance();
		}

		return Math.round(sum * 100.0) / 100.0;
	}

	/** Returns accounts sorted by balance */
	public SortedSet<Account> getAccountsSortedBySum() {
		SortedSet<Account> result = new TreeSet<>(
				Comparator.comparingDouble(Account::getBalance)
		);

		for (Client client : bank.getClients()) {
			result.addAll(client.getAccounts());
		}

		return result;
	}

	/**
	 * Returns total negative balance of CheckingAccount (the credit amount)
	 */
	public double getBankCreditSum() {
		double result = 0.0;

		Set<Account> accounts = new HashSet<>();
		for (Client client : bank.getClients()) {
			accounts.addAll(client.getAccounts());
		}

		for (Account account : accounts) {
			if (account instanceof CheckingAccount && account.getBalance() < 0) {
				result -= account.getBalance();
			}
		}

		return Math.round(result * 100.0) / 100.0;
	}

	/** Returns a map of clients to their accounts */
	public Map<Client, Collection<Account>> getCustomerAccounts() {
		Map<Client, Collection<Account>> result = new HashMap<>();
		for (Client client : bank.getClients()) {
			result.put(client, client.getAccounts());
		}
		return result;
	}

	/** Returns a map of cities to the list of clients living there */
	public Map<String, List<Client>> getClientsByCity() {
		Map<String, List<Client>> cityMap = new TreeMap<>();

		for (Client client : bank.getClients()) {
			String city = client.getCity();
			cityMap.computeIfAbsent(city, c -> new ArrayList<>()).add(client);
		}

		return cityMap;
	}
}
