package org.songdan.tij.array.random.impl;

import org.songdan.tij.array.random.IMajorKey;

import java.util.UUID;

/**
 * 生成UUID型主键
 * 
 * @author jwh
 */

public class UUIDCreator implements IMajorKey {
	
    @Override
    public String getMajorKey() {
        String uuid = UUID.randomUUID().toString().replace("-", "").toUpperCase();
        return uuid;
    }
}
