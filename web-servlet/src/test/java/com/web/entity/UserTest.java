package com.web.entity;

import org.hibernate.validator.HibernateValidator;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;


class UserTest {

    private static Validator validator = Validation.byProvider(HibernateValidator.class).configure().failFast(false).buildValidatorFactory().getValidator();

    @Test
    void v() {
        User user = new User();

        Set<ConstraintViolation<User>> validate = validator.validate(user);

        for (ConstraintViolation<User> violation : validate) {
            System.out.println(violation.getMessage());
        }

    }

}