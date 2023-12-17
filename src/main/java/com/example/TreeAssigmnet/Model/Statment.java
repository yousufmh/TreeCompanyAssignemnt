package com.example.TreeAssigmnet.Model;

import java.time.LocalDate;
import java.util.Date;

public class Statment {

    private int ID;
    private int AccountID;
    private LocalDate dateField;
    private double amount;
    private String accountNumber;

    public Statment(int ID, int accountID, LocalDate dateField, double amount, String accountNumber) {
        this.ID = ID;
        AccountID = accountID;
        this.dateField = dateField;
        this.amount = amount;
        this.accountNumber = accountNumber;
    }

    public Statment() {
        this.ID = 0;
        AccountID = 0;
        this.amount = 0.0;
        this.accountNumber = "" ;

    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getAccountID() {
        return AccountID;
    }

    public void setAccountID(int accountID) {
        AccountID = accountID;
    }

    public LocalDate getDateField() {
        return dateField;
    }

    public void setDateField(LocalDate dateField) {
        this.dateField = dateField;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    @Override
    public String toString() {
        return "Statment{" +
                "ID=" + ID +
                ", AccountID=" + AccountID +
                ", dateField='" + dateField + '\'' +
                ", amount='" + amount + '\'' +
                ", accountNumber='" + accountNumber + '\'' +
                '}';
    }


}
