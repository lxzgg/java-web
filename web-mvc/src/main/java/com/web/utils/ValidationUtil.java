package com.web.utils;

import org.hibernate.validator.HibernateValidator;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

/**
 * 参数验证工具类
 * <p>
 * * @AssertFalse 被注释的元素必须为 false
 * * @AssertTrue 被注释的元素必须为 true
 * * @DecimalMax(value,inclusive) 当包含inclusive= false时，检查带注释的值是否小于指定的最大值。否则值是否小于或等于指定的最大值。参数值是根据BigDecimal的字符串表示的最大值的字符串表示形式。
 * * @DecimalMin(value,inclusive) 当包含inclusive= false时，检查带注释的值是否大于指定的最小值。否则值是否大于或等于指定的最小值。参数值是根据BigDecimal字符串表示的值的字符串表示形式。
 * * @Digits(integer,fraction) 验证注解的元素值的整数位数和小数位数上限
 * * @Email 被注释的元素必须是电子邮箱地址
 * * @Future 被注释的元素必须是一个将来的日期
 * * @FutureOrPresent 被注释的元素必须是一个将来的日期或者现在
 * * @Max(value) 验证元素值小于等于@Max指定的value值
 * * @Min(value) 验证元素值大于等于@Min指定的value值
 * * @NotBlank 被注释的字符串的必须非空，同时，长度大于0
 * * @NotEmpty 被注释的字符串的必须非空，非null
 * * @NotNull 被注释的字符串的必须非空
 * * @Negative 检查元素是否严格负。零值被认为是无效的。
 * * @NegativeOrZero 注释的检查元素是负或者零值。
 * * @Null 注释的元素必须是null
 * * @Past 注释的元素时间必须是过去的时间
 * * @PastOrPresent 注释的元素时间必须是过去或者当前
 * * @Pattern(regex,flags) 被注释的元素必须符合指定的正则表达式
 * * @Positive 被注释的元素必须是严格的正值，不包括零值
 * * @PositiveOrZero 被注释的元素是正值或者零
 * * @Size(min,max) 被注释的元素的大小必须在指定的范围内，包含最大值
 * <p>
 * Hibernate Validator 附加的 constraint
 * <p>
 * * @CreditCardNumber(ignoreNonDigitCharacters) 这个验证旨在检查用户的错误，而不是信用卡的有效性
 * * @Currency(value) 验证货币单位
 * * @EAN 检查注释字符序列是一个有效的EAN条码
 * * @Length(min,max) 被注释的字符串的大小必须在指定的范围内
 * * @Range(min,max) 被注释的元素必须在合适的范围内
 * * @SafeHtml(whitelistType,additionalTags,additionalTagsWithAttributes=,baseURI) classpath中要有jsoup包
 * * @URL(protocol,host,port,regexp,flags) 被注释的字符串必须是一个有效的url
 * * @ScriptAssert(lang,script,alias,reportOn) 要有Java Scripting API
 */
public class ValidationUtil {

    private static Validator validator = Validation.byProvider(HibernateValidator.class).configure().failFast(true).buildValidatorFactory().getValidator();

    public static <T> void validator(T object, Class<?>... groups) {
        Set<ConstraintViolation<T>> validate = validator.validate(object, groups);
        if (validate.size() > 0) {
            ConstraintViolation<T> violation = validate.iterator().next();
            throw new ErrorException(violation.getPropertyPath() + violation.getMessage());
        }
    }

    public static <T> void validator(T object, String propertyName, Class<?>... groups) {
        Set<ConstraintViolation<T>> validate = validator.validateProperty(object, propertyName, groups);
        if (validate.size() > 0) {
            ConstraintViolation<T> violation = validate.iterator().next();
            throw new ErrorException(violation.getPropertyPath() + violation.getMessage());
        }
    }

}
