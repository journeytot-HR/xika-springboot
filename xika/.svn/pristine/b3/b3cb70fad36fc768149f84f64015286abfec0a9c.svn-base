package com.xika.config;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


/**   
* Copyright: Copyright (c) 2019 xika ltd
* 
* @ClassName: LogServiceTakeTime.java
* @Description: service的方法执行需要多少时间统计
*
* @version: v1.0.0
* @author: nec
* @date: 2019年6月5日 上午11:52:29 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2019年6月5日     nec           v1.0.0               修改原因
*/
@Aspect
@Component
public class ServiceTimeLog {
	
	final static Logger log = LoggerFactory.getLogger(ServiceTimeLog.class);

	@Pointcut("execution(* com.xika.service..*.*(..))")
	public void performance(){
	}

	@Around("performance()")
	public Object doLog(ProceedingJoinPoint joinPoint) throws Throwable {
		
		//记录起始时间 
		long begin = System.currentTimeMillis();
		Object result = "";
		/** 执行目标方法 */
		try{
			result= joinPoint.proceed();
		}catch(Exception e){
			log.error("日志记录发生错误, errorMessage: {}", e.getMessage());
		}
		finally{
			/** 记录操作时间 */
			long consumeTime = (System.currentTimeMillis() - begin);
			log.error("当前方法操作时间 consumeTime: {}ms", consumeTime);
		}
        return result;
	}
	
//	@Before("performance()")
//    public void doBefore(JoinPoint joinPoint) throws Throwable {
//        // 接收到请求，记录请求内容
//		log.info("doBefore");
//    }
	
//    @AfterReturning(returning = "ret", pointcut = "performance()")
//    public void doAfterReturning(Object ret) throws Throwable {
//        // 处理完请求，返回内容
//    	log.info("doAfterReturning");
//    }
	
}