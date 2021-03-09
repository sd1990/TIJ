package org.songdan.tij.thread;

import com.google.common.base.Joiner;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author: Songdan
 * @create: 2019-11-14 20:27
 **/
@BenchmarkMode({Mode.AverageTime,Mode.SampleTime})
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Thread)
@Warmup(iterations = 5, time = 1)
@Measurement(iterations = 5, time = 1)
@Fork(0)
public class StringJMH {

//    @Benchmark
    public void test() {
        String str = "set-xr-rc-zeus-zeus-test02-201911011732-8994308";
        str.replaceAll("-", "");
    }

//    @Benchmark
    public void testApache() {
        String str = "set-xr-rc-zeus-zeus-test02-201911011732-8994308";
        StringUtils.replaceChars(str,"-", "");

    }

    @Benchmark
    public String handleTagString2(){
        String tagsString = "settle_type:1&time_logistics_history_hit:true&feeMode:WAIMAI&isProxy:true&logistics:8&is_discount:false&is_proxy_exemption:false&is_c2_exists:false|settle_type:5&settle_type:false|settle_type:5&settle_type:false|settle_type:1&time_logistics_history_hit:true&feeMode:WAIMAI&isProxy:true&logistics:8&is_discount:false&is_proxy_exemption:false&is_c2_exists:false|settle_type:5&settle_type:false|settle_type:1&time_logistics_history_hit:true&feeMode:WAIMAI&isProxy:true&logistics:8&is_discount:false&is_proxy_exemption:false&is_c2_exists:false|settle_type:5&settle_type:false|settle_type:1&time_logistics_history_hit:true&feeMode:WAIMAI&isProxy:true&logistics:8&is_discount:false&is_proxy_exemption:false&is_c2_exists:false|settle_type:5&settle_type:false|settle_type:5&settle_type:false|settle_type:1&time_logistics_history_hit:true&feeMode:WAIMAI&isProxy:true&logistics:8&is_discount:false&is_proxy_exemption:false&is_c2_exists:false|settle_type:5&settle_type:false|settle_type:1&time_logistics_history_hit:true&feeMode:WAIMAI&isProxy:true&logistics:8&is_discount:false&is_proxy_exemption:false&is_c2_exists:false|settle_type:1&time_logistics_history_hit:true&feeMode:WAIMAI&isProxy:true&logistics:8&is_discount:false&is_proxy_exemption:false&is_c2_exists:false|settle_type:5&settle_type:false|settle_type:1&time_logistics_history_hit:true&feeMode:WAIMAI&isProxy:true&logistics:8&is_discount:false&is_proxy_exemption:false&is_c2_exists:false|settle_type:5&settle_type:false";
        if(org.apache.commons.lang3.StringUtils.isEmpty(tagsString)){
            return tagsString;
        }
        Map<String, LinkedHashSet<String>> tagMap = new HashMap<>();
        String tagStrings[] = tagsString.split("\\|");
        LinkedHashSet<String> tagStringSet = Sets.newLinkedHashSet(Lists.newArrayList(tagStrings));
//        for(String tags: tagStringSet){
//            String[] tagKvs = tags.split("&");
//            for (String tagKv : tagKvs) {
//                String[] kv = tagKv.split(":");
//                if (kv == null || kv.length < 2) {
//                    continue;
//                }
//                LinkedHashSet<String> valueSet = tagMap.getOrDefault(kv[0], Sets.newLinkedHashSet());
//                valueSet.add(kv[1]);
//                tagMap.put(kv[0], valueSet);
//            }
//        }
        String join = Joiner.on("&").skipNulls().join(tagStringSet);
        return join;
    }

    @Benchmark
    @Threads(4)
    public String handleTagString1(){
        String tagsString = "settle_type:1&time_logistics_history_hit:true&feeMode:WAIMAI&isProxy:true&logistics:8&is_discount:false&is_proxy_exemption:false&is_c2_exists:false|settle_type:5&settle_type:false|settle_type:5&settle_type:false|settle_type:1&time_logistics_history_hit:true&feeMode:WAIMAI&isProxy:true&logistics:8&is_discount:false&is_proxy_exemption:false&is_c2_exists:false|settle_type:5&settle_type:false|settle_type:1&time_logistics_history_hit:true&feeMode:WAIMAI&isProxy:true&logistics:8&is_discount:false&is_proxy_exemption:false&is_c2_exists:false|settle_type:5&settle_type:false|settle_type:1&time_logistics_history_hit:true&feeMode:WAIMAI&isProxy:true&logistics:8&is_discount:false&is_proxy_exemption:false&is_c2_exists:false|settle_type:5&settle_type:false|settle_type:5&settle_type:false|settle_type:1&time_logistics_history_hit:true&feeMode:WAIMAI&isProxy:true&logistics:8&is_discount:false&is_proxy_exemption:false&is_c2_exists:false|settle_type:5&settle_type:false|settle_type:1&time_logistics_history_hit:true&feeMode:WAIMAI&isProxy:true&logistics:8&is_discount:false&is_proxy_exemption:false&is_c2_exists:false|settle_type:1&time_logistics_history_hit:true&feeMode:WAIMAI&isProxy:true&logistics:8&is_discount:false&is_proxy_exemption:false&is_c2_exists:false|settle_type:5&settle_type:false|settle_type:1&time_logistics_history_hit:true&feeMode:WAIMAI&isProxy:true&logistics:8&is_discount:false&is_proxy_exemption:false&is_c2_exists:false|settle_type:5&settle_type:false";
        if(org.apache.commons.lang3.StringUtils.isEmpty(tagsString)){
            return tagsString;
        }
        Map<String, LinkedHashSet<String>> tagMap = new HashMap<>();
        String tagStrings[] = tagsString.split("\\|");
        LinkedHashSet<String> tagStringSet = Sets.newLinkedHashSet(Lists.newArrayList(tagStrings));
        for(String tags: tagStringSet){
            String[] tagKvs = tags.split("&");
            for (String tagKv : tagKvs) {
                String[] kv = tagKv.split(":");
                if (kv == null || kv.length < 2) {
                    continue;
                }
                LinkedHashSet<String> valueSet = tagMap.getOrDefault(kv[0], Sets.newLinkedHashSet());
                valueSet.add(kv[1]);
                tagMap.put(kv[0], valueSet);
            }
        }
        String join = Joiner.on("&").skipNulls().join(Iterables.transform(tagMap.entrySet(), entry -> entry.getKey() + ":" + entry.getValue()));
        return join;
    }

    @Benchmark
    public String handleTagString(){
        return null;
    }

    @Benchmark
    public void sleep() throws InterruptedException {
        TimeUnit.MILLISECONDS.sleep(2);
    }

    public static void main(String[] args) throws RunnerException {
        Options opts = new OptionsBuilder().include(StringJMH.class.getSimpleName()).build();
        new Runner(opts).run();
    }


}
