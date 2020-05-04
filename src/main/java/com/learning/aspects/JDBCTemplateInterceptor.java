package com.learning.aspects;

import java.io.Serializable;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.stereotype.Component;


@Component("jdbcTemplateInterceptor")
public class JDBCTemplateInterceptor implements MethodInterceptor , Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		
		
		Object[] arguments = invocation.getArguments();
		String[] sqls = (String[])arguments[0];
		String[] sqlWithPreSqls = new String[sqls.length+1];
		sqlWithPreSqls[0] = "pre sql";
		System.arraycopy(sqls, 0, sqlWithPreSqls, 1, sqls.length);
		arguments[0] = sqlWithPreSqls;
		
		return invocation.proceed();
	}
	
}
