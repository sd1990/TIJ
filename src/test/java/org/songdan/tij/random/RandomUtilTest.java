package org.songdan.tij.random;

import org.junit.Test;

public class RandomUtilTest {

    @Test
    public void testGetRandomString() throws Exception {
        String randomString = RandomUtil.getRandomString(24);
        System.out.println("密钥："+RandomUtil.getRandomString(24));
        System.out.println("平台编码："+RandomUtil.getRandomString(8));
        System.out.println("注册码："+RandomUtil.getRandomString(8));
        System.out.println("授权码："+RandomUtil.getRandomString(8));

    }
}
