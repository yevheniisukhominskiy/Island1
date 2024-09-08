package com.eugened.utils;

import com.eugened.entity.object.organism.Organism;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class ClassKeyMapDeserializer extends JsonDeserializer<Map<Class<? extends Organism>, Double>> {

    @Override
    public Map<Class<? extends Organism>, Double> deserialize(JsonParser jp, DeserializationContext ctxt)
            throws IOException, JsonProcessingException {
        Map<Class<? extends Organism>, Double> result = new HashMap<>();
        JsonNode node = jp.getCodec().readTree(jp);

        Iterator<Map.Entry<String, JsonNode>> fields = node.fields();
        while (fields.hasNext()) {
            Map.Entry<String, JsonNode> field = fields.next();
            String className = field.getKey();
            Double probability = field.getValue().asDouble();

            try {
                @SuppressWarnings("unchecked")
                Class<? extends Organism> clazz = (Class<? extends Organism>) Class.forName(className);
                result.put(clazz, probability);
            } catch (ClassNotFoundException e) {
                throw new IOException("Class not found: " + className, e);
            }
        }

        return result;
    }
}


