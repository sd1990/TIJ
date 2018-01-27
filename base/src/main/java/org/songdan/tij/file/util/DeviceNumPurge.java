package org.songdan.tij.file.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.songdan.tij.generics.Sets;

/**
 * TODO completion javadoc.
 *
 * @author song dan
 * @since 24 十一月 2017
 */
public class DeviceNumPurge {

    public static void main(String[] args) throws FileNotFoundException {
        purgeDevice();
//        purge();
    }

    private static void purge() {
        File cancelFile = new File("/Users/songdan/Desktop/cancel-device-bach.csv");
        //线上设备数量
        File opcDeviceNum = new File("/Users/songdan/Desktop/opc-shelf-device.csv");
        BufferedReader opcCancelOrderReader = null;
        BufferedReader opcDeviceNumberReader = null;
        try {
            System.out.println("读一整行:");
            // 读取非汉字可用
            // reader = new BufferedReader(new FileReader(file));
            // 读汉字可用
            String str;
            StringBuilder deviceBuilder = new StringBuilder();
            HashSet<String> bachOrderSet = new HashSet<>();
            ArrayList<ShelfDevice> cancelDeviecList = new ArrayList<>();
            opcCancelOrderReader = new BufferedReader(new InputStreamReader(new FileInputStream(cancelFile)));
            while ((str = opcCancelOrderReader.readLine()) != null) {
                if ("".equals(str)) {
                    continue;
                }
                String[] strs = str.trim().split(",");
                String id = strs[0];
                bachOrderSet.add(id);
                String shelfCode = strs[1];
                String deviceTypeStr = strs[2];
                if ("".equals(deviceTypeStr)) {
                    continue;
                }
                String deviceNum = strs[3];

                int deviceType = Integer.parseInt(deviceTypeStr);
                ShelfDevice shelfDevice = new ShelfDevice(shelfCode, deviceType == 38 ? 1 : (deviceType == 39 ? 2 : 3),
                        Integer.parseInt(deviceNum));

                cancelDeviecList.add(shelfDevice);

            }
            System.out.println("all bach device size is " + cancelDeviecList.size());
            System.out.println("all bach order size is "+bachOrderSet.size());

            // 读取数据库里面的旧的设备数量
            opcDeviceNumberReader = new BufferedReader(new InputStreamReader(new FileInputStream(opcDeviceNum)));
            ArrayList<ShelfDevice> opcDeviceList = new ArrayList<>();
            while ((str = opcDeviceNumberReader.readLine()) != null) {
                if ("".equals(str)) {
                    continue;
                }
                String[] strs = str.trim().split(",");
                String shelfCode = strs[0];
                String deviceTypeStr = strs[1];
                if ("".equals(deviceTypeStr)) {
                    continue;
                }
                String deviceNum = strs[2];

                int deviceType = Integer.parseInt(deviceTypeStr);
                ShelfDevice shelfDevice = new ShelfDevice(shelfCode, deviceType ,
                        Integer.parseInt(deviceNum));

                opcDeviceList.add(shelfDevice);

            }

            System.out.println("opc device size is " + opcDeviceList.size());

            Map<ShelfDevice, Integer> cancelDeviecMap = cancelDeviecList.stream().collect(Collectors.toMap(Function.identity(), ShelfDevice::getNumber, (x1, x2) ->
                    x1 + x2));
            Map<ShelfDevice, Integer> opcDeviceMap = opcDeviceList.stream().collect(Collectors.toMap(Function.identity(), ShelfDevice::getNumber, (x1, x2) ->
                    x1 + x2));
            //FIXME
            StringBuilder displayBuilder = new StringBuilder();
            cancelDeviecMap.forEach(((shelfDevice, integer) -> {
                int oldValue = opcDeviceMap.get(shelfDevice);
                String deviceSql = String.format("update shelf_device_relation set device_number = %d where shop_code = '%s' and device_type = %d;", oldValue - shelfDevice.getNumber(), shelfDevice.getShelfCode(), shelfDevice.getDeviceType());
                deviceBuilder.append(deviceSql).append(System.lineSeparator());

                String sql = String.format(
                        "update shelf_goods_relation set amount = ceil((amount*%d)/%d)  where shelf_code = '%s' and equipment_type = %d ;",
                        (oldValue-shelfDevice.getNumber()),oldValue, shelfDevice.getShelfCode(), shelfDevice.getDeviceType());
                displayBuilder.append(sql).append(System.lineSeparator());
            }));
            BufferedWriter writer = new BufferedWriter(new FileWriter("/Users/songdan/Desktop/cancel-device-purge.sql"));
            writer.write(deviceBuilder.toString());
            writer.flush();
            writer = new BufferedWriter(new FileWriter("/Users/songdan/Desktop/cancel-display-purge.sql"));
            writer.write(displayBuilder.toString());
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            /*if (null != excludeReader) {
                try {
                    excludeReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }*/
        }
    }

