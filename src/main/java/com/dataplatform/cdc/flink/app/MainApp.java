package com.dataplatform.cdc.flink.app;

import com.dataplatform.cdc.flink.factory.FlinkEnvFactory;
import com.dataplatform.cdc.flink.topology.PostgresToDorisProcessor;
import org.apache.flink.api.common.restartstrategy.RestartStrategies;
import org.apache.flink.api.common.time.Time;

import java.util.Properties;

public class MainApp {
    public static void main(String[] args) throws Exception {

        PostgresToDorisProcessor processor = new PostgresToDorisProcessor();
        processor.process();
    }
}