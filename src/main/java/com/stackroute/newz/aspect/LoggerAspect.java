package com.stackroute.newz.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


/* Annotate this class with @Aspect and @Component */
@Aspect
@Component
public class LoggerAspect {

	private static final Logger LOG = LoggerFactory.getLogger(LoggerAspect.class);

	@Before("execution(* com.stackroute.newz.controller.NewsController.*(..))")
	public void executedMethodsLogger(JoinPoint joinPoint) {
		LOG.info("[ Executed method {} ]", joinPoint.toString());
	}

	@AfterReturning(value = "execution(* com.stackroute.newz.controller.NewsController.*.*(..))", returning = "result")
	public void afterReturning(JoinPoint joinPoint, Object result) {
		LOG.info("{} returned with value {}", joinPoint, result);
	}

	@After(value = "execution(* com.stackroute.newz.controller.NewsController.*.*(..))")
	public void after(JoinPoint joinPoint) {
		LOG.info("after execution of {}", joinPoint);
	}
	
	@AfterThrowing(value = "execution(* com.stackroute.newz.controller.NewsController.*.*(..))")
	public void afterThrowing(JoinPoint joinPoint) {
		LOG.info("after throwing of {}", joinPoint);
	}
	/*
	 * Write loggers for each of the methods of controller, any particular method
	 * will have all the four aspectJ annotation
	 * (@Before, @After, @AfterReturning, @AfterThrowing).
	 */

}
