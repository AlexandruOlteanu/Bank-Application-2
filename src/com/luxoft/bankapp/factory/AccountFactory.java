package com.luxoft.bankapp.factory;

import com.luxoft.bankapp.domain.AbstractAccount;
import com.luxoft.bankapp.domain.AccountType;
import com.luxoft.bankapp.domain.CheckingAccount;
import com.luxoft.bankapp.domain.SavingAccount;

//ex1
public class AccountFactory {
    private AccountFactory() {}

    public static AbstractAccount of(AccountType type) {
        return switch (type) {
            case SAVING -> new SavingAccount(0, 0.0);
            case CHECKING -> new CheckingAccount(0, 0.0, 0.0);
        };
    }
}
