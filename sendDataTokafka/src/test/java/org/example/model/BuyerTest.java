package org.example.model;

import org.apache.spark.sql.types.DataTypes;
import org.apache.spark.sql.types.StructType;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BuyerTest {
    @Test
    void testBuyerSchema() {
        StructType schema = Buyer.getSchema();

        assertEquals(6, schema.fields().length);
        assertEquals(DataTypes.StringType, schema.apply("name").dataType());
        assertEquals(DataTypes.StringType, schema.apply("cpf").dataType());
    }

    @Test
    void testBuyerGettersSetters() {
        Buyer buyer = new Buyer();
        buyer.setName("John Doe");
        buyer.setCpf("123.456.789-00");

        assertEquals("John Doe", buyer.getName());
        assertEquals("123.456.789-00", buyer.getCpf());
    }
}