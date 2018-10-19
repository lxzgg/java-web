package com.web.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.DecimalMin;
import java.sql.Timestamp;

@Getter
@Setter
@ToString
public class UserDto {
    private Integer id;
    private String name;
    @DecimalMin("0.01")
    private Number price;
    private Boolean status;
    private Timestamp createdAt;
    private Timestamp updatedAt;
}
