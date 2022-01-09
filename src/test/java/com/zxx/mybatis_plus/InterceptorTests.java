package com.zxx.mybatis_plus;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zxx.mybatis_plus.entity.User;
import com.zxx.mybatis_plus.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
public class InterceptorTests {
    @Resource
    private UserMapper userMapper;

    @Test
    public void testSelectPage(){

        //创建分页参数
        Page<User> pageParam = new Page<>(1,5);
        //执行分页查询
        userMapper.selectPage(pageParam, null);
        //查看分页参数的成员
        System.out.println(pageParam);
    }

    @Test
    public void testSelectPageVo(){
        Page<User> pageParam = new Page<>(1,5);
        userMapper.selectPageByPage(pageParam, 18);
        List<User> users = pageParam.getRecords();
        users.forEach(System.out::println);
    }
}
