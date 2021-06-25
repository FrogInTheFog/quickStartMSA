package ru.diasoft.task;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import java.util.Arrays;

@Component
@Aspect
public class LogExecutionTimeAspect {

    private static Logger LOG = LogManager.getLogger(LogExecutionTimeAspect.class);

    @Before("@annotation(ru.diasoft.task.LogExecutionTime)")
    public void logBeforeMethod(JoinPoint jp) {
        String methodName = jp.getSignature().getName();
        String methodParams = Arrays.toString(jp.getArgs());

        LOG.info("Running method: {}", methodName);
        LOG.info("Params for method: {}", methodParams);
    }

    @Around("@annotation(ru.diasoft.task.LogExecutionTime)")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        Object proceed = joinPoint.proceed();
        long executionTime = System.currentTimeMillis() - start;

        LOG.info("{} done with: {} ms", joinPoint.getSignature(), executionTime);
        LOG.info("Result: {}", proceed);
        return proceed;
    }
}