package com.dataplatform.cdc.flink.connector;

import org.apache.flink.cdc.connectors.postgres.PostgreSQLSource;
import org.apache.flink.cdc.debezium.JsonDebeziumDeserializationSchema;
import org.apache.flink.streaming.api.functions.source.SourceFunction;

import java.util.Properties;

public class PostgresSourceConnector {
    public static SourceFunction build() {
        SourceFunction<String> sourceFunction = PostgreSQLSource.<String>builder()
                .hostname("localhost")
                .port(5432)
                .database("postgres") // monitor postgres database
                .schemaList("inventory")  // monitor inventory schema
                .tableList("inventory.products") // monitor products table
                .username("postgres")
                .password("postgres")
                .deserializer(new JsonDebeziumDeserializationSchema()) // converts SourceRecord to JSON String
                .build();
        return sourceFunction;
    }
}
