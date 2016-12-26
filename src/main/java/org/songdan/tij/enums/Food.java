package org.songdan.tij.enums;

/**
 * 使用接口组织枚举
 * Created by PC on 2016/4/4.
 */
public interface Food {

    enum Appetizer implements Food {
        SALAD, SOUP, SPRING_ROOLS;
    }

    enum MainCourse implements Food {
        lasagne, burrito, pad_thai
    }

    enum Dessert implements Food {

    }

    enum Coffee implements Food {

    }

}
