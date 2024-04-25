package com.yc.test;

import com.yc.entity.User;
import com.yc.repository.UserRepository;
import com.yc.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.DigestUtils;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserAuthenticationTest {
    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;

    @Test
    public void addUser() {
        User user = new User();
        user.setName("dylan");
        user.setSalt("abc");
        String passwordDigest = DigestUtils.md5DigestAsHex(("123456"+user.getSalt()).getBytes());
        user.setPassword(passwordDigest);
        userRepository.save(user);
    }

    @Test
    public void testFindByName() {
        System.out.println(userRepository.findByName("admin"));
    }

    @Test
    public void testLogin() {
        System.out.println(userService.login("dylan3", "123"));
    }
}
