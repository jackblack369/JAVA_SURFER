package com.hbase;

public class BaseHBaseUtilTest {

    protected static final String TABLE_NAME = "student";

    protected static final byte[] COLUMN_FAMILY_PERSONAL_INFO = "personalInfo".getBytes();

    protected static final byte[] COLUMN_FAMILY_FAMILY_INFO = "familyInfo".getBytes();

    protected static final byte[] COLUMN_NAME = "name".getBytes();

    protected static final byte[] COLUMN_AGE = "age".getBytes();

    protected static final byte[] COLUMN_PHONE = "phone".getBytes();

    protected static final byte[] COLUMN_FATHER = "father".getBytes();

    protected static final byte[] COLUMN_MOTHER = "mother".getBytes();

    protected HBaseUtil hBaseUtil;

}
