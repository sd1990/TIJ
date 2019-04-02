package org.songdan.tij.exception;

/**
 * @author: Songdan
 * @create: 2018-11-07 16:23
 **/
public class ExceptionUtil {

    public static RuntimeException launderThrowable(Throwable throwable) {
        if (throwable instanceof RuntimeException) {
            return (RuntimeException) throwable;
        }
        if (throwable instanceof Error) {
            throw (Error)throwable;
        }
        throw new IllegalStateException("未知异常", throwable);
    }

}
