package com.jackson;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.util.Map;

import static com.fasterxml.jackson.core.JsonGenerator.Feature.WRITE_BIGDECIMAL_AS_PLAIN;

public class MySerializer extends JsonSerializer<Map> {

    private ObjectMapper mapper = new ObjectMapper();


    @Override
    public void serialize(Map value,
                          JsonGenerator gen,
                          SerializerProvider serializers)
            throws IOException {
        mapper.enable(WRITE_BIGDECIMAL_AS_PLAIN);
        StringWriter writer = new StringWriter();
//        mapper.writeValue(writer, value);
//        gen.writeFieldName(writer.toString());

        mapper.writeValueAsString(value)
    }
}
