package com.yc.service.impl;

import com.yc.entity.User;
import com.yc.repository.UserRepository;
import com.yc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Override
    public String login(String name, String password) {
        if(!StringUtils.isEmpty(name) && !StringUtils.isEmpty(password)) {
            // 查询用户
            User user = userRepository.findByName(name);
            if(user == null) {
                return "用户不存在";
            }
            // 比对密码
            String passwordDigest = DigestUtils.md5DigestAsHex((password+user.getSalt()).getBytes());
            if(passwordDigest.equals(user.getPassword())) {
                return "登陆成功";
            } else {
                return "密码错误";
            }
        } else {
            return "用户名或密码未提供";
        }
    }
}
