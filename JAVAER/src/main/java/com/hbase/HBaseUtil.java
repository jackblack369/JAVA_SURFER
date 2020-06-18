package com.hbase;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HConstants;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class HBaseUtil {

    private static HBaseUtil hBaseUtil;

    private Configuration config = null;

    private Connection connection = null;

    private Map<String, Table> tableMap = new HashMap<String, Table>();

    private HBaseUtil() {

    }

    public static HBaseUtil getInstance() {
        if (hBaseUtil == null) {
            synchronized (HBaseUtil.class) {
                if (hBaseUtil == null) {
                    hBaseUtil = new HBaseUtil();
                }
            }
        }

        return hBaseUtil;
    }

    /**
     * 初始化Configuration与Connection
     */
    public void init(String zkAddress) {
        config = HBaseConfiguration.create();
        config.set(HConstants.ZOOKEEPER_QUORUM, zkAddress);

        try {
            connection = ConnectionFactory.createConnection(config);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(0);
        }
    }

    /**
     * 创建table
     */
    public void createTable(String tableName, byte[]... columnFamilies) {
        // HBase创建表的时候必须创建指定列族
        if (columnFamilies == null || columnFamilies.length == 0) {
            return ;
        }

        TableDescriptorBuilder tableDescriptorBuilder = TableDescriptorBuilder.newBuilder(TableName.valueOf(tableName));
        for (byte[] columnFamily : columnFamilies) {
            tableDescriptorBuilder.setColumnFamily(ColumnFamilyDescriptorBuilder.newBuilder(columnFamily).build());
        }

        try {
            Admin admin = connection.getAdmin();
            admin.createTable(tableDescriptorBuilder.build());
            // 这个Table连接存入内存中
            tableMap.put(tableName, connection.getTable(TableName.valueOf(tableName)));
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        }

    }

    public Table getTable(String tableName) {
        Table table = tableMap.get(tableName);
        if (table != null) {
            return table;
        }

        try {
            table = connection.getTable(TableName.valueOf(tableName));
            if (table != null) {
                // table对象存入内存
                tableMap.put(tableName, table);
            }

            return table;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}
