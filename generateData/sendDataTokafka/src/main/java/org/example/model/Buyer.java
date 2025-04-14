package org.example.model;

import org.apache.spark.sql.types.DataTypes;
import org.apache.spark.sql.types.StructType;

public class Buyer {
    private String name;
    private String cpf;
    private String phone;
    private String email;
    private String address;
    private String birthDate;

    public static StructType getSchema() {
        return new StructType()
                .add("name", DataTypes.StringType)
                .add("cpf", DataTypes.StringType)
                .add("phone", DataTypes.StringType)
                .add("email", DataTypes.StringType)
                .add("address", DataTypes.StringType)
                .add("birth_date", DataTypes.StringType);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }
}