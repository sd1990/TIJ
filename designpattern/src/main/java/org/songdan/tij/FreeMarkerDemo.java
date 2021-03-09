package org.songdan.tij;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Maps;
import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import lombok.Data;
import org.songdan.tij.designpattern.composite.JsonUtil;

import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @author: Songdan
 * @create: 2021-01-19 18:17
 **/
public class FreeMarkerDemo {

    public static void main(String[] args) throws IOException, TemplateException {
        //创建一个Configuration实例
        final Configuration cfg;
        cfg = new Configuration();
        //设置FreeMarker的模版文件夹位置
        StringTemplateLoader strTemplateLoader = new StringTemplateLoader();
        String template = "{\n" +
                "\t\"code\":${rpc.pageList.result.code},\n" +
                "\t\"msg\":${rpc.pageList.result.message!'\"\"'}\n" +
                "\t<#if rpc.pageList.result.code == 0>\n" +
                "\t,\n" +
                "\t\"data\":{\n" +
                "\t\t\"pageInfo\":{\n" +
                "\t\t\t\"pageNo\":${rpc.pageList.pager.pageNo},\n" +
                "\t\t\t\"pages\":${rpc.pageList.pager.pageSize},\n" +
                "\t\t\t\"total\":${rpc.pageList.pager.total}\n" +
                "\t\t}\n" +
                "\t\t,\n" +
                "\t\t\"list\":[\n" +
                "\t\t<#if rpc.pageList.settingList?? && (rpc.pageList.settingList?size>0)>\n" +
                "\t\t\t<#list rpc.pageList.settingList as item>\n" +
                "\t\t\t\t<#if item??>\n" +
                "\t\t\t\t\t{\n" +
                "\t\t\t\t\t\t\"id\":\"${item.id}\",\n" +
                "\t\t\t\t\t\t\"wmContactPointDesc\":\"${item.wmContactPointLabel!''}\",\n" +
                "\t\t\t\t\t\t\"cityLocationDesc\":\"${item.cityLocationLabel!''}\",\n" +
                "\t\t\t\t\t\t\"brandLevelDesc\":\"${item.brandLevelLabel!''}\",\n" +
                "\t\t\t\t\t\t\"brandId\":\"${item.brandId!''}\",\n" +
                "\t\t\t\t\t\t\"firstCategoryDesc\":\"${item.firstCategoryLabel!''}\",\n" +
                "\t\t\t\t\t\t\"secondCategoryDesc\":\"${item.secondCategoryLabel!''}\",\n" +
                "\t\t\t\t\t\t\"deliveryTypeDesc\":\"${item.deliveryTypeLabel!''}\",\n" +
                "\t\t\t\t\t\t\"brandName\":\"${item.brandName!''}\",\n" +
                "\t\t\t\t\t\t\"serviceFee\":\"${item.setting.techFee!''}\",\n" +
                "\t\t\t\t\t\t\"minAmount\":\"${item.setting.amount!''}\",\n" +
                "\t\t\t\t\t\t\"sdRate\":\"${item.setting.sdRate!''}\",\n" +
                "                                                \"sdRates\":[\n" +
                "                                                <#assign rates = item.setting.shopSdRates?json_string> \n" +
                "                                                 <#if rates?? && (rates?size>0)>\n" +
                "\t\t\t                               <#list rates as key,value>\n" +
                "\t\t\t\t                           <#if key??>\n" +
                "\t\t\t\t\t                         {\n" +
                "                                                                      \"level\":\"${key!''}\",\n" +
                "                                                                      \"rate\":\"${value!''}\"\n" +
                "                                                                 }\n" +
                "                                                                 <#if item_has_next>\n" +
                "\t\t\t\t\t                         ,\n" +
                "\t\t\t\t\t                         </#if>\n" +
                "\t\t\t\t                            </#if>\n" +
                "\t\t\t                               </#list>\n" +
                "\t\t                                 </#if> \n" +
                "                                                 ]\n" +
                "\t\t\t\t\t\t\"feeUpperLimit\":\"${item.setting.upperLimit!''}\",\n" +
                "\t\t\t\t\t\t\"feeLowerLimit\":\"${item.setting.lowerLimit!''}\"\n" +
                "\t\t\t\t\t}\n" +
                "\t\t\t\t\t<#if item_has_next>\n" +
                "\t\t\t\t\t,\n" +
                "\t\t\t\t\t</#if>\n" +
                "\t\t\t\t</#if>\n" +
                "\t\t\t</#list>\n" +
                "\t\t</#if>\n" +
                "\t\t]\n" +
                "\t}\n" +
                "\t</#if>\n" +
                "}";
        String template2 = "<#assign m = shopSdRates?eval><#list m?keys as k>\n" +
                "  ${k} => ${m[k]}\n" +
                "</#list>";
        strTemplateLoader.putTemplate("demo",template2);
        cfg.setTemplateLoader(strTemplateLoader);
        //创建模版对象
        Template t = cfg.getTemplate("demo");
//        String param = "{\"amount\":\"5\",\"sdRate\":\"-1\",\"shopSdRates\":\"{\\\"S\\\":\\\"10\\\",\\\"A\\\":\\\"9\\\",\\\"B1\\\":\\\"9\\\",\\\"B2\\\":\\\"8\\\",\\\"C\\\":\\\"7\\\",\\\"NONE\\\":\\\"6\\\"}\",\"techFee\":\"10\",\"lowerLimit\":\"-1\",\"upperLimit\":\"-1\"}";
//        System.out.println(param);
//        ObjectMapper objectMapper = new ObjectMapper();
//        JavaType javaType = objectMapper.getTypeFactory().constructMapType(HashMap.class, String.class, String.class);
//        Map<String,String> map = objectMapper.readValue(param,javaType);
        Con con = new Con();
        Map<String, BigDecimal> shopSdRates = new HashMap<>();
        shopSdRates.put("S", BigDecimal.valueOf(10));
        shopSdRates.put("A", BigDecimal.valueOf(9));
        shopSdRates.put("B1", BigDecimal.valueOf(8));
        shopSdRates.put("B2", BigDecimal.valueOf(7));
        shopSdRates.put("C", BigDecimal.valueOf(6));
        shopSdRates.put("NONE", BigDecimal.valueOf(5));
        con.setShopSdRates(shopSdRates);
        Map<String, String> map = beanToMap(con);
        System.out.println(map);
        StringWriter stringWriter = new StringWriter();
        t.process(map,stringWriter);
        System.out.println(stringWriter.toString());
    }

    @Data
    public static class Con{
        private Map<String, BigDecimal> shopSdRates;
    }

    public static Map<String, String> beanToMap(Object obj) throws IOException {
//        String json = JsonUtil.toJson(obj);
//        System.out.println(json);
//        return JsonUtil.ofMap(json, String.class, String.class);
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(obj);
        JsonNode jsonNode = objectMapper.readTree(json);
        if (jsonNode == null) {
            return Maps.newHashMap();
        }
        Map<String, String> map = Maps.newHashMap();
        Iterator<String> iterator = jsonNode.fieldNames();
        while (iterator.hasNext()) {
            String field = iterator.next();
            map.put(field, JsonUtil.toJson(jsonNode.get(field)));
        }
        return map;
    }

}
