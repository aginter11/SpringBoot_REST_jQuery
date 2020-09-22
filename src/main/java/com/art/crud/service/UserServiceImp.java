package com.art.crud.service;


import com.art.crud.dao.UserDao;
import com.art.crud.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImp implements UserService {

    @Autowired
    private UserDao userDao;

    @Transactional
    @Override
    public void add(User user) {
        userDao.add(user);
    }

    @Transactional
    @Override
    public void updateUser(User user) {
        userDao.updateUser(user);

    }

    @Transactional
    @Override
    public void removeUser(int id) {
        userDao.removeUser(id);

    }


    @Override
    public User getUserbyId(int id) {
        return userDao.getUserbyId(id);
    }


    @Override
    public List<User> listUsers() {
        return userDao.listUsers();
    }


}
