package com.art.crud.dao;




import com.art.crud.model.User;

import java.util.List;

public interface UserDao {
    void add(User user);
    void updateUser(User user);
    void removeUser(int id);
    User getUserbyId(int id);
    List<User> listUsers();

    User getUserByName(String name);

}
