package org.example.service;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.types.DataTypes;
import org.apache.spark.sql.types.StructType;
import org.example.model.Buyer;
import org.example.model.Card;
import org.example.model.Item;

public class DataReader {
    private final SparkSession sparkSession;
    private Dataset<Row> data;

    public DataReader(SparkSession sparkSession) {
        this.sparkSession = sparkSession;
    }

    public void readAndShowData(String path, int limit) {
        StructType schema = createSchema();
        if (limit != 0)
            this.data = sparkSession.read()
                    .schema(schema)
                    .json(path)
                    .limit(limit);
        else
            this.data = sparkSession.read()
                    .schema(schema)
                    .json(path);
        data.printSchema();
        data.show(false);
    }

    private StructType createSchema() {
        return new StructType()
                .add("id", DataTypes.StringType)
                .add("created_at", DataTypes.StringType)
                .add("buyer", Buyer.getSchema())
                .add("card", Card.getSchema())
                .add("company", DataTypes.StringType)
                .add("item", Item.getSchema())
                .add("total_value", DataTypes.DoubleType);
    }

    public Dataset<Row> getData() {
        return data;
    }
}