package org.songdan.tij.file.util;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.songdan.tij.generics.Sets;

import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URISyntaxException;
import java.util.*;

/**
 * TODO completion javadoc.
 *
 * @author song dan
 * @since 24 十一月 2017
 */
public class Fileparser {

    public static void main(String[] args) throws IOException, URISyntaxException {
        BufferedReader reader = null;
        File file = new File("/Users/songdan/Desktop/"+"poi_error_v4_ext.txt");
        List<Ext> list = new ArrayList<>();
        try {
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            List<String> lines = extractSingleStr(reader);

            for (int i = 0; i < lines.size(); i++) {
                String str = lines.get(i);
                Ext ext = getValue(str);
                list.add(ext);
            }
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


        Set<Long> diffOrderIdList = new HashSet<>();
        Set<Long> noDiffOrderIdList = new HashSet<>();
        file = new File("/Users/songdan/Desktop/"+"错误订单v4.csv");
        try {
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            List<String> lines = extractSingleStr(reader);
            BigDecimal diffSum = BigDecimal.valueOf(0);
            int reCount = 0;
            for (int i = 1; i < lines.size(); i++) {
                String str = lines.get(i);
                String[] strs = str.split(",");
                String orderFee = strs[5];
                Ext ext = list.get(i - 1);
                String fee = ext.getFee();
                String amount = ext.getAmount();
                if (fee == null || amount == null) {
                    continue;
                }
                long orderId = Long.parseLong(strs[0].trim());
                double eVal = Double.valueOf(fee);
                double fVal = Double.valueOf(amount);
                double dFee = Double.valueOf(strs[7]);
                double dAmount = Double.valueOf(strs[8]);
                if (eVal != dFee || fVal != dAmount * 100) {
                    diffOrderIdList.add(orderId);
                    BigDecimal base = BigDecimal.valueOf(Double.valueOf(orderFee)).divide(BigDecimal.valueOf(eVal), 2, RoundingMode.HALF_UP);
                    BigDecimal diff = base.multiply(BigDecimal.valueOf(eVal).subtract(BigDecimal.valueOf(dFee))).divide(BigDecimal.valueOf(100));
                    if (ext.getC().startsWith("40001")) {
                        diff = base.multiply(BigDecimal.valueOf(Math.min(eVal, 5)).subtract(BigDecimal.valueOf(Math.min(dFee, 5)))).divide(BigDecimal.valueOf(100));
                    }
                    System.out.println("C:" + ext.getC() + "E:" + eVal + ",F:" + fVal + ",dFee:" + dFee + ",orderFee:" + orderFee + ",base:" + base + ",diff:" + diff);
                    diffSum = diffSum.add(diff);
                    reCount++;
                } else {
                    noDiffOrderIdList.add(orderId);
                }
            }
            System.out.println("order size:"+list.size()+",diffSum:"+diffSum+",reCount:"+reCount);
            System.out.println("no diff order id is:" + noDiffOrderIdList);
            file = new File("/Users/songdan/Desktop/"+"settle_order_id_1.txt");
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            lines = extractSingleStr(reader);
            Set<Long> settleOrderSet = new HashSet<>();
            for (int i = 0; i < lines.size(); i++) {
                settleOrderSet.add(Long.parseLong(lines.get(i).trim()));
            }
            Set<Long> set1 = Sets.difference(diffOrderIdList, settleOrderSet);
            System.out.println(set1.size());
            System.out.println(set1);
            Set<Long> set2 = Sets.difference(settleOrderSet, diffOrderIdList);
            System.out.println(set2.size());
            System.out.println(set2);
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


    public static Ext getValue(String value) {
        Gson gson = new GsonBuilder().create();
        Ext ext = gson.fromJson(value, Ext.class);
        return ext;
    }

    public static class Ext{
        private String C;
        private String E;
        private String F;
        private String G;
        private String H;
        private String Ww;
        private String Xx;
        private String Q;
        private String R;

        public String getQ() {
            return Q;
        }

        public void setQ(String q) {
            Q = q;
        }

        public String getR() {
            return R;
        }

        public void setR(String r) {
            R = r;
        }

        public String getE() {
            return E;
        }

        public void setE(String e) {
            E = e;
        }

        public String getF() {
            return F;
        }

        public void setF(String f) {
            F = f;
        }

        public String getC() {
            return C;
        }

        public void setC(String c) {
            C = c;
        }

        public String getG() {
            return G;
        }

        public void setG(String g) {
            G = g;
        }

        public String getH() {
            return H;
        }

        public void setH(String h) {
            H = h;
        }

        public String getWw() {
            return Ww;
        }

        public void setWw(String ww) {
            Ww = ww;
        }

        public String getXx() {
            return Xx;
        }

        public void setXx(String xx) {
            Xx = xx;
        }

        public String getFee() {
            if (C.startsWith("1001")) {
                return getE();
            }
            if (C.startsWith("2002")) {
                return getG();
            }
            if (C.startsWith("3001")) {
                return getWw();
            }
            if (C.startsWith("40001")) {
                return getQ();
            }
            return null;
        }

        public String getAmount() {
            if (C.startsWith("1001")) {
                return getF();
            }
            if (C.startsWith("2002")) {
                return getH();
            }
            if (C.startsWith("3001")) {
                return getXx();
            }
            if (C.startsWith("40001")) {
                return getR();
            }
            return null;
        }
    }

    public static <T> List<List<T>> partition(List<T> source, int partition) {
        List<List<T>> list = new ArrayList<>(source.size() / partition + 1);
        for (int i = 0; i < source.size(); ) {
            int toIndex = i + partition;
            toIndex = source.size() > toIndex ? toIndex : source.size();
            list.add(source.subList(i, toIndex));
            i = i + partition;
        }
        return list;
    }

    private static List<String> extractSingleStr(BufferedReader reader) throws IOException {
        List<String> list = new ArrayList<>();
        String str;
        while ((str = reader.readLine()) != null) {
            list.add(str);
        }
        return list;
    }

}
