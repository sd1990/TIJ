package org.songdan.tij.file.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import org.songdan.tij.generics.Sets;

/**
 * TODO completion javadoc.
 *
 * @author song dan
 * @since 24 十一月 2017
 */
public class Fileparser5 {

    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("/Users/songdan/Desktop/repository_out.txt");
        BufferedReader reader = null;
        Set<String> outSet = new LinkedHashSet<>();
        Set<String> displaySet = new LinkedHashSet<>();
        try {
            // 1. 获取所有的调出信息
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            String str;
            while ((str = reader.readLine()) != null) {
                String[] split = str.split("\\s+");
                outSet.add(split[0] + "_" + split[1]);
            }
            System.out.println(outSet.size());

            // 2. 所有的陈列信息
            file = new File("/Users/songdan/Desktop/allSku.txt");
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            while ((str = reader.readLine()) != null) {
                displaySet.add(str);
            }
            // 3. 对比所有的差异
            BufferedWriter writer = new BufferedWriter(new FileWriter("/Users/songdan/Desktop/diff_sku2.csv"));
            Set<String> diffSet = outSet.parallelStream().filter(sku -> !displaySet.contains(sku))
                    .collect(Collectors.toSet());
            List<ShelfGoodsRelation> diffGoodsSet = new ArrayList<>();
            for (String s : diffSet) {
                String[] split = s.split("_");
                if (split[1].startsWith("82")) {
                    continue;
                }
                diffGoodsSet.add(new ShelfGoodsRelation(split[0], split[1]));
                writer.write(s + "," + split[0] + "," + split[1] + ",");
                writer.newLine();
            }
            writer.flush();
            System.out.println("差异的商品陈列是:" + diffGoodsSet.size());
            Set<Shelf> shelfSet = new LinkedHashSet<>();
            // 获取所有的点位信息
            file = new File("/Users/songdan/Desktop/shelfInfo.csv");
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            while ((str = reader.readLine()) != null) {
                String[] shopInfo = str.split(",");
                shelfSet.add(new Shelf(shopInfo[0], shopInfo[1].toUpperCase(), shopInfo[0].startsWith("100")));
            }

            // 分组丢失陈列的点位
            Map<String, List<ShelfGoodsRelation>> loseShelfSkuMap = diffGoodsSet.parallelStream()
                    .collect(Collectors.groupingBy(ShelfGoodsRelation::getShelfCode));
            // 组装点位信息的丢失sku信息
            Set<Shelf> targetSet = shelfSet.parallelStream()
                    .filter(shelf -> loseShelfSkuMap.containsKey(shelf.getShelfCode())).collect(Collectors.toSet());
            System.out.println("差异的点位是:" + targetSet.size());
            targetSet.forEach(shelf -> {
                shelf.setLoseSkus(loseShelfSkuMap.get(shelf.getShelfCode()).stream().map(ShelfGoodsRelation::getSkuCode)
                        .collect(Collectors.toSet()));
            });

            Map<String, Shelf> targetShelfMap = targetSet.stream()
                    .collect(Collectors.toMap(Shelf::getShelfCode, shelf -> shelf));

            // 每个点位的拨出的设备数量和设备类型
            file = new File("/Users/songdan/Desktop/shelf_device.csv");
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            int count = 0;
            while ((str = reader.readLine()) != null) {
                count++;
                String[] deviceInfo = str.split(",");
                if (str.startsWith(",")) {
                    break;
                }
                Shelf shelf = targetShelfMap.get(deviceInfo[0]);
                if (Objects.isNull(shelf)) {
                    continue;
                }
                // int number = Integer.parseInt(deviceInfo[2]);
                int number = 1;
                switch (deviceInfo[1]) {
                case "D":
                    shelf.setBigNum(number);
                    break;
                case "X":
                    shelf.setSmallNum(number);
                    break;
                case "R":
                    shelf.setHotNum(number);
                    break;
                case "L":
                    shelf.setColdNum(number);
                    break;
                }
            }
            Set<Shelf> bjSet = new HashSet<>();
            Set<Shelf> noBjSet = new HashSet<>();
            for (Shelf shelf : targetShelfMap.values()) {
                if (shelf.isBj()) {
                    bjSet.add(shelf);
                } else {
                    noBjSet.add(shelf);
                }
            }
            // 拿到模版，根据点位和每个点位缺少的sku以及设备类型,组合生成shelfGoodsRelation

            SkuTemplate bjTemplate = new SkuTemplate("bj").invoke();
            SkuTemplate noBjTemplate = new SkuTemplate("no-bj").invoke();

            Set<ShelfGoodsRelation> allRelation = new LinkedHashSet<>();

            allRelation.addAll(generate(bjSet, bjTemplate));

            allRelation.addAll(generate(noBjSet, noBjTemplate));

            System.out.println("补充 size is " + diffGoodsSet.size());
            System.out.println("差异 size is " + allRelation.size());
            Set<ShelfGoodsRelation> difference = Sets.difference(new HashSet<>(allRelation), new HashSet<>(diffGoodsSet));
            System.out.println("差集是："+difference);
            // 生成sql文件
            BufferedWriter bw = new BufferedWriter(new FileWriter(new File("/Users/songdan/Desktop/diff.sql")));
            for (ShelfGoodsRelation shelfGoodsRelation : allRelation) {
                bw.write(shelfGoodsRelation.insertSql());
                bw.newLine();
            }
            bw.flush();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != reader) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static List<ShelfGoodsRelation> generate(Set<Shelf> bjSet, SkuTemplate bjTemplate) {
        List<ShelfGoodsRelation> allRelation = new ArrayList<>(1000);
        bjSet.stream().forEach(shelf -> {
            List<ShelfGoodsRelation> relationList = new ArrayList<>(100);
            List<SkuTemplate.Sku> normalList = bjTemplate.getNormalList();
            List<SkuTemplate.Sku> hotList = bjTemplate.getHotList();
            List<SkuTemplate.Sku> coldList = bjTemplate.getColdList();

            relationList.addAll(shelf.normalGoods(normalList));
            relationList.addAll(shelf.smallGoods(normalList));
            relationList.addAll(shelf.hotGoods(hotList));
            relationList.addAll(shelf.coldGoods(coldList));


            Map<String, ShelfGoodsRelation> goodsRelationMap = relationList.stream().distinct().collect(Collectors.toMap(ShelfGoodsRelation::getSkuCode, shelfGoodsRelation -> shelfGoodsRelation));

//            Map<String, SkuTemplate.Sku> normalMap = normalList.stream().collect(CollectorsDemo.toMap(SkuTemplate.Sku::getCode, sku -> sku));
//            Map<String, SkuTemplate.Sku> hotMap = hotList.stream().collect(CollectorsDemo.toMap(SkuTemplate.Sku::getCode, sku -> sku));
//            Map<String, SkuTemplate.Sku> coldMap = coldList.stream().collect(CollectorsDemo.toMap(SkuTemplate.Sku::getCode, sku -> sku));
            //将没有出现在模版中的差异sku补上
            Set<String> loseSkus = new HashSet<>(shelf.getLoseSkus());
            List<ShelfGoodsRelation> other = loseSkus.stream().filter(s -> {
                return !goodsRelationMap.containsKey(s);
            }).map(s -> {
                ShelfGoodsRelation shelfGoodsRelation = new ShelfGoodsRelation(shelf.getShelfCode(), s);
                shelfGoodsRelation.setSkuType("");
                shelfGoodsRelation.setStatusWish(0);
                shelfGoodsRelation.setNum(1);
                return shelfGoodsRelation;
            }).collect(Collectors.toList());
            if (relationList.size() == 0) {
                System.out.println(shelf);
            }
            allRelation.addAll(relationList);
            allRelation.addAll(other);
        });
        return allRelation;
    }

}

