package com.luxoft.bankapp.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import com.luxoft.bankapp.domain.CheckingAccount;
import com.luxoft.bankapp.domain.SavingAccount;

import org.junit.Test;

public class AccountCloneTest {

    @Test
    public void testCloneCheckingAccount() {
        CheckingAccount checkingAccount = new CheckingAccount(1, 1000.0, 100.0);
        CheckingAccount checkingAccountClone = (CheckingAccount) checkingAccount.clone();

        assertEquals(checkingAccount.getId(), checkingAccountClone.getId());
        assertEquals(checkingAccount.getBalance(), checkingAccountClone.getBalance(), 0);
        assertEquals(checkingAccount.getOverdraft(), checkingAccountClone.getOverdraft(), 0);

        checkingAccount.setBalance(2000.0);

        assertNotEquals(
                "After changing the balance of the original account, "
                        + "the balance of the clone account should not change as well",
                checkingAccount.getBalance(), checkingAccountClone.getBalance(), 0
        );
    }

    @Test
    public void testCloneSavingAccount() {
        SavingAccount savingAccount = new SavingAccount(1, 1000.0);
        SavingAccount savingAccountClone = (SavingAccount) savingAccount.clone();

        assertEquals(savingAccount.getId(), savingAccountClone.getId());
        assertEquals(savingAccount.getBalance(), savingAccountClone.getBalance(), 0);

        savingAccount.setBalance(2000.0);

        assertNotEquals(
                "After changing the balance of the original account, "
                        + "the balance of the clone account should not change as well",
                savingAccount.getBalance(), savingAccountClone.getBalance(), 0
        );
    }

}