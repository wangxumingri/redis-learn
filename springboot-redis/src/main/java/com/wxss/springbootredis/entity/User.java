package com.wxss.springbootredis.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class User {
    private Long id;
    private String name;
    private Integer age;
    private Date birthday;
}
