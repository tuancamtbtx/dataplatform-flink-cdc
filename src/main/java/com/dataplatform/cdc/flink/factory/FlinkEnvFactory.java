package com.dataplatform.cdc.flink.factory;

import org.apache.flink.api.common.restartstrategy.RestartStrategies;
import org.apache.flink.streaming.api.TimeCharacteristic;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

public class FlinkEnvFactory {
    private final int parallelism;
    private final boolean enableCheckpointing;
    private final long checkpointInterval;
    private final boolean useEventTime;
    private final RestartStrategies.RestartStrategyConfiguration restartStrategy;

    public FlinkEnvFactory(int parallelism, boolean enableCheckpointing, long checkpointInterval, boolean useEventTime, RestartStrategies.RestartStrategyConfiguration restartStrategy) {
        this.parallelism = parallelism;
        this.enableCheckpointing = enableCheckpointing;
        this.checkpointInterval = checkpointInterval;
        this.useEventTime = useEventTime;
        this.restartStrategy = restartStrategy;
    }

    public static StreamExecutionEnvironment getEnv() {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        return env;
    }

    public StreamExecutionEnvironment createStreamExecutionEnvironment() {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.setParallelism(parallelism);

        if (enableCheckpointing) {
            env.enableCheckpointing(checkpointInterval);
        }

        if (useEventTime) {
            env.setStreamTimeCharacteristic(TimeCharacteristic.EventTime);
        }

        env.setRestartStrategy(restartStrategy);

        // Add more properties or configurations as needed
        return env;
    }
}
