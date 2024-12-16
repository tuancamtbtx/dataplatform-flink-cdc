package com.dataplatform.cdc.flink.connector;

import org.apache.flink.cdc.connectors.mysql.source.MySqlSource;
import org.apache.flink.cdc.debezium.JsonDebeziumDeserializationSchema;

import java.util.Properties;

public class MysqlSourceConnector {
    public static MySqlSource build(Properties props) {
        MySqlSource<String> mySqlSource = MySqlSource.<String>builder()
                .hostname("yourHostname")
                .port(9000)
                .databaseList("yourDatabaseName") // set captured database
                .tableList("yourDatabaseName.yourTableName") // set captured table
                .username("yourUsername")
                .password("yourPassword")
                .deserializer(new JsonDebeziumDeserializationSchema()) // converts SourceRecord to JSON String
                .build();
        return mySqlSource;
    }
}
