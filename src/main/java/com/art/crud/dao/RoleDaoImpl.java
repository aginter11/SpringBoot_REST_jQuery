package com.art.crud.dao;


import com.art.crud.model.Role;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class RoleDaoImpl implements RoleDao {
    @PersistenceContext
    private EntityManager em;

    @Override
    @SuppressWarnings("unchecked")
    public List<Role> getAllRole() {
        return em.createQuery("from Role").getResultList();
    }

    @Override
    public Role getRoleById(Long id) {
        return em.find(Role.class, id);
    }

    @Override
    public Role getRoleByName(String role) {
        String hql = "from Role where role = :paramRole";
        return em.createQuery(hql, Role.class)
                .setParameter("paramRole", role)
                .getSingleResult();
    }

    @Override
    public void addRole(Role role) {
        em.persist(role);
    }

    @Override
    public void deleteRole(Role role) {
        em.remove(role);
    }

    @Override
    public void updateRole(Role role) {
        em.merge(role);
    }
}
