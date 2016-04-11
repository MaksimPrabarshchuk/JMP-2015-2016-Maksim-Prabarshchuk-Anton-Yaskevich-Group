package com.epam.repository.impl;

import com.epam.model.User;
import com.epam.repository.UserDAO;
import com.epam.repository.base.impl.AbstractDAO;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class UserDAOImpl extends AbstractDAO<User, Integer> implements UserDAO {
//    @Override
//    @Transactional
//    public User create(User entity) {
//        return super.create(entity);
//    }
}
