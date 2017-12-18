package org.songdan.tij.file.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.songdan.tij.generics.Sets;

/**
 * TODO completion javadoc.
 *
 * @author song dan
 * @since 24 十一月 2017
 */
public class Fileparser6 {

    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("/Users/songdan/Desktop/store_sku.txt");
        File file2 = new File("/Users/songdan/Desktop/shelf_sku.csv");
        BufferedReader reader = null;
        try {
            System.out.println("读一整行:");
            // 读取非汉字可用
            // reader = new BufferedReader(new FileReader(file));
            // 读汉字可用
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            BufferedReader reader2 = new BufferedReader(new InputStreamReader(new FileInputStream(file2)));
            BufferedWriter fileWriter = new BufferedWriter(new FileWriter("/Users/songdan/Desktop/recover_sql.sql"));
            String str;
            // 确认调入的集合
            Set<ShopSku> shopSkuSet = new HashSet<>();
            // 确认调出的集合
            Set<ShopSku> shopSkuSet2 = new HashSet<>();
            while ((str = reader.readLine()) != null) {
                String[] split = str.split("\\s+");
                ShopSku shopSku = new ShopSku(split[0], split[1]);
                shopSkuSet.add(shopSku);
            }

            while ((str = reader2.readLine()) != null) {
                String[] split = str.split(",");
                ShopSku shopSku = new ShopSku(split[0], split[1]);
                shopSkuSet2.add(shopSku);
            }

            Set<String> strings = shopSkuSet.stream().collect(Collectors.groupingBy(ShopSku::getShopCode)).keySet();
            System.out.println();

            Set<ShopSku> intersection = Sets.intersection(shopSkuSet, shopSkuSet2);
            Set<ShopSku> difference = Sets.difference(shopSkuSet, shopSkuSet2);
            System.out.println(difference);
            System.out.println("差集的数量:" + difference.size());
            System.out.println("交集的size:" + intersection.size());
            for (ShopSku shopSku : intersection) {
                String update = String.format("update shelf_goods_relation set status_wish = 1 where shelf_code = '%s' and sku_code = '%s';", shopSku.getShopCode(), shopSku.getSkuCode());
                fileWriter.write(update);
                fileWriter.newLine();
            }
            fileWriter.flush();
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

}

class ShopSku{
    private String shopCode;

    private String skuCode;

    public String getShopCode() {
        return shopCode;
    }

    public void setShopCode(String shopCode) {
        this.shopCode = shopCode;
    }

    public String getSkuCode() {
        return skuCode;
    }

    public void setSkuCode(String skuCode) {
        this.skuCode = skuCode;
    }

    public ShopSku(String shopCode, String skuCode) {
        this.shopCode = shopCode;
        this.skuCode = skuCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ShopSku shopSku = (ShopSku) o;

        if (shopCode != null ? !shopCode.equals(shopSku.shopCode) : shopSku.shopCode != null) return false;
        return skuCode != null ? skuCode.equals(shopSku.skuCode) : shopSku.skuCode == null;
    }

    @Override
    public int hashCode() {
        int result = shopCode != null ? shopCode.hashCode() : 0;
        result = 31 * result + (skuCode != null ? skuCode.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ShopSku{" +
                "shopCode='" + shopCode + '\'' +
                ", skuCode='" + skuCode + '\'' +
                '}';
    }
}