class ShelfGoodsRelation {

    private String shelfCode;

    private String skuCode;

    private String skuType;

    private int num;

    private int equipmentType;

    private int statusWish;

    public ShelfGoodsRelation(String shelfCode, String skuCode) {
        this.shelfCode = shelfCode;
        this.skuCode = skuCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ShelfGoodsRelation that = (ShelfGoodsRelation) o;

        if (shelfCode != null ? !shelfCode.equals(that.shelfCode) : that.shelfCode != null) return false;
        return skuCode != null ? skuCode.equals(that.skuCode) : that.skuCode == null;
    }

    @Override
    public int hashCode() {
        int result = shelfCode != null ? shelfCode.hashCode() : 0;
        result = 31 * result + (skuCode != null ? skuCode.hashCode() : 0);
        return result;
    }

    public String insertSql() {
        return String.format(
                "insert into shelf_goods_relation (shelf_code,sku_code, sku_type, status, status_wish,amount,equipment_type) values('%s','%s','%s','Y',%s,%s,%s);",
                getShelfCode(), getSkuCode(), getSkuType(), getStatusWish(), getNum(), getEquipmentType());
    }

    public String getShelfCode() {
        return shelfCode;
    }

    public void setShelfCode(String shelfCode) {
        this.shelfCode = shelfCode;
    }

    public String getSkuCode() {
        return skuCode;
    }

    public void setSkuCode(String skuCode) {
        this.skuCode = skuCode;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getSkuType() {
        return skuType;
    }

    public void setSkuType(String skuType) {
        this.skuType = skuType;
    }

    public int getEquipmentType() {
        return equipmentType;
    }

    public void setEquipmentType(int equipmentType) {
        this.equipmentType = equipmentType;
    }

    public int getStatusWish() {
        return statusWish;
    }

    public void setStatusWish(int statusWish) {
        this.statusWish = statusWish;
    }
}

class ShelfDevice {
    private String shelfCode;

    private String deviceType;

    private int number;

