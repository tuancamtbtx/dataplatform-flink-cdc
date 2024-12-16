package com.dataplatform.cdc.flink.factory;

import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

public class FlinkFactory {
    public static StreamExecutionEnvironment getEnv() {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        return env;
    }
}
