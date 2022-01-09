package com.zxx.mybatis_plus;

import com.zxx.mybatis_plus.entity.Product;
import com.zxx.mybatis_plus.entity.User;
import com.zxx.mybatis_plus.mapper.ProductMapper;
import com.zxx.mybatis_plus.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
public class MapperTests {

    @Resource
    private UserMapper userMapper;

    @Resource
    private ProductMapper productMapper;

    @Test
    public void testConcurrentUpdate() {

        //1、小李
        Product p1 = productMapper.selectById(1L);

        //2、小王
        Product p2 = productMapper.selectById(1L);

        //3、小李将价格加了50元，存入了数据库
        p1.setPrice(p1.getPrice() + 50);
        int result1 = productMapper.updateById(p1);
        System.out.println("小李修改结果：" + result1);

        //4、小王将商品减了30元，存入了数据库
        p2.setPrice(p2.getPrice() - 30);
        int result2 = productMapper.updateById(p2);
        System.out.println("小王修改结果：" + result2);
        if(result2 == 0){//更新失败，重试
            System.out.println("小王重试");
            //重新获取数据
            p2 = productMapper.selectById(1L);
            //更新
            p2.setPrice(p2.getPrice() - 30);
            productMapper.updateById(p2);
        }

        //最后的结果
        Product p3 = productMapper.selectById(1L);
        System.out.println("最后的结果：" + p3.getPrice());
    }

    @Test
    public void testInsert(){

        User user = new User();
        user.setName("Helen");
        user.setAge(18);
        //不设置email属性，则生成的动态sql中不包括email字段

        int result = userMapper.insert(user);
        System.out.println("影响的行数：" + result); //影响的行数
        System.out.println("id：" + user.getId()); //id自动回填
    }

    @Test
    public void testSelect(){

        //按id查询
        User user = userMapper.selectById(1);
        System.out.println(user);

        //按id列表查询
        List<User> users = userMapper.selectBatchIds(Arrays.asList(1, 2, 3));
        users.forEach(System.out::println);

        //按条件查询
        Map<String, Object> map = new HashMap<>();
        map.put("name", "Helen"); //注意此处是表中的列名，不是类中的属性名
        map.put("age", 18);
        List<User> users1 = userMapper.selectByMap(map);
        users1.forEach(System.out::println);
    }

    @Test
    public void testUpdate(){

        User user = new User();
        user.setId(1L);
        user.setAge(28);

        //注意：update时生成的sql自动是动态sql
        int result = userMapper.updateById(user);
        System.out.println("影响的行数：" + result);
    }

    @Test
    public void testDelete(){

        int result = userMapper.deleteById(4);
        System.out.println("影响的行数：" + result);
    }

    @Test
    public void testSelectAllByName(){
        List<User> users = userMapper.selectAllByName("Sandy");
        users.forEach(System.out::println);
    }

}
