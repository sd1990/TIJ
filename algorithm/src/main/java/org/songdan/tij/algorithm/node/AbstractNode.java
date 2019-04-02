package org.songdan.tij.algorithm.node;

import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.Map.Entry;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public abstract class AbstractNode implements ValueNode {

    private static final long serialVersionUID = -5030207966278794611L;
    private static final PathFormat parser = new PathFormat();
    private static final Pattern numberPattern = Pattern.compile("^[0-9]+$");
    private final static Logger LOGGER = LoggerFactory.getLogger(AbstractNode.class);

    private Splitter splitter = Splitter.on(".").trimResults().omitEmptyStrings();


    @Override
    public Iterator<ValueNode> iterator() {
        List<ValueNode> empty = Collections.emptyList();
        return empty.iterator();
    }

    @Override
    public ValueNode firstChild(String... paths) {
        List<ValueNode> list = find(1, paths);
        return list.size() > 0 ? list.get(0) : NullNode.getRoot();
    }

    @Override
    public List<ValueNode> find(String... paths) {
        return operate(OpType.find, 0, null, paths);
    }

    @Override
    public List<ValueNode> find(int count, String... paths) {
        return operate(OpType.find, count, null, paths);
    }

    @Override
    public List<ValueNode> operate(OpType opType, ValueNodeWrapper value, String... paths) {
        return operate(opType, 0, value, paths);
    }

    @Override
    public List<ValueNode> operate(OpType opType, int count, ValueNodeWrapper value, String... paths) {
        if (paths.length == 0)
            throw new IllegalArgumentException("paths must defined ");

        if (paths.length == 1 && paths[0].indexOf('.') != -1) {

            List<String> l = parser.parseLine(paths[0]);
            paths = new String[l.size()];
            l.toArray(paths);
        }

        List<ValueNode> list = new ArrayList<ValueNode>(count == 0 ? 16 : count);
        operate(opType, count == 0 ? Short.MAX_VALUE : count, this, list, paths, 0, value);
        return list;
    }

    private void operate(OpType opType, int count, ValueNode node, List<ValueNode> list, String[] paths, int depth,
                         ValueNodeWrapper value) {
        if (list.size() >= count || node.getType() == Type.NULL)
            return;
        Type type = node.getType();

        if (type == Type.map)
            opMap(opType, count, (MapNode) node, list, paths, depth, value);
        else if (type == Type.list) {
            opList(opType, count, (ListNode) node, list, paths, depth, value);
        }
    }

    private void opList(OpType opType, int count, ListNode node, List<ValueNode> list, String[] paths, int depth,
                        ValueNodeWrapper value) {

        int nextDepth = depth + 1;
        boolean end = nextDepth == paths.length;
        String key = paths[depth];
        List<ValueNode> selected;
        //既不为* 也不是数字则设置为*
        if (!StringUtils.equals(key, "*") && !numberPattern.matcher(key).matches()){
            key = "*";
            nextDepth = nextDepth -1;
            end = false;
        }

        if (key == null || key.equals("") || key.equals("*")) {
            selected = node.getAll();
        } else {
            int index;
            try {
                index = Integer.parseInt(key);
            } catch (NumberFormatException e) {
                return;
            }

            if (index >= node.size() || index < 0)
                return;
            selected = Arrays.asList(node.get(index));
        }

        for (ValueNode vn : selected) {
            if (vn == null) {
                continue;
            }
            if (end) {

                switch (opType) {
                    case find:
                        break;
                    case replace:
                        List<ValueNode> original = node.update(value.getList());
                        LOGGER.debug("original: {}, replace: {}, paths: {}", original, value, paths);
                        break;
                }
                list.add(vn);

                if (list.size() >= count)
                    return;
            } else
                operate(opType, count, vn, list, paths, nextDepth, value);
        }

    }

    private void opMap(OpType opType, int count, MapNode node, List<ValueNode> list, String[] paths, int depth,
                       ValueNodeWrapper value) {

        int nextDepth = depth + 1;
        boolean end = nextDepth == paths.length;
        String key = paths[depth];

        Collection<String> keys;

        if (key == null || key.equals("") || key.equals("*")) {
            keys = node.keySet();
        } else
            keys = Arrays.asList(key);

        for (String k : keys) {
            ValueNode vn = node.get(k);
            if (vn == null)
                continue;

            if (end) {
                switch (opType) {
                    case find:
                        break;
                    case replace:
                        ValueNode original = node.set(k, value.getSingle());
                        LOGGER.debug("original: {}, replace: {}, paths: {}", original, value, paths);
                        break;
                }
                list.add(vn);

                if (list.size() >= count)
                    return;
            } else
                operate(opType, count, vn, list, paths, nextDepth, value);
        }

    }

    private void walk(ValueNode node, Map<Path, ValueNode> map, Path path) {
        Type type = node.getType();
        switch (type) {
            case map:
                map.put(path, EmptyNode.MAP);
                for (Entry<String, ValueNode> entry : node.entrySet()) {
                    walk(entry.getValue(), map, path.append(entry.getKey()));
                }
                break;
            case list:
                map.put(path, EmptyNode.LIST);
                int i = 0;
                for (ValueNode entry : node) {
                    walk(entry, map, path.append((i++) + ""));
                }
                break;
            default:
                map.put(path, node);
        }
    }

    public Map<Path, ValueNode> flatten() {
        Map<Path, ValueNode> map = Maps.newConcurrentMap();
        walk(this, map, Path.GOD);
        return map;
    }

    private void addDiff(List<DiffValue> diffs, DiffValue.DiffType diffType, Path path, ValueNode oNode,
                         ValueNode tNode) {
        // if (diffType != DiffValue.DiffType.CHANGE) {
        // //如果是add或者是delete,需要把子的diff都删除掉
        // ;
        // for (Iterator<DiffValue> it = diffs.iterator(); it.hasNext();) {
        // DiffValue diffValue = it.next();
        // if (diffValue.getPath().isParent(path)) {
        // it.remove();
        // }
        // }
        // }
        diffs.add(new DiffValue(diffType, path.getPath(), oNode, tNode));
    }

    public List<DiffValue> diff(ValueNode other) {
        return diff(other, null, Maps.newHashMap());
    }

    public List<DiffValue> diff(ValueNode other, Set<String> watchPathSet, Map<String, Set<DiffValue>> watchDiffValue) {
        Map<Path, ValueNode> t = this.flatten();
        Map<Path, ValueNode> o = other.flatten();

        List<DiffValue> diffs = Lists.newLinkedList();

        Map<String, Pattern> watchPathPatternMap = Maps.newHashMap();
        if (CollectionUtils.isNotEmpty(watchPathSet)) {
            for (String path : watchPathSet) {
                //获取regex path
                String patternPath = path.substring(0, path.lastIndexOf("."));
                watchPathPatternMap.put(path, Pattern.compile(patternPath));
            }
        }

        for (Entry<Path, ValueNode> entry : t.entrySet()) {
            Path path = entry.getKey();
            ValueNode tNode = entry.getValue();
            ValueNode oNode = o.remove(path);
            if (!tNode.equals(oNode)) {
                if (oNode != null) {
                    addDiff(diffs, DiffValue.DiffType.CHANGE, path, oNode, tNode);

                } else {
                    addDiff(diffs, DiffValue.DiffType.ADD, path, null, this.find(path.getPath()).get(0));
                }
                addWatchValue(path.getPath(), other, watchPathPatternMap, watchDiffValue);
            }
        }

        for (Entry<Path, ValueNode> entry : o.entrySet()) {
            addDiff(diffs, DiffValue.DiffType.DELETE, entry.getKey(), other.find(entry.getKey().getPath()).get(0), null);
            addWatchValue(entry.getKey().getPath(), other, watchPathPatternMap, watchDiffValue);
        }
        return diffs;
    }

    /**
     * 添加上watch的值
     *
     * @param path
     * @param other
     * @param watchPathPatternMap
     */
    private void addWatchValue(String path, ValueNode other, Map<String, Pattern> watchPathPatternMap,
                               Map<String, Set<DiffValue>> watchDiffValueMap) {
        //判断是否存在watch的PathString
        List<String> watchPathList = getWatchPath(path, watchPathPatternMap);
        //获取old 和 new的 watch
        if (CollectionUtils.isNotEmpty(watchPathList)) {
            for (String watchPath : watchPathList) {
                //构造具体path
                String realPath = genRealPath(path, watchPath);
                //old
                List<ValueNode> oldValueNodes = other.find(realPath);
                //new
                List<ValueNode> newValueNodes = this.find(realPath);
                if ((CollectionUtils.isNotEmpty(oldValueNodes) && oldValueNodes.size() > 1) ||
                        (CollectionUtils.isNotEmpty(newValueNodes) && newValueNodes.size() > 1)) {
                    throw new RuntimeException(watchPath + "匹配值必须唯一");
                }
                Set<DiffValue> watchDiffValues = watchDiffValueMap.get(watchPath);
                if (watchDiffValues == null) {
                    watchDiffValues = Sets.newHashSet();
                }
                //判断是否已经存在该realPath
                Set<String> realPathSets = watchDiffValues.stream().map(DiffValue::getPath).collect(Collectors.toSet());
                //如果两个都为空则直接返回,不谢watchDiffValueMap
                if (CollectionUtils.isEmpty(oldValueNodes) && CollectionUtils.isEmpty(newValueNodes)){
                    continue;
                }
                if (!realPathSets.contains(realPath)) {
                    if (CollectionUtils.isEmpty(oldValueNodes) && CollectionUtils.isNotEmpty(newValueNodes)) {
                        watchDiffValues.add(new DiffValue(DiffValue.DiffType.ADD, realPath, null, newValueNodes.get(0)));
                    } else if (CollectionUtils.isNotEmpty(oldValueNodes) && CollectionUtils.isEmpty(newValueNodes)) {
                        watchDiffValues.add(new DiffValue(DiffValue.DiffType.DELETE, realPath, oldValueNodes.get(0), null));
                    } else if (StringUtils.equals(oldValueNodes.get(0).toString(), newValueNodes.get(0).toString())) {
                        watchDiffValues.add(new DiffValue(DiffValue.DiffType.CHANGE, realPath, oldValueNodes.get(0), newValueNodes.get(0)));
                    } else {
                        watchDiffValues.add(new DiffValue(DiffValue.DiffType.UNCHANGED, realPath, oldValueNodes.get(0), newValueNodes.get(0)));
                    }
                    watchDiffValueMap.put(watchPath, watchDiffValues);
                }
            }
        }

    }

    private String genRealPath(String path, String watchPath) {
        String result = String.valueOf(watchPath);
        for (String subStr : splitter.splitToList(path)) {
            try {
                int index = Integer.parseInt(subStr);
                result = result.replaceFirst("\\*", subStr);
            } catch (Exception e) {

            }
        }
        return result;
    }

    private List<String> getWatchPath(String path, Map<String, Pattern> watchPathPatternMap) {
        List<String> resultList = Lists.newArrayList();
        if (MapUtils.isNotEmpty(watchPathPatternMap)) {
            for (Entry<String, Pattern> entry : watchPathPatternMap.entrySet()) {
                if (entry.getValue().matcher(path).find()) {
                    resultList.add(entry.getKey());
                }
            }
        }
        return resultList;
    }

    @Override
    public ValueNode get(int childIndex) {
        return null;
        // return NullNode.getRoot();
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public ValueNode get(String childName) {
        return null;
        // return NullNode.getRoot();
    }

    @Override
    public Set<Entry<String, ValueNode>> entrySet() {
        return Collections.emptySet();
    }

    @Override
    public Set<String> keySet() {
        return Collections.emptySet();
    }

    @Override
    public Boolean toBooleanValue() {
        return false;
    }

    @Override
    public Number toNumber() {
        return null;
    }

    @Override
    public Date toDateValue() {
        return null;
    }

    @Override
    public String toStringWithType() {
        return "@" + getType() + "<" + toString() + ">";
    }

    public String toString() {
        return toJson();
    }

    @Override
    public int getIntValue(int def) {
        Number num = toNumber();
        return num == null ? def : num.intValue();
    }

    @Override
    public long getLongValue(long def) {
        Number num = toNumber();
        return num == null ? def : num.longValue();
    }

    @Override
    public float getFloatValue(float def) {
        Number num = toNumber();
        return num == null ? def : num.floatValue();
    }


}
