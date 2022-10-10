package com.haodf.dourw.config;

import com.github.shyiko.mysql.binlog.BinaryLogClient;
import com.github.shyiko.mysql.binlog.event.deserialization.EventDeserializer;
import com.haodf.dourw.Listener.BinLogListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Async;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * @description:
 * @author: drw
 * @create: 2022-10-10 16:00
 **/
@Slf4j
@Configuration
public class BinLogConfig {
    @Resource
    private BinLogMysqlConfig binLogMysqlConfig;
    @Resource
    private BinLogListener binLogListener;

    /**
     *binlog 监听器
     */
    @Async
    public void startBinLogListener() throws IOException {
        BinaryLogClient client = new BinaryLogClient(binLogMysqlConfig.getHost(), binLogMysqlConfig.getPort(),
                binLogMysqlConfig.getUser(), binLogMysqlConfig.getPasswd());

        EventDeserializer eventDeserializer = new EventDeserializer();
        eventDeserializer.setCompatibilityMode(
                EventDeserializer.CompatibilityMode.DATE_AND_TIME_AS_LONG,
                EventDeserializer.CompatibilityMode.CHAR_AND_BINARY_AS_BYTE_ARRAY
        );
        client.setServerId(binLogMysqlConfig.getServerId());
        client.setEventDeserializer(eventDeserializer);
        client.registerEventListener(binLogListener);
        client.connect();
    }
}
