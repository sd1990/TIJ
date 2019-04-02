package org.songdan.tij.algorithm.node;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

@JsonSerialize(using = JacksonSupport.ValueNodeSerializer.class)
@JsonDeserialize(using = JacksonSupport.ValueNodeDeserializer.class)
public interface ValueNode extends Iterable<ValueNode>, Serializable {

    Type getType();

    List<ValueNode> find(String... path);

    List<ValueNode> find(int count, String... path);

    List<ValueNode> operate(OpType opType, ValueNodeWrapper value, String... path);

    List<ValueNode> operate(OpType opType, int count, ValueNodeWrapper value, String... path);

    ValueNode firstChild(String... path);

    ValueNode get(int childIndex);

    int size();

    ValueNode get(String childName);

    Set<Entry<String, ValueNode>> entrySet();

    Set<String> keySet();

    Date toDateValue();

    Number toNumber();

    Boolean toBooleanValue();

    String toJson();

    String toStringWithType();

    int getIntValue(int def);

    float getFloatValue(float def);

    long getLongValue(long def);

    Map<Path, ValueNode> flatten();

    List<DiffValue> diff(ValueNode other);

    List<DiffValue> diff(ValueNode other, Set<String> watchPathSet, Map<String, Set<DiffValue>> watchDiffValue);

    enum Type {
        list, map, bool, number, string, NULL
    }

    enum OpType {
        find, replace;
    }

    // static interface DiffValue extends Comparable<DiffValue> {
    //
    // boolean getAction();
    //
    // Path getPath();
    //
    // ValueNode getValue();
    // }
}
