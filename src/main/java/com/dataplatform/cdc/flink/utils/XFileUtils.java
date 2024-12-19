package com.dataplatform.cdc.flink.utils;

import io.github.cdimascio.dotenv.Dotenv;

public class XFileUtils {
    public static String FLINK_JOB_CONF_PATH = "SPARK_JOB_CONF_PATH";

    public static String getConfigPipelinePath() {
        Dotenv dotenv = Dotenv.load();
        return dotenv.get(FLINK_JOB_CONF_PATH);
    }
}
