package org.songdan.tij.algorithm.limiter;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 令牌桶算法
 *
 * 每秒向令牌桶中发放n个令牌，令牌桶满了不再添加；外部请求时，如果有令牌，则可以执行请求，没有令牌拒绝
 * @author: Songdan
 * @create: 2019-02-26 15:14
 **/
public class TokenBucketLimit {

    private int burst;//令牌桶的总量

    private int tokenNum;//令牌桶中令牌的数量

    private int tokenRate;//每秒发放令牌的个数



    private void refreshToken() {
        Executors.newSingleThreadScheduledExecutor().scheduleAtFixedRate(() -> {
            if (tokenNum<burst) {
                tokenNum++;
            }
        }, 0, 1000/tokenRate, TimeUnit.MILLISECONDS);
    }

    public boolean permitGuranted() {
        if (tokenNum > 0) {
            tokenNum--;
            return true;
        }
        return false;
    }

}
