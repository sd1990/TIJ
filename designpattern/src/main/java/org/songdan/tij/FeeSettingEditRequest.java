package org.songdan.tij;

import lombok.Data;

import java.util.Map;

/**
 */
@Data
public class FeeSettingEditRequest {
    /**
     * 定价记录ID
     */
    private Long settingId;
    /**
     * 变更的定价配置
     *
     */
    private Map<String, String> setting;

}
