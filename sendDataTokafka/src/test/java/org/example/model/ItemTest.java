package org.example.model;

import org.apache.spark.sql.types.DataTypes;
import org.apache.spark.sql.types.StructType;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ItemTest {
    @Test
    void testItemSchema() {
        StructType schema = Item.getSchema();

        assertEquals(9, schema.fields().length);
        assertEquals(DataTypes.StringType, schema.apply("name").dataType());
        assertEquals(DataTypes.DoubleType, schema.apply("price").dataType());
    }

    @Test
    void testItemGettersSetters() {
        Item item = new Item();
        item.setName("Smartphone");
        item.setPrice(999.99);
        item.setStock(10);

        assertEquals("Smartphone", item.getName());
        assertEquals(999.99, item.getPrice());
        assertEquals(10, item.getStock());
    }
}