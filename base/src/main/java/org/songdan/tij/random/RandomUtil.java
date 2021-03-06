package org.songdan.tij.random;

import java.util.UUID;

/**
 * 随机字符串产生工具
 *
 * @author jwh
 */
public final class RandomUtil {

    /**
     * 取UUID字符串
     *
     * @return UUID字符串
     */
    public static String getUUIDString() {
        String uuid = UUID.randomUUID().toString().replace("-", "").toUpperCase();
        return uuid;
    }

    /**
     * 随机产生16位字符串
     *
     * @return 16位字符串
     */
    public static String getRandomString(int length) {
        String md5Value = Security.MD5Encode(getUUIDString());
        String shortMD5Value = md5Value.substring(0, length).toUpperCase();
        return shortMD5Value;
    }
}
