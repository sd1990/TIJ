package org.songdan.tij.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Parser(parseBy = DefaultParser.class)
public @interface Step {

    int step() default 0;

}
