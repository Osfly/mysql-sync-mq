package com.haodf.dourw.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;


/**
 *binlog 监听启动
 */
@Component
public class BinlogClientRunner implements CommandLineRunner {

    @Resource
    private BinLogConfig binLogConfig;

    @Override
    public void run(String... args) throws Exception {
        binLogConfig.startBinLogListener();
    }
}
