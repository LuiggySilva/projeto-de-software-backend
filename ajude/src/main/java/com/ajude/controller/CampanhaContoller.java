package com.ajude.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.ajude.service.CampanhaService;

@RestController
public class CampanhaContoller {
	
	@Autowired
	CampanhaService campanhaService;
	
}
