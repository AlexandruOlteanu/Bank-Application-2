package com.luxoft.bankapp.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import com.luxoft.bankapp.domain.Account;
import com.luxoft.bankapp.domain.AccountCache;
import com.luxoft.bankapp.domain.AccountType;
import com.luxoft.bankapp.domain.CheckingAccount;
import com.luxoft.bankapp.domain.SavingAccount;

import org.junit.Test;

public class AccountCacheTest {

    @Test
    public void testLoadSavingAccount() {
        AccountCache.loadCache();
        Account savingAccount = AccountCache.cloneAccount(AccountType.SAVING);

        assertTrue(
                "Expected account type to be SAVING",
                savingAccount instanceof SavingAccount
        );

        assertEquals(
                "Expected id to be 0",
                0,
                savingAccount.getId()
        );

        assertEquals(
                "Expected balance to be 0.0",
                0.0,
                savingAccount.getBalance(),
                0.0
        );
    }

    @Test
    public void testLoadCheckingAccount() {
        AccountCache.loadCache();
        Account checkingAccount = AccountCache.cloneAccount(AccountType.CHECKING);

        assertTrue(
                "Expected account type to be CHECKING",
                checkingAccount instanceof CheckingAccount
        );

        assertEquals(
                "Expected id to be 0",
                0,
                checkingAccount.getId()
        );

        assertEquals(
                "Expected balance to be 0.0",
                0.0,
                checkingAccount.getBalance(),
                0.0
        );

        assertEquals(
                "Expected overdraft to be 0.0",
                0.0,
                ((CheckingAccount) checkingAccount).getOverdraft(),
                0.0
        );
    }
}