    private static String getKey(ShelfDevice shelfDevice) {
        return shelfDevice.getShelfCode() + "_" + shelfDevice.getDeviceType();
    }

    private static void purgeDevice() {
        File file = new File("/Users/songdan/Desktop/27-opc-device.csv");
        File allFile = new File("/Users/songdan/Desktop/20161226-bach.csv");
//        File opcOrder = new File("/Users/songdan/Desktop/opc-order.csv");
        File opcCancelOrder = new File("/Users/songdan/Desktop/cancel-order.csv");
//        File opcDevice = new File("/Users/songdan/Desktop/opc-device-effect.csv");
        BufferedReader reader = null;
        BufferedReader allReader = null;
//        BufferedReader opcOrderReader = null;
        BufferedReader opcCancelOrderReader = null;
//        BufferedReader opcDeviceReader = null;
        try {
            System.out.println("读一整行:");
            // 读取非汉字可用
//             reader = new BufferedReader(new FileReader(file));
            // 读汉字可用
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            allReader = new BufferedReader(new InputStreamReader(new FileInputStream(allFile)));
//            opcOrderReader = new BufferedReader(new InputStreamReader(new FileInputStream(opcOrder)));
            opcCancelOrderReader = new BufferedReader(new InputStreamReader(new FileInputStream(opcCancelOrder)));
//            opcDeviceReader = new BufferedReader(new InputStreamReader(new FileInputStream(opcDevice)));
            String str;
            StringBuilder builder = new StringBuilder();
            List<ShelfDevice> deviceList = new ArrayList<>();
            List<ShelfDevice> allDeviceList = new ArrayList<>();
            while ((str = reader.readLine()) != null) {
                if ("".equals(str)) {
                    continue;
                }
                String[] strs = str.trim().split(",");
                String shelfCode = strs[0];
                String deviceTypeStr = strs[1];
                if ("".equals(deviceTypeStr)) {
                    continue;
                }
                String deviceNum = strs[2];

                int deviceType = Integer.parseInt(deviceTypeStr);
                ShelfDevice shelfDevice = new ShelfDevice(shelfCode, deviceType ,
                        Integer.parseInt(deviceNum));

                deviceList.add(shelfDevice);

            }
            Map<ShelfDevice, Integer> opcDeviceMap = deviceList.stream().collect(Collectors.toMap(Function.identity(), ShelfDevice::getNumber, (x1, x2) ->
                    x1 + x2));
            //opc已经取消的数据
            HashSet<String> opcCancelOrderSet = new HashSet<>();
            while ((str = opcCancelOrderReader.readLine()) != null) {
                if ("".equals(str)) {
                    continue;
                }
                String[] strs = str.trim().split(",");
                String id = strs[0];
                opcCancelOrderSet.add(id);
            }
            System.out.println("opc cancel order size is " + opcCancelOrderSet.size());
            //全量bach订单数据
            HashSet<String> bachOrderSet = new HashSet<>();
            HashSet<String> cancelOrderSet = new HashSet<>();
            String[] filterStr = {"377000728","377000777","377001714","100004710","106003796","122005712","108761776","100072703","111002752", "122005752", "385000742", "385000754", "113011780", "113012739", "108000793", "108001710", "222000741", "222000720", "222000726", "222000747", "222000738", "222000731", "117002773", "170000781", "523000760", "107000705", "602000700", "100005725", "116002731", "125560795", "125003723", "100761722", "122004797", "102638751", "113271786", "115095724", "115002787", "123539764", "123010777"};
            HashSet<String> filters = new HashSet<>();
            for (String s : filterStr) {
                filters.add(s);
            }
            while ((str = allReader.readLine()) != null) {
                if ("".equals(str)) {
                    continue;
                }
                String[] strs = str.trim().split(",");
                String id = strs[0];
                bachOrderSet.add(id);
                if (!opcCancelOrderSet.contains(id)) {
                    //fei已取消的数据
                    continue;
                }
                cancelOrderSet.add(id);
                String shelfCode = strs[1];
                String deviceTypeStr = strs[2];
                if ("".equals(deviceTypeStr)) {
                    continue;
                }
                String deviceNum = strs[3];

                int deviceType = Integer.parseInt(deviceTypeStr);
                ShelfDevice shelfDevice = new ShelfDevice(shelfCode, deviceType == 38 ? 1 : (deviceType == 39 ? 2 : 3),
                        Integer.parseInt(deviceNum));
                if (filters.contains(shelfDevice.getShelfCode())) {
                    continue;
                }

                allDeviceList.add(shelfDevice);

            }
            System.out.println("all bach device size is " + allDeviceList.size());
            System.out.println(bachOrderSet.size());
            System.out.println(cancelOrderSet.size());

//            Set<String> orderDiffSet = Sets.intersection(opcCancelOrderSet, bachOrderSet);
//
//            System.out.println("diff size is :"+orderDiffSet.size());

/*            for (String orderId : orderDiffSet) {
                System.out.println(orderId);
            }*/
//            long count = deviceList.stream().map(shelfDevice -> "'" + shelfDevice.getShelfCode() + "'").distinct().count();
//            System.out.println(count);
//            System.out.println(allDeviceList.stream().map(shelfDevice -> "'" + shelfDevice.getShelfCode() + "'").distinct().count());
            System.out.println(allDeviceList.stream().map(shelfDevice-> "'"+shelfDevice.getShelfCode()+"'").distinct().collect(Collectors.joining(",")));
//            System.out.println(shelfCodes);

//            Map<ShelfDevice, Integer> shelfDeviceIntegerMap = deviceList.stream()
//                    .collect(Collectors.toMap(Function.identity(), ShelfDevice::getNumber, (x1, x2) -> {
//                        return x1 + x2;
//                    }));

            Map<ShelfDevice, Integer> allDeviceMap = allDeviceList.stream().collect(Collectors.toMap(shelfDevice -> {
//                System.out.println(shelfDevice);
                return shelfDevice;
            }, ShelfDevice::getNumber,(x1, x2) -> {
                return x1 + x2;
            }));

//            Set<ShelfDevice> difference = Sets.difference(allDeviceMap.keySet(), shelfDeviceIntegerMap.keySet());
//            Set<ShelfDevice> inner = Sets.intersection(allDeviceMap.keySet(), shelfDeviceIntegerMap.keySet());
//            for (ShelfDevice shelfDevice : allDeviceList) {
//                Integer cj = shelfDeviceIntegerMap.get(shelfDevice);
//                Integer zz = allDeviceMap.get(shelfDevice);
//                if (zz != cj) {
//                    System.out.println(shelfDevice+":zz->"+ zz);
//                    System.out.println(shelfDevice+":cj->"+ cj);
//                    String sql = String.format(
//                            "update shelf_device_relation set device_number = device_number - %d where shop_code = '%s' and device_type = %d;",
//                            zz-cj, shelfDevice.getShelfCode(), shelfDevice.getDeviceType());
//                    builder.append(sql).append(System.lineSeparator());
//                }
//            }
//            System.out.println("innner work size is " + inner.size());
//            System.out.println("real work size is " + difference.size());
            StringBuilder displayBuilder = new StringBuilder();
            allDeviceMap.forEach(((shelfDevice, integer) -> {
//                if (difference.contains(shelfDevice)) {
                Integer oldValue = opcDeviceMap.get(shelfDevice);
                String sql = String.format("update shelf_device_relation set device_number = %d where shop_code = '%s' and device_type = %d;", oldValue - shelfDevice.getNumber(), shelfDevice.getShelfCode(), shelfDevice.getDeviceType());
                builder.append(sql).append(System.lineSeparator());
                String displaySql = String.format(
                        "update shelf_goods_relation set amount = ceil((amount*%d)/%d)  where shelf_code = '%s' and equipment_type = %d ;",
                        (oldValue-shelfDevice.getNumber()),oldValue, shelfDevice.getShelfCode(), shelfDevice.getDeviceType());
                displayBuilder.append(displaySql).append(System.lineSeparator());
//                }
            }));
            BufferedWriter writer = new BufferedWriter(new FileWriter("/Users/songdan/Desktop/cancel-device.sql"));
            writer.write(builder.toString());
            writer.flush();
            writer = new BufferedWriter(new FileWriter("/Users/songdan/Desktop/cancel-display-purge2.sql"));
            writer.write(displayBuilder.toString());
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            /*if (null != reader) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }*/
        }
    }

    static class ShelfDevice {

        private String shelfCode;

        private Integer deviceType;

        private Integer number;

        public ShelfDevice(String shelfCode, Integer deviceType, Integer number) {
            this.shelfCode = shelfCode;
            this.deviceType = deviceType;
            this.number = number;
        }

        public String getShelfCode() {
            return shelfCode;
        }

        public Integer getDeviceType() {
            return deviceType;
        }

        public Integer getNumber() {
            return number;
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

        @Override
        public String toString() {
            return "ShelfDevice{" +
                    "shelfCode='" + shelfCode + '\'' +
                    ", deviceType=" + deviceType +
                    ", number=" + number +
                    '}';
        }
    }

}
