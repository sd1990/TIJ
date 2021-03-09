package org.songdan.tij.anno;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE,ElementType.ANNOTATION_TYPE})
@Documented
@Inherited
public @interface Base {
    String base() default "base";
}
