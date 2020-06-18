package com.jackson;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.MapSerializer;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class TestMapWithBigDecimal {

    @JsonSerialize(keyUsing = MySerializer.class)
    static Map<String, BigDecimal> testMap = new HashMap<>();

    static ObjectMapper mapper = new ObjectMapper();

    public static void main(String[] args) throws JsonProcessingException {

        testMap.put("a",new BigDecimal("12345678"));
        String jsonResult = mapper.writerWithDefaultPrettyPrinter()
                .writeValueAsString(testMap);
        System.out.println(jsonResult);
    }
}
