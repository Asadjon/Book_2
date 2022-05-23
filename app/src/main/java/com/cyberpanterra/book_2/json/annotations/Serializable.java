package com.cyberpanterra.book_2.json.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Serializable {

    /**
     * If {@code true}, the field marked with this annotation is written out in the JSON while
     * serializing. If {@code false}, the field marked with this annotation is skipped from the
     * serialized output. Defaults to {@code true}.
     * @since 1.4
     */
    public boolean serialize() default true;
}
