package org.example.model;

import org.apache.spark.sql.types.DataTypes;
import org.apache.spark.sql.types.StructType;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CardTest {
    @Test
    void testCardSchema() {
        StructType schema = Card.getSchema();

        // Verify schema structure
        assertEquals(4, schema.fields().length);
        assertEquals(DataTypes.StringType, schema.apply("number").dataType());
        assertEquals(DataTypes.StringType, schema.apply("bank").dataType());
        assertEquals(DataTypes.StringType, schema.apply("agency").dataType());
        assertEquals(DataTypes.StringType, schema.apply("account").dataType());
    }

    @Test
    void testCardGettersAndSetters() {
        Card card = new Card();
        card.setNumber("1234-5678-9012-3456");
        card.setBank("Test Bank");
        card.setAgency("0001");
        card.setAccount("123456-7");

        assertEquals("1234-5678-9012-3456", card.getNumber());
        assertEquals("Test Bank", card.getBank());
        assertEquals("0001", card.getAgency());
        assertEquals("123456-7", card.getAccount());
    }
}