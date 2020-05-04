package com.learning.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learning.aspects.PreSql;
import com.learning.templates.factory.JDBCTemplateService;
import com.learning.templates.impl.JDBCTemplate;

@Service
public class FileImportController {
	
	
	@Autowired
	private JDBCTemplateService jdbcTemplateService;
	
	

	public JDBCTemplateService getJdbcTemplateService() {
		return jdbcTemplateService;
	}



	public void setJdbcTemplateService(JDBCTemplateService jdbcTemplateService) {
		this.jdbcTemplateService = jdbcTemplateService;
	}



	@PreSql
	public void process(String body) {
		JDBCTemplate jdbcTemplate = jdbcTemplateService.create();
		jdbcTemplate.execute(body);
	}
}
