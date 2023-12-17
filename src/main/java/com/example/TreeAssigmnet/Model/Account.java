package com.example.TreeAssigmnet.Model;

import java.util.Objects;


public class Account {

    private int ID;
    private String  accountType;
    private String accountNumber;

    public Account(int ID, String accountType, String accountNumber) {
        this.ID = ID;
        this.accountType = accountType;
        this.accountNumber = accountNumber;
    }

    public Account() {
        this.ID = 0;
        this.accountType = "";
        this.accountNumber = "";

    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    @Override
    public String toString() {
        return "Account{" +
                "ID=" + ID +
                ", accountType='" + accountType + '\'' +
                ", accountNumber='" + accountNumber + '\'' +
                '}';
    }



}
