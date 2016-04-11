package com.epam.aspect;

import com.epam.model.User;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class UserAspect {
    @AfterReturning(pointcut = "execution(* com.epam.repository..create(..))", returning = "user")
    public void logCreatedUser(User user) {
        System.out.printf("Hello %d", user.getId());
    }

    @Before("execution(* com.epam.repository..create(..))")
    public void logBeforeCreation(JoinPoint joinPoint) {
        System.out.println("logBefore() is running!");
    }
}
