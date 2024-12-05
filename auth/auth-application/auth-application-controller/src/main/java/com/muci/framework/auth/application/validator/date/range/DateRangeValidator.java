package com.muci.framework.auth.application.validator.date.range;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.lang.reflect.Field;
import java.time.LocalDate;

public class DateRangeValidator implements ConstraintValidator<DateRange, Object> {
    private String startFieldName;
    private String endFieldName;

    @Override
    public void initialize(DateRange constraintAnnotation) {
        this.startFieldName = constraintAnnotation.start();
        this.endFieldName = constraintAnnotation.end();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        try {
            Field startField = value.getClass().getDeclaredField(startFieldName);
            Field endField = value.getClass().getDeclaredField(endFieldName);
            startField.setAccessible(true);
            endField.setAccessible(true);

            LocalDate startDate = (LocalDate) startField.get(value);
            LocalDate endDate = (LocalDate) endField.get(value);

            // 如果起始日期或结束日期任一为空，则验证通过
            if (startDate == null || endDate == null) {
                return true;
            }

            // 只有当两个日期都不为空时，才验证起始日期是否小于等于结束日期
            boolean isValid = !startDate.isAfter(endDate);

            // 如果验证失败，自定义错误消息
            if (!isValid) {
                context.disableDefaultConstraintViolation();
                context.buildConstraintViolationWithTemplate(
                        String.format("%s不能晚于%s",
                                startFieldName.replace("start", "起始"),
                                endFieldName.replace("end", "结束"))
                ).addConstraintViolation();
            }

            return isValid;
        } catch (Exception e) {
            return false;
        }
    }
}