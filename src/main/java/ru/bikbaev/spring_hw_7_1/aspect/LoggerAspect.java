package ru.bikbaev.spring_hw_7_1.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.core.annotation.Order;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
@EnableAspectJAutoProxy

public class LoggerAspect {


    @Around(value = "@annotation(TrackUserAction)")
    @Order

    public Object log(ProceedingJoinPoint joinPoint) throws Throwable {
        String methodName = joinPoint.getSignature().getName();
        Object[] arguments = joinPoint.getArgs();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String user = (authentication != null) ?  authentication.getName():"Anonymous";


        System.out.println( "User: "+user+ " use "+"method " + methodName +
                " with parameters " + Arrays.asList(arguments) );

        Object result = joinPoint.proceed();


        return result;
    }
}
