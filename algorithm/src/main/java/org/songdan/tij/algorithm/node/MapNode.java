package org.songdan.tij.algorithm.node;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class MapNode extends AbstractNode {

    private static final long serialVersionUID = 697816776137475507L;
    private LinkedHashMap<String, ValueNode> map = new LinkedHashMap<String, ValueNode>();

    @Override
    public Type getType() {
        return Type.map;
    }

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public ValueNode get(String childName) {
        ValueNode node = map.get(childName);
        return node;
        // return node == null ? NullNode.getRoot() : node;
    }

    @Override
    public Set<Entry<String, ValueNode>> entrySet() {
        return Collections.unmodifiableSet(map.entrySet());
    }

    public Map<String, ValueNode> getMap() {
        return Collections.unmodifiableMap(map);
    }

    @Override
    public Set<String> keySet() {
        return map.keySet();
    }

    public int getIntValue(String key, int def) {
        return map.get(key).getIntValue(def);
    }

    public ValueNode set(String key, String value) {
        return map.put(key, new StringNode(value));
    }

    public ValueNode set(String key, float value) {
        return map.put(key, new NumericNode(value));
    }

    public ValueNode set(String key, int value) {
        return map.put(key, new NumericNode(value));
    }

    public ValueNode set(String key, ValueNode value) {
        return map.put(key, value);
    }

    public ValueNode remove(String key) {
        return map.remove(key);
    }

    @Override
    public String toJson() {
        return JacksonSupport.toJson(this);
    }
}
