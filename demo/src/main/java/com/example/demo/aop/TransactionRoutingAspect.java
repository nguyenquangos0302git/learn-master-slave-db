package com.example.demo.aop;

import com.example.demo.context.DbContextHolder;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Aspect
@Component
@Order(-1)
public class TransactionRoutingAspect {

    @Around("@annotation(tx)")
    public Object route(ProceedingJoinPoint pjp, Transactional tx) throws Throwable {
        try {
            if (tx.readOnly()) {
                DbContextHolder.useSlave();
            } else {
                DbContextHolder.useMaster();
            }
            return pjp.proceed();
        } finally {
            DbContextHolder.clear();
        }
    }

}
