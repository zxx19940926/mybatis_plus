package com.zxx.mybatis_plus.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zxx.mybatis_plus.entity.User;
import com.zxx.mybatis_plus.mapper.UserMapper;
import com.zxx.mybatis_plus.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Override
    public List<User> listAllByName(String name) {
        // baseMapper对象指向当前业务的mapper对象
        return baseMapper.selectAllByName("Helen");
    }
}
