package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;


@RestController
@RequestMapping("/api")
public class UserRestController {
	
	@Autowired 
	UserService service;
	
	@GetMapping("/user/{id}")
	public User leggiUserById(@PathVariable Long id) {
		return service.leggiUserById(id);
	}
	
	@GetMapping("/users")
	public List<User> leggiUserAll() {
		return service.leggiUserAll();
	}
	
	@PostMapping(path ="/user", consumes = org.springframework.http.MediaType.APPLICATION_JSON_VALUE)
	public void inserisciUser(@RequestBody User user) {
		service.inserisciUser(user);
	}
	
}
