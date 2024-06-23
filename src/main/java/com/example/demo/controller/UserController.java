package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping(path= "airportshop")
public class UserController {
	
	@Autowired
	UserService userService;
	

	@GetMapping(value= "users", produces= { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<List<User>> getAllArticles(){
		List<User> listArticle = new ArrayList<User>();
		listArticle = userService.getAllUser();
		return new ResponseEntity<List<User>>(listArticle, HttpStatus.OK);
		}
	
	@PutMapping(value= "user", produces= { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Boolean> addUser(@RequestBody User user){
		boolean op = userService.addUser(user);
		if (op) {
			return new ResponseEntity<Boolean>(op, HttpStatus.OK);
		}
		return new ResponseEntity<Boolean>(op, HttpStatus.FOUND);
		}
	
	@PostMapping(value= "user", produces= { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Boolean> updateUser(@RequestBody User user) {
		boolean op = userService.addUser(user);
		if (op) {
			return new ResponseEntity<Boolean>(op, HttpStatus.OK);
		}
		return new ResponseEntity<Boolean>(op, HttpStatus.NOT_FOUND);
	}
	
	@GetMapping(value= "user/{id}", produces= { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<User> getUser(@RequestParam int id) {
		Optional<User> user = userService.getUser(id);
		if (user.isEmpty()) {
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<User>(user.get(),HttpStatus.OK);
	}
	
	
}
