package com.keepthinkerexample.spring.aop.aspectj;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;


@Aspect
public class LoggingHandler {
	
	@Pointcut("execution(* com.keepthinker.example.spring.aop.aspectj.StringService.*(..))")
	private void log(){}
	
	@Before("log()")
	public void beforeAdvice(JoinPoint joinpoint){
		System.out.println("beforeAdvice");
		String targetClassName=joinpoint.getTarget().getClass().getName();

		String signature=joinpoint.getSignature().toString();
		String argsInfo = "";
		for(Object obj:joinpoint.getArgs()){
			argsInfo += obj + ":" + obj.getClass().getName() + "  ";
		}
		Logger logger = Logger.getLogger(targetClassName);
		logger.info("get into via " + signature + (argsInfo.length() > 0 ? 
				(" with args: " + argsInfo) : ""));
	}

	@After("log()")
	public void afterAdvice(){
		System.out.println("afterAdvice");
	}

	@AfterReturning(pointcut = "log()", returning="retVal")
	public void afterReturningAdvice(Object retVal){
		System.out.println("afterReturningAdvice : Returning:" + retVal.toString() );
	}

	@AfterThrowing(pointcut = "log()", throwing = "ex")
	public void AfterThrowingAdvice(IllegalArgumentException ex){
		System.out.println("AfterThrowingAdvice: There has been an exception: " + ex.toString());   
	}

}
