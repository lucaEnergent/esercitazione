package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;

@Controller
public class UserController {

	@Autowired
	UserRepository repo;

	@GetMapping("/form")
	public String mostraForm(Model model) {
		model.addAttribute("user", new User());
		return "form";
	}

	@PostMapping("/form")
    public String inserisciDati(User user) {
        repo.save(user);
        return "redirect:/user";
    }
	
	@GetMapping("/user")
    public String listaUsers(Model model) {
        model.addAttribute("users", repo.findAll());
        return "user";
    }
	
}
