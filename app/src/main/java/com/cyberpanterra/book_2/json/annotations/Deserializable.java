package com.cyberpanterra.book_2.json.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Deserializable {

    /**
     * If {@code true}, the field marked with this annotation is deserialized from the JSON.
     * If {@code false}, the field marked with this annotation is skipped during deserialization.
     * Defaults to {@code true}.
     * @since 1.4
     */
    public boolean deserialize() default true;
}
