package com.uuid;

import java.util.UUID;

/**
 * @author dongwei
 * 2018/8/24 14:00
 **/
public class IdUtil {

    static UUID uuid = UUID.randomUUID();

    public static void main(String[] args) {
        System.out.println("uuid:" + uuid.toString());
    }
}
