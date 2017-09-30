package com.nis.comm.aop;

import com.nis.comm.annotation.SqlLog;
import com.nis.comm.utils.AppContextUtil;
import com.nis.log.cache.SqlLogCache;
import com.nis.log.service.SysLogService;
import java.lang.reflect.Method;
import java.util.UUID;
import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class SqlLogsAspect {
	private static final Logger c = Logger.getLogger(SqlLogsAspect.class);
	@Autowired
	private SysLogService aV;

	@Pointcut("@annotation(com.nis.comm.annotation.SqlLog)")
	public void aspect() {
	}

	@Before("aspect()")
	public void before(JoinPoint joinPoint) {
	}

	@After("aspect()")
	public void after(JoinPoint joinPoint) {
	}

	@Around("aspect()")
	public Object around(ProceedingJoinPoint joinPoint) {
		String uniqueId = UUID.randomUUID().toString();
		Thread.currentThread().setName(uniqueId);
		Object object = null;
		SqlLogCache sqlLogCache = (SqlLogCache) AppContextUtil.getInstance().getBean(SqlLogCache.class);

		try {
			Object e = joinPoint.getTarget();
			String methodName = joinPoint.getSignature().getName();
			Signature sig = joinPoint.getSignature();
			MethodSignature msig = null;
			if (!(sig instanceof MethodSignature)) {
				throw new IllegalArgumentException("该注解只能用于方法");
			}

			msig = (MethodSignature) sig;
			Class[] parameterTypes = msig.getMethod().getParameterTypes();
			Method method = e.getClass().getMethod(methodName, parameterTypes);
			if (method != null && method.isAnnotationPresent(SqlLog.class)) {
				SqlLog sqlLog = (SqlLog) method.getAnnotation(SqlLog.class);
				sqlLogCache.B(uniqueId, sqlLog.p());
			}

			object = joinPoint.proceed();
		} catch (Exception arg15) {
			arg15.printStackTrace();
			c.error("错误", arg15);
		} catch (Throwable arg16) {
			arg16.printStackTrace();
			c.error("错误", arg16);
		} finally {
			sqlLogCache.bG(uniqueId);
		}

		return object;
	}

	@AfterReturning("aspect()")
	public void afterReturn(JoinPoint joinPoint) {
	}

	@AfterThrowing(pointcut = "aspect()", throwing = "ex")
	public void afterThrow(JoinPoint joinPoint, Exception ex) {
	}
}