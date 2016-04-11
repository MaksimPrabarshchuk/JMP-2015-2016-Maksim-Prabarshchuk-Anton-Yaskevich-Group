package com.epam.model.metamodel;

import com.epam.model.User;
import com.epam.model.UserGroup;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(UserGroup.class)
public abstract class UserGroup_ {

	public static volatile SingularAttribute<UserGroup, Integer> id;
	public static volatile SingularAttribute<UserGroup, String> name;
	public static volatile SetAttribute<UserGroup, User> users;

}

