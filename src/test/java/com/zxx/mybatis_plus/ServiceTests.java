package com.zxx.mybatis_plus;

import com.zxx.mybatis_plus.entity.User;
import com.zxx.mybatis_plus.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class ServiceTests {

    @Resource
    private UserService userService;


    @Test
    public void testCount(){

        int count = userService.count();
        System.out.println("总记录数：" + count);
    }

    @Test
    public void testSaveBatch(){

        // SQL长度有限制，海量数据插入单条SQL无法实行，
        // 因此MP将批量插入放在了通用Service中实现，而不是通用Mapper
        ArrayList<User> users = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            User user = new User();
            user.setName("xiaoming" + i);
            user.setAge(10 + i);
            users.add(user);
        }
        userService.saveBatch(users);
    }

    @Test
    public void testListAllByName(){
        List<User> users = userService.listAllByName("Helen");
        users.forEach(System.out::println);
    }
}
