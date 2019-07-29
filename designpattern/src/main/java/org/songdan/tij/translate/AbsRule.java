package org.songdan.tij.translate;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * TODO completion javadoc.
 *
 * @author song dan
 * @since 13 四月 2018
 */
@JsonDeserialize(using = AbsRule.AbsRuleDeserializer.class)
public abstract class AbsRule {

	public abstract Expression toExpression();

	public static class AbsRuleDeserializer extends JsonDeserializer<AbsRule>{

		@Override
		public AbsRule deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
			ObjectMapper objectMapper = (ObjectMapper) jsonParser.getCodec();
			ObjectNode root = objectMapper.readTree(jsonParser);
			System.out.println(root);
			Class<? extends AbsRule> instanceClass;
			if (containField(root,"rules")) {
				instanceClass = CombinationRule.class;
			}
			else {
				instanceClass = Rule.class;
			}
			return objectMapper.convertValue(root,instanceClass);
		}

		private boolean containField(JsonNode json, String fieldName) {
			Iterator<Map.Entry<String, JsonNode>> fields = json.fields();
			while (fields.hasNext()) {
				Map.Entry<String, JsonNode> next = fields.next();
				if (next.getKey().equals(fieldName)) {
					return true;
				}
			}
			return false;
		}
	}

}
