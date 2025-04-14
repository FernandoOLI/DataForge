package org.example.model;

import org.apache.spark.sql.types.DataTypes;
import org.apache.spark.sql.types.StructType;

public class Card {
    private String number;
    private String bank;
    private String agency;
    private String account;

    public static StructType getSchema() {
        return new StructType()
                .add("number", DataTypes.StringType)
                .add("bank", DataTypes.StringType)
                .add("agency", DataTypes.StringType)
                .add("account", DataTypes.StringType);
    }

    // Getters and Setters
    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getAgency() {
        return agency;
    }

    public void setAgency(String agency) {
        this.agency = agency;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }
}