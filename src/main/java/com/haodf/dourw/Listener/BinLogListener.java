package com.haodf.dourw.Listener;

import com.github.shyiko.mysql.binlog.BinaryLogClient;
import com.github.shyiko.mysql.binlog.event.DeleteRowsEventData;
import com.github.shyiko.mysql.binlog.event.Event;
import com.github.shyiko.mysql.binlog.event.EventData;
import com.github.shyiko.mysql.binlog.event.TableMapEventData;
import com.github.shyiko.mysql.binlog.event.UpdateRowsEventData;
import com.github.shyiko.mysql.binlog.event.WriteRowsEventData;
import com.haodf.dourw.config.BinLogMysqlConfig;
import com.haodf.dourw.vo.ChangeEventVO;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @description:
 * @author: drw
 * @create: 2022-10-10 15:54
 **/
@Component
public class BinLogListener implements BinaryLogClient.EventListener {

    @Resource
    private BinLogMysqlConfig binLogMysqlConfig;


    /**
     *tableId -> tableName
     */
    private final ConcurrentHashMap<Long,String> tableMap = new ConcurrentHashMap<>();


    @Override
    public void onEvent(Event event) {
        // binlog事件
        EventData data = event.getData();
        if (Objects.nonNull(data)) {

            if (data instanceof TableMapEventData) {
                TableMapEventData tableMapEventData = (TableMapEventData) data;
                tableMap.put(tableMapEventData.getTableId(),tableMapEventData.getTable());
            }

            // update事件
            if (data instanceof UpdateRowsEventData) {
                UpdateRowsEventData updateRowsEventData = (UpdateRowsEventData) data;
                String tableName = tableMap.get(updateRowsEventData.getTableId());
                if (filterData(tableName)) {
                    ChangeEventVO changeEventVO = new ChangeEventVO("update", tableName, Long.parseLong(updateRowsEventData.getRows().get(0).getValue().toString()));
                    // 发送mq
                    return;
                }
            }

            // insert事件
            if (data instanceof WriteRowsEventData) {
                WriteRowsEventData writeRowsEventData = (WriteRowsEventData) data;
                String tableName = tableMap.get(writeRowsEventData.getTableId());
                if (filterData(tableName)) {
                    ChangeEventVO changeEventVO = new ChangeEventVO("insert", tableName, Long.parseLong(writeRowsEventData.getRows().get(0)[0].toString()));
                    // 发送mq
                    return;
                }
            }

             // delete事件
            if (data instanceof DeleteRowsEventData) {
                DeleteRowsEventData deleteRowsEventData = (DeleteRowsEventData) data;
                String tableName = tableMap.get(deleteRowsEventData.getTableId());
                if (filterData(tableName)) {
                    ChangeEventVO changeEventVO = new ChangeEventVO("delete", tableName, Long.parseLong(deleteRowsEventData.getRows().get(0).toString()));
                    // 发送mq
                    return;

                }
            }
        }

    }

    /**
     *监听的表
     */
    private boolean filterData(String tableName){
        return binLogMysqlConfig.getTables().contains(tableName);
    }
}
