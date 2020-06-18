package com.hbase;

import com.google.common.collect.Lists;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.Table;
import org.junit.Before;
import org.junit.Test;

public class HBaseUtilTest extends BaseHBaseUtilTest {

    @Before
    public void init() {
        hBaseUtil = HBaseUtil.getInstance();
        hBaseUtil.init("60.205.220.252");
    }

    /**
     * 创建表
     */
    @Test
    public void testCreateTable() {
        hBaseUtil.createTable(TABLE_NAME, COLUMN_FAMILY_PERSONAL_INFO, COLUMN_FAMILY_FAMILY_INFO);
    }

    /**
     * 添加数据
     */
    @Test
    public void testPut() throws Exception {
        Table table = hBaseUtil.getTable(TABLE_NAME);
        // 用户1，用户id：12345
        Put put1 = new Put("12345".getBytes());
        put1.addColumn(COLUMN_FAMILY_PERSONAL_INFO, COLUMN_NAME, "Lucy".getBytes());
        put1.addColumn(COLUMN_FAMILY_PERSONAL_INFO, COLUMN_AGE, "18".getBytes());
        put1.addColumn(COLUMN_FAMILY_PERSONAL_INFO, COLUMN_PHONE, "13511112222".getBytes());
        put1.addColumn(COLUMN_FAMILY_FAMILY_INFO, COLUMN_FATHER, "LucyFather".getBytes());
        put1.addColumn(COLUMN_FAMILY_FAMILY_INFO, COLUMN_MOTHER, "LucyMother".getBytes());
        // 用户2，用户id：12346
        Put put2 = new Put("12346".getBytes());
        put2.addColumn(COLUMN_FAMILY_PERSONAL_INFO, COLUMN_NAME, "Lily".getBytes());
        put2.addColumn(COLUMN_FAMILY_PERSONAL_INFO, COLUMN_AGE, "19".getBytes());
        put2.addColumn(COLUMN_FAMILY_PERSONAL_INFO, COLUMN_PHONE, "13522223333".getBytes());
        put2.addColumn(COLUMN_FAMILY_FAMILY_INFO, COLUMN_FATHER, "LilyFather".getBytes());
        put2.addColumn(COLUMN_FAMILY_FAMILY_INFO, COLUMN_MOTHER, "LilyMother".getBytes());
        // 用户3，用户id：12347
        Put put3 = new Put("12347".getBytes());
        put3.addColumn(COLUMN_FAMILY_PERSONAL_INFO, COLUMN_NAME, "James".getBytes());
        put3.addColumn(COLUMN_FAMILY_PERSONAL_INFO, COLUMN_AGE, "22".getBytes());
        put3.addColumn(COLUMN_FAMILY_FAMILY_INFO, COLUMN_FATHER, "JamesFather".getBytes());
        put3.addColumn(COLUMN_FAMILY_FAMILY_INFO, COLUMN_MOTHER, "JamesMother".getBytes());
        // 用户4，用户id：12447
        Put put4 = new Put("12447".getBytes());
        put4.addColumn(COLUMN_FAMILY_PERSONAL_INFO, COLUMN_NAME, "Micheal".getBytes());
        put4.addColumn(COLUMN_FAMILY_PERSONAL_INFO, COLUMN_AGE, "22".getBytes());
        put2.addColumn(COLUMN_FAMILY_PERSONAL_INFO, COLUMN_PHONE, "13533334444".getBytes());
        put4.addColumn(COLUMN_FAMILY_FAMILY_INFO, COLUMN_MOTHER, "MichealMother".getBytes());

        table.put(Lists.newArrayList(put1, put2, put3, put4));
    }

    @Test
    public void testGet() throws Exception {
        Table table = hBaseUtil.getTable(TABLE_NAME);
        // get1，拿到全部数据
        Get get1 = new Get("12345".getBytes());
        // get2，只拿personalInfo数据
        Get get2 = new Get("12346".getBytes());
        get2.addFamily(COLUMN_FAMILY_PERSONAL_INFO);

        Result[] results = table.get(Lists.newArrayList(get1, get2));
        if (results == null || results.length == 0) {
            return ;
        }

        for (Result result : results) {
            printResult(result);
        }
    }


    @Test
    public void testUpdate() throws Exception {
        Table table = hBaseUtil.getTable(TABLE_NAME);
        // 用户1，用户id：12345
        Put put = new Put("12346".getBytes());
        put.addColumn(COLUMN_FAMILY_PERSONAL_INFO, COLUMN_AGE, 1, "22".getBytes());
        table.put(put);
    }

    private void printResult(Result result) {
        System.out.println("====================分隔符====================");
        printBytes(result.getValue(COLUMN_FAMILY_PERSONAL_INFO, COLUMN_NAME));
        printBytes(result.getValue(COLUMN_FAMILY_PERSONAL_INFO, COLUMN_AGE));
        printBytes(result.getValue(COLUMN_FAMILY_PERSONAL_INFO, COLUMN_PHONE));
        printBytes(result.getValue(COLUMN_FAMILY_FAMILY_INFO, COLUMN_FATHER));
        printBytes(result.getValue(COLUMN_FAMILY_FAMILY_INFO, COLUMN_MOTHER));
    }

    private void printBytes(byte[] bytes) {
        if (bytes != null && bytes.length != 0) {
            System.out.println(new String(bytes));
        }
    }


}
