package org.songdan.tij.string;

import com.google.common.collect.Lists;
import com.google.gson.Gson;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
public class HostGroup {

    /**
     * 地址映射列表
     */
    private List<HostMap> hostMaps;

    /**
     * 此组的开关
     */
    private boolean switcher;

    @Getter
    @Setter
    @ToString
    public static class HostMap {
        /**
         * 源IP地址
         */
        private String src;

        /**
         * 目的地址
         */
        private String dst;

        /**
         * 开关
         */
        private boolean switcher = true;

        public static HostMap closed(){
            HostMap hostMap = new HostMap();
            hostMap.setSwitcher(false);
            return hostMap;
        }
    }

    public static void main(String[] args) {
        HostGroup hostGroup = new HostGroup();
        hostGroup.setSwitcher(true);
        HostMap hostMap = new HostMap();
        hostMap.setSrc("10.182.38.98");
        hostMap.setDst("10.179.17.175");
        hostMap.setSwitcher(true);
        hostGroup.setHostMaps(Lists.newArrayList(hostMap));
        Gson gson = new Gson();
        System.out.println(gson.toJson(hostGroup));
    }
}

