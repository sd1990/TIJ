package org.songdan.tij;

import lombok.Builder;

/**
 * @author: Songdan
 * @create: 2020-10-16 18:02
 **/
@Builder
public class LombokDemo {

    @Builder.Default
    private Integer value = -2;

    public static void main(String[] args) {
        LombokDemo demo = LombokDemo.builder().build();
        System.out.println(demo.value);
    }

}
