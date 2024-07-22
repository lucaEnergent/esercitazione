package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.User;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository repo;
	
	public User leggiUserById(Long id) {
		User user = repo.findById(id).orElseThrow(() -> new UserNotFoundException("User con ID " + id + " non trovato"));
		
		return user;
	}
	
	public List<User> leggiUserAll() {
		 List<User> listaUtenti =  repo.findAll();
		 
		 return listaUtenti;
	}
	
	public void inserisciUser(User user) {
		repo.save(user);
	}
}
