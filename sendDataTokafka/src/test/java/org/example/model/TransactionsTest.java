package org.example.model;

import org.apache.spark.sql.types.DataTypes;
import org.apache.spark.sql.types.StructType;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TransactionTest {
    @Test
    void testTransactionSchema() {
        StructType schema = Transaction.getSchema();

        assertEquals(7, schema.fields().length);
        assertEquals(DataTypes.StringType, schema.apply("id").dataType());
        assertEquals(DataTypes.DoubleType, schema.apply("total_value").dataType());
    }

    @Test
    void testTransactionGettersSetters() {
        Transaction transaction = new Transaction();
        Buyer buyer = new Buyer();
        Item item = new Item();

        buyer.setName("John Doe");
        item.setName("Laptop");

        transaction.setId("T1001");
        transaction.setBuyer(buyer);
        transaction.setItem(item);
        transaction.setTotalValue(1299.99);

        assertEquals("T1001", transaction.getId());
        assertEquals("John Doe", transaction.getBuyer().getName());
        assertEquals("Laptop", transaction.getItem().getName());
        assertEquals(1299.99, transaction.getTotalValue());
    }
}