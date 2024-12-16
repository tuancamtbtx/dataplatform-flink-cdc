package com.dataplatform.cdc.flink.app;

import com.dataplatform.cdc.flink.topology.PostgresToDorisProcessor;

import java.util.Properties;

public class MainApp {
    public static void main(String[] args) throws Exception {
        Properties prop = new Properties();
        PostgresToDorisProcessor processor = new PostgresToDorisProcessor(prop);
        processor.process();
    }
}