package com.haodf.dourw;

import com.haodf.communal.support.application.AbstractApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @description: 启动程序
 * @author: drw
 * @create: 2022-10-09 18:23
 **/

@SpringBootApplication
@EnableAsync
public class MainApplication extends AbstractApplication {
    public static void main(String[] args) {
        new MainApplication().start(args);
    }
}