package org.songdan.tij.algorithm.node;

import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.*;

import java.io.IOException;
import java.util.Map.Entry;

import static com.fasterxml.jackson.core.JsonToken.END_ARRAY;
import static com.fasterxml.jackson.core.JsonToken.END_OBJECT;

@SuppressWarnings("unchecked")
public class JacksonSupport {

    private static final ObjectMapper mapper = new ObjectMapper();

    static {

        // mapper.enable(SerializationFeature.INDENT_OUTPUT);

        mapper.configure(JsonParser.Feature.ALLOW_BACKSLASH_ESCAPING_ANY_CHARACTER, true);
        mapper.configure(JsonParser.Feature.ALLOW_COMMENTS, true);
        mapper.configure(JsonParser.Feature.ALLOW_NON_NUMERIC_NUMBERS, true);
        mapper.configure(JsonParser.Feature.ALLOW_NUMERIC_LEADING_ZEROS, true);
        mapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
        mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
        mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

//        SimpleModule module = new SimpleModule("DateTimeModule", Version.unknownVersion());
//        module.addDeserializer(Date.class, new DateJsonDeserializer());
//        module.addSerializer(Date.class, new DateJsonSerializer());
//        module.addSerializer(java.sql.Date.class, new SqlDateJsonSerializer());
//        module.addDeserializer(java.sql.Date.class, new SqlDateJsonDeserializer());
//        module.addSerializer(Timestamp.class, new TimestampJsonSerializer());
//        module.addDeserializer(Timestamp.class, new TimestampJsonDeserializer());
//        module.addSerializer(Time.class, new SqlTimeJsonSerializer());
//        module.addDeserializer(Time.class, new SqlTimeJsonDeserializer());
//        mapper.registerModule(module);

    }

    public static ValueNode parse(String json) {

        try {
            return mapper.readValue(json, ValueNode.class);
        } catch (Exception e) {
            throw new RuntimeException("json parse error :" + json.substring(0, Math.min(100, json.length())), e);
        }
    }

    public static void main(String[] args) {
        ValueNode parse = JacksonSupport.parse(
                "{\"orderPay\":{\"guaranteeAmount\":0.01,\"id\":0,\"orderId\":75800,\"orderPayRecordList\":[{\"amount\":0.01,\"command\":\"GUARANTEE\",\"createDate\":1392811949792,\"id\":0,\"orderId\":75800,\"refTransactionId\":\"qta_payvRjR140219201229784\",\"status\":\"GUARANTEE\",\"transactionId\":\"qta_payvRjR140219201229784\"}],\"payAmount\":10,\"payloadId\":\"0\",\"payloadVersion\":2,\"version\":2}}");
        System.out.println(parse);
    }

    public static <T> T parseJson(String json, Class<T> type) {
        try {
            return (T) mapper.readValue(json, type);
        } catch (JsonParseException e) {
            throw new RuntimeException("Deserialize from JSON failed.", e);
        } catch (JsonMappingException e) {
            throw new RuntimeException("Deserialize from JSON failed.", e);
        } catch (IOException e) {
            throw new RuntimeException("Deserialize from JSON failed.", e);
        }
    }

    public static <T> T parseJsonToList(String json, TypeReference<T> typeReference) {
        if (json == null || "".equals(json.trim())) {
            return null;
        }
        try {
            return (T) mapper.readValue(json, typeReference);
        } catch (Exception e) {
            throw new RuntimeException("Deserialize from JSON failed.", e);
        }
    }

    public static String toJson(Object obj) {
        try {
            return mapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("json format error: " + obj, e);
        }
    }

    public static byte[] convertIntoBytes(Object obj) {
        try {
            return mapper.writeValueAsBytes(obj);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("bytes format error: " + obj, e);
        }
    }

    public static <T> T parseBytesToObject(byte[] bytes, TypeReference<T> typeReference) {
        if (bytes == null || bytes.length == 0) {
            return null;
        }
        try {
            return (T) mapper.readValue(bytes, typeReference);
        } catch (Exception e) {
            throw new RuntimeException("Deserialize from bytes failed.", e);
        }
    }

    public static String toFormatJson(Object obj) {
        try {
            return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("json format error: " + obj, e);
        }
    }

    public static class ValueNodeSerializer extends JsonSerializer<ValueNode> {
        @Override
        public void serialize(ValueNode value, JsonGenerator jgen, SerializerProvider provider) throws IOException {
            write(value, jgen);
        }
    }

    public static class ValueNodeDeserializer extends JsonDeserializer<ValueNode> {
        @Override
        public ValueNode deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
            return parseToken(jp.getCurrentToken(), jp);
        }
    }

    public static void write(ValueNode node, JsonGenerator gen) throws IOException {
        switch (node.getType()) {
            case bool:
                gen.writeBoolean(node.toBooleanValue());
                break;
            case NULL:
                gen.writeNull();
                break;
            case number:
                writeNumber(node, gen);
                break;
            case string:
                gen.writeString(node.toString());
                break;
            case list:
                gen.writeStartArray();
                for (ValueNode child : node) {
                    write(child, gen);
                }
                gen.writeEndArray();
                break;
            case map:
                gen.writeStartObject();
                for (Entry<String, ValueNode> entry : node.entrySet()) {
                    gen.writeFieldName(entry.getKey());
                    write(entry.getValue(), gen);
                }
                gen.writeEndObject();
                break;
        }
    }

    private static void writeNumber(ValueNode node, JsonGenerator gen) throws IOException {
        Number num = node.toNumber();
        if (num instanceof Integer) {

            gen.writeNumber(num.intValue());
        } else if (num instanceof Long) {
            gen.writeNumber(num.longValue());
        } else if (num instanceof Double) {
            gen.writeNumber(num.doubleValue());
        } else {
            gen.writeNumber(num.floatValue());
        }
    }

    private static ValueNode parseToken(JsonToken token, JsonParser jp) throws IOException {
        switch (token) {
            case START_OBJECT:
                return parseMap(jp);
            case START_ARRAY:
                return parseArray(jp);
            case VALUE_STRING:
                return new StringNode(jp.getValueAsString());
            case VALUE_NUMBER_FLOAT:
            case VALUE_NUMBER_INT:
                return new NumericNode(jp.getNumberValue());
            case VALUE_NULL:
                return NullNode.getRoot();
            case VALUE_FALSE:
            case VALUE_TRUE:
                return new BooleanValue(jp.getBooleanValue());
            default:
                throw new JsonParseException("意外的的token: " + token, jp.getCurrentLocation());
        }
    }

    private static ValueNode parseArray(JsonParser jp) throws IOException {
        ListNode node = new ListNode();
        JsonToken token = jp.nextToken();
        while (token != END_ARRAY) {
            ValueNode vn = parseToken(token, jp);
            node.add(vn);
            token = jp.nextToken();
        }
        return node;
    }

    private static MapNode parseMap(JsonParser jp) throws IOException {
        MapNode node = new MapNode();
        while (jp.nextToken() != END_OBJECT) {
            String fieldName = jp.getCurrentName();
            ValueNode vn = parseToken(jp.nextToken(), jp);
            node.set(fieldName, vn);
        }
        return node;
    }

}
