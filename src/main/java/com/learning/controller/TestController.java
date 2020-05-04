package com.learning.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learning.templates.factory.JDBCTemplateService;
import com.learning.templates.impl.JDBCTemplate;

@Service
public class TestController {
	@Autowired
	private JDBCTemplateService jdbcTemplateService;


	
	public void process(String body) {
		JDBCTemplate jdbcTemplate = jdbcTemplateService.create();
		jdbcTemplate.execute(body);
	}
}