package com.muci.framework.auth.application.validator.date.range;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = DateRangeValidator.class)
@Repeatable(DateRanges.class) // 允许在同一个类上多次使用该注解
public @interface DateRange {
    String message() default "日期区间无效";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    String start();
    String end();
}
