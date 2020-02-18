package com.mycoding.springboot.scc.controller;

import org.slf4j.Logger;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.mycoding.springboot.scc.services.SimpsonsCharacterQuoteService;

@Controller
public class IndexController {

	private final Logger logger = LoggerFactory.getLogger(IndexController.class);

	SimpsonsCharacterQuoteService simpsonsCharacterQuoteService;

	@Autowired
	public void setUserService(SimpsonsCharacterQuoteService simpsonsCharacterQuoteService) {
		this.simpsonsCharacterQuoteService = simpsonsCharacterQuoteService;
	}

	@GetMapping("/")
	public String index() {
		return "main";

	}
}