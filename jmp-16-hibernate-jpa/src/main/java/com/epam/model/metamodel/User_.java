package com.epam.model.metamodel;

import com.epam.model.User;
import com.epam.model.UserGroup;

import java.time.LocalDate;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(User.class)
public abstract class User_ {

	public static volatile SingularAttribute<User, LocalDate> birthday;
	public static volatile SingularAttribute<User, String> name;
	public static volatile SetAttribute<User, UserGroup> groups;
	public static volatile SingularAttribute<User, Integer> id;

}

