package com.example.demo.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.entity.User;
import com.example.demo.entity.UserVo;
import com.example.demo.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class xuyuanshazi {

    @Resource
    private UserMapper userMapper;
    @GetMapping("queryUser")
    public UserVo queryList(Integer current, Integer size) {
        /**
         * 这些代码应该写在service层
         */
        UserVo userVo = new UserVo();
        IPage<User> page = new Page<>(current, size);
        userMapper.selectPage(page, null);
        userVo.setCurrent(current);
        userVo.setSize(size);
        userVo.setTotal(page.getTotal());
        userVo.setUserList(page.getRecords());
        return userVo;
    }

    @RequestMapping("/list")
    public String list(Model model, @RequestParam(value = "pageNum", defaultValue = "0") int pageNum, @RequestParam(value = "pageSize", defaultValue = "2") int pageSize) {
        System.out.println("============================");
        IPage<User> page = new Page<>(pageNum, pageSize);
        IPage<User> users = userMapper.selectPage(page,null);
        System.out.println("总页数" + users.getPages());
        System.out.println("当前页是：" + pageNum);
        List<User> list = users.getRecords();
        System.out.println("分页数据：");
        for (User user : list) {
            System.out.println(user);
        }
        model.addAttribute("users", users);
        return "user/list";
    }

}
