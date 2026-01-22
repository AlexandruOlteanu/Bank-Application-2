package com.luxoft.bankapp.domain;

import com.luxoft.bankapp.utils.Params;

import java.io.Serial;
import java.util.Formatter;
import java.util.Locale;


public class SavingAccount extends AbstractAccount {

    @Serial
    private static final long serialVersionUID = 9200460687227050240L;

    public SavingAccount(int id, double amount) {
        super(id, amount);
    }

    public SavingAccount(int id, double amount, Currency currency) {
        super(id, amount, currency);
    }

    @Override
    public String toString() {
        Formatter fmt = new Formatter(Locale.US);
        String stringAccount = fmt.format("Saving account %d, balance: %.2f", getId(), getBalance()).toString();
        fmt.close();
        return stringAccount;
    }

    public static Account parse(Params params) {
        String id = params.get("id");
        String balance = params.get("balance");
        String currency = params.get("currency");

        return new SavingAccount(
                Integer.parseInt(id),
                Double.parseDouble(balance),
                new Currency(currency));
    }

    @Override
    public double maximumAmountToWithdraw() {
        return getBalance();
    }

}