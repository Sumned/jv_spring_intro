package com.dev.spring.service.impl;

import com.dev.spring.dao.UserDao;
import com.dev.spring.model.User;
import com.dev.spring.service.UserService;
import com.dev.spring.util.HashUtil;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void add(User user) {
        user.setSalt(HashUtil.getSalt());
        user.setPassword(HashUtil.hashPassword(user.getPassword(), user.getSalt()));
        userDao.add(user);
    }

    @Override
    public List<User> listUsers() {
        return userDao.listUsers();
    }

    @Override
    public User getByUserId(Long id) {
        return userDao.getByUserId(id);
    }
}
