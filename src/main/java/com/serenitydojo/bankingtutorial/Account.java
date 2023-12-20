package com.serenitydojo.bankingtutorial;

public class Account {
    Integer balance;
    public Account(Integer amount) {
        this.balance = amount;
    }

    public Integer getBalance() {
        return balance;
    }

    public void transferTo(Account to, Integer amountToTransfer) {
        if (amountToTransfer > balance) {
            throw new InsufficientFundsException("Insufficient funds on the account");
        }
        this.withdraw(amountToTransfer);
        to.deposit(amountToTransfer);
    }

    public void deposit(Integer amountToTransfer) {
        this.balance += amountToTransfer;
    }

    public void withdraw(Integer amountToTransfer) {
        this.balance -= amountToTransfer;
    }
}
