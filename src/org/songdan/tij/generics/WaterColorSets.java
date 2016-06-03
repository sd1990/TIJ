package org.songdan.tij.generics;

import java.util.EnumSet;


public class WaterColorSets {
    public static void main(String[] args) {
        EnumSet<WaterColors> set1 = EnumSet.range(WaterColors.ZINC, WaterColors.DEEP_YELLOW);
        EnumSet<WaterColors> set2 = EnumSet.range(WaterColors.MEDIUM_YELLOW, WaterColors.CRIMSON);
        System.out.println("set1 intersection set2 is : "+Sets.intersection(set1, set2));
    }
}
