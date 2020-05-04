package com.learning.templates.factory;

import com.learning.templates.impl.JDBCTemplate;


public interface JDBCTemplateService {
	public JDBCTemplate create();
}
