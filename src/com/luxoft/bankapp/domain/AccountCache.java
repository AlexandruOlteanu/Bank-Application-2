package com.luxoft.bankapp.domain;

import com.luxoft.bankapp.factory.AccountFactory;

import java.util.HashMap;
import java.util.Map;

//ex2
public class AccountCache {

    private AccountCache() {
    }

    private static final Map<String, AbstractAccount> cachedAccounts = new HashMap<>();

    public static void loadCache() {
        cachedAccounts.put(
                AccountType.CHECKING.name(),
                AccountFactory.of(AccountType.CHECKING)
        );

        cachedAccounts.put(
                AccountType.SAVING.name(),
                AccountFactory.of(AccountType.SAVING)
        );
    }

    public static AbstractAccount cloneAccount(AccountType accountType) {
        return cachedAccounts.get(accountType.name()).clone();
    }
}