    public ShelfDevice(String shelfCode, String deviceType, int number) {
        this.shelfCode = shelfCode;
        this.deviceType = deviceType;
        this.number = number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        ShelfDevice that = (ShelfDevice) o;

        if (shelfCode != null ? !shelfCode.equals(that.shelfCode) : that.shelfCode != null)
            return false;
        return deviceType != null ? deviceType.equals(that.deviceType) : that.deviceType == null;
    }

    @Override
    public int hashCode() {
        int result = shelfCode != null ? shelfCode.hashCode() : 0;
        result = 31 * result + (deviceType != null ? deviceType.hashCode() : 0);
        return result;
    }
}

class Shelf {
    private String shelfCode;

    private String level;

    private boolean isBj;

    private int coldNum;

    private int hotNum;

    private int bigNum;

    private int smallNum;

    private Set<String> loseSkus;

    public Shelf(String shelfCode, String level, boolean isBj) {
        this.shelfCode = shelfCode;
        this.level = level;
        this.isBj = isBj;
    }

    public List<ShelfGoodsRelation> normalGoods(List<SkuTemplate.Sku> normalList) {
        // NORAML_TYPE(1, "常温类型"),
        // COLD_TYPE(2, "冷柜类型"),
        // HEAT_TYPE(3, "热柜类型");
        return getShopSkuRelations(normalList, getBigNum(), EqumentType.NORAML_TYPE, true);
    }

    public List<ShelfGoodsRelation> smallGoods(List<SkuTemplate.Sku> normalList) {
        return getShopSkuRelations(normalList, getBigNum(), EqumentType.NORAML_TYPE, false);
    }

    private List<ShelfGoodsRelation> getShopSkuRelations(List<SkuTemplate.Sku> normalList, int num,
            EqumentType equmentType, boolean isBig) {
        if (num == 0) {
            return Collections.emptyList();
        }
        List<ShelfGoodsRelation> list = new ArrayList<>();
        for (SkuTemplate.Sku sku : normalList) {
            if (!loseSkus.contains(sku.getCode())) {
//                System.out.println(shelfCode+":"+loseSkus + "不包含:" + sku.getCode() + "设备类型:" + equmentType.getCode());
                continue;
            }
            if (generateNum(sku, num, isBig) == 0) {
                continue;
            }
            ShelfGoodsRelation shelfGoodsRelation = new ShelfGoodsRelation(getShelfCode(), sku.getCode());
            shelfGoodsRelation.setEquipmentType(equmentType.getCode());
            shelfGoodsRelation.setNum(generateNum(sku, num, isBig));
            shelfGoodsRelation.setSkuType(sku.getSkuType());
            shelfGoodsRelation.setStatusWish(1);
            list.add(shelfGoodsRelation);

        }
        return list;
    }

    protected int generateNum(SkuTemplate.Sku sku, int num, boolean isBig) {
        if (level.toLowerCase().equals("a")) {
            if (!isBig) {
                return sku.getBNum() * num;
            } else {
                return sku.getANum() * num;
            }
        } else if (level.toLowerCase().equals("b")) {
            return sku.getBNum() * num;
        } else {
            return sku.getCNum() * num;

        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Shelf shelf = (Shelf) o;

        return shelfCode != null ? shelfCode.equals(shelf.shelfCode) : shelf.shelfCode == null;
    }

    @Override
    public int hashCode() {
        return shelfCode != null ? shelfCode.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Shelf{" + "shelfCode='" + shelfCode + '\'' + ", coldNum=" + coldNum + ", hotNum=" + hotNum + ", bigNum="
                + bigNum + ", smallNum=" + smallNum + ", loseSkus=" + loseSkus + '}';
    }

    public List<ShelfGoodsRelation> hotGoods(List<SkuTemplate.Sku> hotList) {
        return getShopSkuRelations(hotList, getHotNum(), EqumentType.HEAT_TYPE, true);
    }

    public List<ShelfGoodsRelation> coldGoods(List<SkuTemplate.Sku> coldList) {

        return getShopSkuRelations(coldList, getColdNum(), EqumentType.COLD_TYPE, true);
    }

    public String getShelfCode() {
        return shelfCode;
    }

    public void setShelfCode(String shelfCode) {
        this.shelfCode = shelfCode;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public boolean isBj() {
        return isBj;
    }

    public void setBj(boolean bj) {
        isBj = bj;
    }

    public Set<String> getLoseSkus() {
        return loseSkus;
    }

    public void setLoseSkus(Set<String> loseSkus) {
        this.loseSkus = loseSkus;
    }

    public int getColdNum() {
        return coldNum;
    }

    public void setColdNum(int coldNum) {
        this.coldNum = coldNum;
    }

    public int getHotNum() {
        return hotNum;
    }

    public void setHotNum(int hotNum) {
        this.hotNum = hotNum;
    }

    public int getBigNum() {
        return bigNum;
    }

    public void setBigNum(int bigNum) {
        this.bigNum = bigNum;
    }

    public int getSmallNum() {
        return smallNum;
    }

    public void setSmallNum(int smallNum) {
        this.smallNum = smallNum;
    }
}
