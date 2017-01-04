package org.songdan.tij.random;

import org.junit.Test;

public class RandomUtilTest {

    @Test
    public void testGetRandomString() throws Exception {
        String randomString = RandomUtil.getRandomString(24);
        System.out.println(randomString);

    }
}
