package com.ssafy.house.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@CrossOrigin("*")
public class MainController {
	
	@RequestMapping({"/","/index"})
	public String home(@RequestParam(required=false) String msg, Model model) {
		model.addAttribute("msg", msg);
		return "index";
	}
}
