package com.learning.templates.factory;

import org.springframework.stereotype.Service;

import com.learning.templates.impl.JDBCTemplate;

@Service
public class JDBCTemplateServiceImpl implements JDBCTemplateService {

	@Override
	public JDBCTemplate create() {
		return new JDBCTemplate();
	}

}
