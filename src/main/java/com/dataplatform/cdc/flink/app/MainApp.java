package com.dataplatform.cdc.flink.app;

import com.dataplatform.cdc.flink.topology.PostgresToDorisProcessor;

public class MainApp {
    public static void main(String[] args) throws Exception {
        PostgresToDorisProcessor processor = new PostgresToDorisProcessor();
        processor.process();
    }
}