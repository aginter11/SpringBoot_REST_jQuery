package com.art.crud.dao;


import com.art.crud.model.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public void add(User user) {

        entityManager.persist(user);
    }

    @Override
    public void updateUser(User user) {

        entityManager.merge(user);
    }

    @Override
    public void removeUser(int id) {

        User user = entityManager.find(User.class, id);
        entityManager.remove(user);

    }

    @Override
    public User getUserbyId(int id) {

        User user = entityManager.find(User.class, id);
        return user;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> listUsers() {

        return entityManager.createQuery("SELECT u from User u").getResultList();
    }

    @Override
    public User getUserByName(String name) {
        TypedQuery<User> query = entityManager.createQuery("SELECT u from User u where u.name =?1",User.class);
        return query.setParameter(1, name).getSingleResult();

    }


}
