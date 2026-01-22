package com.luxoft.bankapp.domain;

import com.luxoft.bankapp.exceptions.NotEnoughFundsException;
import com.luxoft.bankapp.utils.Params;

import java.io.Serial;
import java.io.Serializable;

//ex2/3
public abstract class AbstractAccount implements Account, Serializable, Cloneable {

    @Serial
    private static final long serialVersionUID = -2272551373694344386L;

    private final int id;

    private double balance;
    private final Currency currency;

    public Currency getCurrency() {
        return currency;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    protected AbstractAccount(int id, double amount) {
        this.id = id;
        this.balance = amount;
        this.currency = new Currency("USD");
    }

    protected AbstractAccount(int id, double amount, Currency currency) {
        this.id = id;
        this.balance = amount;
        this.currency = currency;
    }

    @Override
    public void deposit(final double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Cannot deposit a negative amount");
        }
        this.balance += amount;
    }

    @Override
    public void withdraw(final double amount) throws NotEnoughFundsException {
        if (amount < 0) {
            throw new IllegalArgumentException("Cannot withdraw a negative amount");
        }

        if (amount > maximumAmountToWithdraw()) {
            throw new NotEnoughFundsException(id, balance, amount, "Requested amount exceeds the maximum amount to withdraw");
        }

        this.balance -= amount;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public double getBalance() {
        return balance;
    }

    @Override
    public long decimalValue() {
        return Math.round(balance);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        AbstractAccount other = (AbstractAccount) obj;
        return id == other.id;
    }

    public static Account parse(Params params) {
        return switch (params.get("accountType")) {
            case "s" -> SavingAccount.parse(params);
            case "c" -> CheckingAccount.parse(params);
            default -> null;
        };

    }

    @Override
    public AbstractAccount clone() {
        try {
            return (AbstractAccount) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}