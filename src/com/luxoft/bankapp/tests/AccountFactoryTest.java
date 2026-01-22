package com.luxoft.bankapp.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import com.luxoft.bankapp.domain.Account;
import com.luxoft.bankapp.domain.AccountType;
import com.luxoft.bankapp.domain.CheckingAccount;
import com.luxoft.bankapp.domain.SavingAccount;
import com.luxoft.bankapp.factory.AccountFactory;

import org.junit.Test;

public class AccountFactoryTest {

    @Test
    public void testOfCheckingAccount() {
        Account account = AccountFactory.of(AccountType.CHECKING);
        assertTrue(
                "Account should be an instance of CheckingAccount",
                account instanceof CheckingAccount
        );
        assertEquals(
                "Account id should be defaulted to 0",
                0, account.getId()
        );
        assertEquals(
                "Account balance should be defaulted to 0.0",
                0.0, account.getBalance(),
                0.0
        );
    }

    @Test
    public void testOfSavingAccount() {
        Account account = AccountFactory.of(AccountType.SAVING);
        assertTrue(
                "Account should be an instance of SavingAccount",
                account instanceof SavingAccount
        );
        assertEquals(
                "Account id should be defaulted to 0",
                0,
                account.getId()
        );
        assertEquals(
                "Account balance should be defaulted to 0.0",
                0.0,
                account.getBalance(),
                0.0
        );
    }

}