package org.songdan.tij.designpattern.composite;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.google.common.collect.Lists;

import lombok.Data;

/**
 * 表单检查基类,使用组合模式来应对未来多级的情况
 *
 * @author song dan
 * @since 31 十月 2017
 */
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonDeserialize(using = Item.ItemDeserializer.class)
public abstract class Item {

    private String name;

    public static void main(String[] args) throws IOException {
        Item item = new ItemDetail();
        item.setName("电视机");
        item.setStatus(1);
        Remark remark = new Remark();
        remark.setId(1);
        remark.setUsername("songdan");
        remark.setUserNo("100000");
        remark.setTime(new Date());
        remark.setContent("hello");
        item.setRemarks(Lists.newArrayList(remark));
        System.out.println(JsonUtil.toJson(Lists.newArrayList(item)));

        List<Item> items = JsonUtil.ofList(JsonUtil.toJson(Lists.newArrayList(item)), Item.class);
        System.out.println(JsonUtil.toJson(items));

        Item itemMenu = new ItemMenu();
        itemMenu.add(item);
        itemMenu.setName("家电");

        Item topItem = new ItemMenu();
        topItem.setName("卧室");
        topItem.add(itemMenu);
        topItem.add(item);

        System.out.println(JsonUtil.toJson(Lists.newArrayList(topItem)));
        items = JsonUtil.ofList(JsonUtil.toJson(Lists.newArrayList(topItem)), Item.class);
        System.out.println(JsonUtil.toJson(items));

    }

    public Integer getStatus() {
        return null;
    }

    public void setStatus(Integer status) {
        throw new UnsupportedOperationException();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Item> getChildren() {
        return null;
    }

    public List<Remark> getRemarks() {
        return null;
    }

    public void setRemarks(List<Remark> remarks) {
        throw new UnsupportedOperationException();
    }

    public void add(Item item) {
        throw new UnsupportedOperationException();
    }

    public Integer status() {
        return null;
    }

    public abstract boolean isMenu();

    /**
     * 检查项列表
     */
    public static class ItemMenu extends Item {

        private List<Item> children = new ArrayList<>();

        @Override
        public void add(Item item) {
            children.add(item);
        }

        @Override
        public boolean isMenu() {
            return true;
        }

        @Override
        public List<Item> getChildren() {
            return children;
        }

        public void setChildren(List<Item> children) {
            this.children = children;
        }

    }

    /**
     * 具体的检查项
     */
    public static class ItemDetail extends Item {

        private List<Remark> remarks = new ArrayList<>();

        private Integer status;

        @Override
        public List<Remark> getRemarks() {
            return remarks;
        }

        public void setRemarks(List<Remark> remarks) {
            this.remarks = remarks;
        }

        @Override
        public boolean isMenu() {
            return false;
        }

        @Override
        public Integer getStatus() {
            return status;
        }

        public void setStatus(Integer status) {
            this.status = status;
        }
    }

    /**
     * 只需要对反序列化做工作即可
     */
    public static class ItemDeserializer extends StdDeserializer<Item> {

        public ItemDeserializer() {
            this(null);
        }

        public ItemDeserializer(Class<?> vc) {
            super(vc);
        }

        @Override
        public Item deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
            JsonNode json = jp.getCodec().readTree(jp);

            if (!containField(json, "status")) {

                ItemMenu itemMenu = new ItemMenu();
                itemMenu.setName(json.findPath("name").asText(""));
                JsonNode children = json.findPath("children");
                itemMenu.setChildren(JsonUtil.ofList(children.toString(), Item.class));
                return itemMenu;
            } else {
                ItemDetail itemDetail = new ItemDetail();
                itemDetail.setName(json.findPath("name").asText(""));
                itemDetail.setStatus(json.findPath("status").asInt());
                JsonNode remarks = json.findPath("remarks");
                itemDetail.setRemarks(JsonUtil.ofList(remarks.toString(), Remark.class));
                return itemDetail;
            }
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

    @Data
    public static class Remark {

        private Integer id;

        private String userNo;

        private String username;

        private Date time;

        private String content;

    }

}
