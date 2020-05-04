package com.learning.aspects;

import java.io.Serializable;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.learning.templates.factory.JDBCTemplateService;
import com.learning.templates.impl.JDBCTemplate;


@Component("jdbcTemplateServiceInterceptor")
public class JDBCTemplateServiceInterceptor implements MethodInterceptor , Serializable{

	
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private BeanFactory beanFactory;

	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		
		
		Object[] arguments = invocation.getArguments();
		
		
		Object originalReturnObject = invocation.proceed();
		
		return proxyAsNeeded(originalReturnObject);
	}

	private Object proxyAsNeeded(Object originalReturnObject) throws ClassNotFoundException {
		
		if(originalReturnObject instanceof JDBCTemplate) {
			ProxyFactoryBean proxyFactory = new ProxyFactoryBean();
			proxyFactory.setProxyTargetClass(true);
			proxyFactory.setTarget(originalReturnObject);
			proxyFactory.setInterceptorNames("jdbcTemplateInterceptor");
			proxyFactory.setBeanFactory(beanFactory);
			return (JDBCTemplate)proxyFactory.getObject();
		}
		return null;
	}
	
}
