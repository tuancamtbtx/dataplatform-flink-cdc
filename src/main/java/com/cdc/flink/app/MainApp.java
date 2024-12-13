package com.cdc.flink.app;

import org.apache.flink.api.common.eventtime.WatermarkStrategy;
import org.apache.flink.cdc.connectors.mysql.source.MySqlSource;
import org.apache.flink.cdc.debezium.JsonDebeziumDeserializationSchema;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

public class MainApp {
    public static void main(String[] args) throws Exception {
//        MySqlSource<String> mySqlSource = MySqlSource.<String>builder()
//                .hostname("yourHostname")
//                .port(9000)
//                .databaseList("yourDatabaseName") // set captured database
//                .tableList("yourDatabaseName.yourTableName") // set captured table
//                .username("yourUsername")
//                .password("yourPassword")
//                .deserializer(new JsonDebeziumDeserializationSchema()) // converts SourceRecord to JSON String
//                .build();

        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        // enable checkpoint
        env.enableCheckpointing(3000);
//
//        env
//                .fromSource(mySqlSource, WatermarkStrategy.noWatermarks(), "MySQL Source")
//                // set 4 parallel source tasks
//                .setParallelism(4)
//                .print().setParallelism(1); // use parallelism 1 for sink to keep message ordering

        env.execute("Print MySQL Snapshot + Binlog");
    }
}