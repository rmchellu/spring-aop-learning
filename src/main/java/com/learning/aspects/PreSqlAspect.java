package com.learning.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.learning.controller.FileImportController;
import com.learning.templates.factory.JDBCTemplateService;
import com.learning.templates.impl.JDBCTemplate;

@Aspect
@Component
public class PreSqlAspect {
	@Autowired
	private BeanFactory beanFactory;
	
	@Around("@annotation(PreSql)")
	public Object addPreSql(ProceedingJoinPoint joinPoint) throws Throwable{
		System.out.println("Intecerpted call");
		Object target = joinPoint.getTarget();
		if(target instanceof FileImportController) {
			FileImportController controller = (FileImportController)target;
			JDBCTemplateService jdbcTemplateService = controller.getJdbcTemplateService();
			JDBCTemplateService proxiedJdbcTemplate =  proxy(jdbcTemplateService);
			controller.setJdbcTemplateService(proxiedJdbcTemplate);
		}
		return joinPoint.proceed();
	}

	
	private JDBCTemplateService proxy(JDBCTemplateService jdbcTemplateService) throws ClassNotFoundException {
		ProxyFactoryBean proxyFactory = new ProxyFactoryBean();
		proxyFactory.setProxyInterfaces(new Class[] {JDBCTemplateService.class});
		proxyFactory.setTarget(jdbcTemplateService);
		proxyFactory.setInterceptorNames("jdbcTemplateServiceInterceptor");
		proxyFactory.setBeanFactory(beanFactory);
		return (JDBCTemplateService)proxyFactory.getObject();
	}
	
	
}

