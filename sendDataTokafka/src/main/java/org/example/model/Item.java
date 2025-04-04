package org.example.model;

import org.apache.spark.sql.types.DataTypes;
import org.apache.spark.sql.types.StructType;

public class Item {
    private String name;
    private String category;
    private double price;
    private String code;
    private String sku;
    private String brand;
    private int stock;
    private String manufacturingAt;
    private String expirationAt;

    public static StructType getSchema() {
        return new StructType()
                .add("name", DataTypes.StringType)
                .add("category", DataTypes.StringType)
                .add("price", DataTypes.DoubleType)
                .add("code", DataTypes.StringType)
                .add("sku", DataTypes.StringType)
                .add("brand", DataTypes.StringType)
                .add("stock", DataTypes.IntegerType)
                .add("manufacturing_at", DataTypes.StringType)
                .add("expiration_at", DataTypes.StringType);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getManufacturingAt() {
        return manufacturingAt;
    }

    public void setManufacturingAt(String manufacturingAt) {
        this.manufacturingAt = manufacturingAt;
    }

    public String getExpirationAt() {
        return expirationAt;
    }

    public void setExpirationAt(String expirationAt) {
        this.expirationAt = expirationAt;
    }
}