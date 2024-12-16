package com.dataplatform.cdc.flink.topology;

import com.dataplatform.cdc.flink.connector.MysqlSourceConnector;
import com.dataplatform.cdc.flink.factory.FlinkFactory;
import org.apache.flink.api.common.eventtime.WatermarkStrategy;
import org.apache.flink.cdc.connectors.mysql.source.MySqlSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

import java.util.Properties;

public class PostgresToDorisProcessor {
    private StreamExecutionEnvironment env;
    private MySqlSource<String> mySqlSource;
    public PostgresToDorisProcessor( Properties prop) {
        StreamExecutionEnvironment env = FlinkFactory.getEnv();
        env.enableCheckpointing(3000);
        this.env = env;
        this.mySqlSource = MysqlSourceConnector.build(prop);
    }
    public void process() throws Exception {
        env
                .fromSource(this.mySqlSource, WatermarkStrategy.noWatermarks(), "MySQL Source")
                // set 4 parallel source tasks
                .setParallelism(4)
                .print().setParallelism(1); // use parallelism 1 for sink to keep message ordering

        env.execute("Print MySQL Snapshot + Binlog");
    }
}
