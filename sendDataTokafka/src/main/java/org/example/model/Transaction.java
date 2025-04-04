package org.example.model;

import org.apache.spark.sql.types.DataTypes;
import org.apache.spark.sql.types.StructType;

public class Transaction {
    private String id;
    private String createdAt;
    private Buyer buyer;
    private Card card;
    private String company;
    private Item item;
    private double totalValue;

    public static StructType getSchema() {
        return new StructType()
                .add("id", DataTypes.StringType)
                .add("created_at", DataTypes.StringType)
                .add("buyer", Buyer.getSchema())
                .add("card", Card.getSchema())
                .add("company", DataTypes.StringType)
                .add("item", Item.getSchema())
                .add("total_value", DataTypes.DoubleType);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public Buyer getBuyer() {
        return buyer;
    }

    public void setBuyer(Buyer buyer) {
        this.buyer = buyer;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public double getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(double totalValue) {
        this.totalValue = totalValue;
    }
}