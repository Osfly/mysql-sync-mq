package com.haodf.dourw.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * @description:
 * @author: drw
 * @create: 2022-10-09 18:21
 **/
@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "binlog.mysql")
public class BinLogMysqlConfig {

    private Long serverId;
    private String host;
    private Integer port;
    private String user;
    private String passwd;

    /**
     *需要监听的表
     */
    private List<String> tables;
    
}
