package org.songdan.tij.recursive;

import java.util.stream.Stream;

/**
 * 尾递归接口
 *
 * @author song dan
 * @since 29 一月 2018
 */
@FunctionalInterface
public interface TailRecursion<T> {

    /**
     * 用于递归的传递
     * 
     * @return
     */
    TailRecursion<T> apply();

    default boolean isFinish() {
        return false;
    }

    /**
     * 获得递归结果,只有在递归结束才能调用,这里默认给出异常,通过工具类的重写来获得值
     * 
     * @return 递归最终结果
     */
    default T getResult() {
        throw new Error("递归还没有结束,调用获得结果异常!");
    }

    default T invoke() {
        return Stream.iterate(this, TailRecursion::apply).filter(TailRecursion::isFinish).findFirst().get().getResult();
    }

}
