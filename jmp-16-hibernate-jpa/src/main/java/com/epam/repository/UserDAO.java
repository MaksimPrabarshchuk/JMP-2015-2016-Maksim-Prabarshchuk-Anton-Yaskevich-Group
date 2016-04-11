package com.epam.repository;

import com.epam.model.User;
import com.epam.model.UserGroup;
import com.epam.repository.base.GenericDAO;

import java.util.List;

public interface UserDAO extends GenericDAO<User, Integer> {

    List<User> getUsersByGroup(UserGroup userGroup);
}
