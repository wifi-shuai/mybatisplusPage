package com.example.demo.entity;

import lombok.Data;

import java.util.List;

@Data
public class UserVo {
    private Integer current;
    private Integer size;
    private Long total;
    private List<User> userList;
}