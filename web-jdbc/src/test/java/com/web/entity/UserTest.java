package com.web.entity;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;


class UserTest {

    private static Validator validator;

    @BeforeAll
    static void setUpValidator() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void v() {
        User user = new User();
        user.setName("");

        Set<ConstraintViolation<User>> validate = validator.validate(user);

        for (ConstraintViolation<User> violation : validate) {
            System.out.println(violation.getMessage());
        }

    }

}