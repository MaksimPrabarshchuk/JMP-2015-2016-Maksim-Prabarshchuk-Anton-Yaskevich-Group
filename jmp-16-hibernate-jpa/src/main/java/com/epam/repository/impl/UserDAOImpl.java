package com.epam.repository.impl;

import com.epam.model.User;
import com.epam.model.UserGroup;
import com.epam.model.metamodel.UserGroup_;
import com.epam.model.metamodel.User_;
import com.epam.repository.UserDAO;
import com.epam.repository.base.impl.JPAAbstractDAO;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.List;

public class UserDAOImpl extends JPAAbstractDAO<User, Integer> implements UserDAO {
    @Override
    public List<User> getUsersByGroup(UserGroup userGroup) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = criteriaBuilder
                .createQuery(User.class);
        Root<UserGroup> answerRoot = criteriaQuery.from(UserGroup.class);
        criteriaQuery.where(criteriaBuilder.equal(answerRoot.get(UserGroup_.id),
                userGroup.getId()));
        SetJoin<UserGroup, User> answers = answerRoot
                .join(UserGroup_.users);
        CriteriaQuery<User> cq = criteriaQuery.select(answers);
        TypedQuery<User> query = entityManager.createQuery(cq);
        return query.getResultList();
    }
}
