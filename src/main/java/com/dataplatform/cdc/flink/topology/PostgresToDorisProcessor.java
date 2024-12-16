package com.dataplatform.cdc.flink.topology;

import com.dataplatform.cdc.flink.connector.PostgresSourceConnector;
import com.dataplatform.cdc.flink.factory.FlinkEnvFactory;
import org.apache.flink.api.common.restartstrategy.RestartStrategies;
import org.apache.flink.api.common.time.Time;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.source.SourceFunction;

import java.util.concurrent.TimeUnit;

public class PostgresToDorisProcessor {
    private StreamExecutionEnvironment env;
    private final SourceFunction<String> mySqlSource;

    public PostgresToDorisProcessor() {
        StreamExecutionEnvironment env = FlinkEnvFactory.getEnv();
        // Example usage
        FlinkEnvFactory factory = new FlinkEnvFactory(
                4, // parallelism
                true, // enableCheckpointing
                10000L, // checkpointInterval
                true, // useEventTime
                RestartStrategies.fixedDelayRestart(3, Time.of(10, TimeUnit.SECONDS)) // restartStrategy
        );
//        env.enableCheckpointing(3000);
        this.env = factory.createStreamExecutionEnvironment();
        this.mySqlSource = PostgresSourceConnector.build();
    }
    public void process() throws Exception {
        env
                .addSource(this.mySqlSource)
                .print().setParallelism(1); // use parallelism 1 for sink to keep message ordering

        env.execute("Print MySQL Snapshot + Binlog");
    }
}
