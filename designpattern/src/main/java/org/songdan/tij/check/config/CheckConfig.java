package org.songdan.tij.check.config;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.songdan.tij.check.checker.Checker;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

@JsonDeserialize(using = CheckConfig.CheckConfigDeserializer.class)
public abstract class CheckConfig implements Ordered {

    public abstract Checker getChecker();

    public static class CheckConfigDeserializer extends JsonDeserializer<CheckConfig> {

        @Override
        public CheckConfig deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
            ObjectMapper objectMapper = (ObjectMapper) jsonParser.getCodec();
            TreeNode treeNode = objectMapper.readTree(jsonParser);
            Class<? extends CheckConfig> instanceClass;
            if (containField(treeNode, "checkConfigList")) {
                instanceClass = CheckConfigGroup.class;
            } else {
                instanceClass = CheckConfigItem.class;
            }
            return objectMapper.convertValue(treeNode, instanceClass);
        }

        private boolean containField(TreeNode json, String fieldName) {
            Iterator<String> fields = json.fieldNames();
            while (fields.hasNext()) {
                String field = fields.next();
                if (field.equals(fieldName)) {
                    return true;
                }
            }
            return false;
        }
    }

}
