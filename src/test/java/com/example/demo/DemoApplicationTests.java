package com.example.demo;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
class DemoApplicationTests {

    @Resource
    private UserMapper userMapper;
    @Test
    public void queryUserForPage() {
        IPage<User> userPage = new Page<>(1, 5);//参数一是当前页，参数二是每页个数
        userPage = userMapper.selectPage(userPage, null);
        System.out.println("总页数"+userPage.getPages());
        System.out.println("总记录数"+userPage.getTotal());
        System.out.println("当前页"+userPage.getCurrent());
        System.out.println("每页大小"+userPage.getSize());
        List<User> list = userPage.getRecords();
        System.out.println();
        for (User user : list) {
            System.out.println(user);
        }
    }
}
