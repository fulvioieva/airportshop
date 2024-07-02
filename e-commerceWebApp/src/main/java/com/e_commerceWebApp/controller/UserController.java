package com.e_commerceWebApp.controller;

import com.e_commerceWebApp.entity.User;
import com.e_commerceWebApp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="e-commerce/users")
public class UserController {
    @Autowired
    private UserService usSer;

    //rotta per tutti gli user
    @GetMapping(value="/all", produces= {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<User>> getAll(){
        List<User> users= usSer.getAllUsers();
        if (users.isEmpty()||users==null) {
            return new ResponseEntity<List<User>>(HttpStatus.NOT_FOUND);

        }
        return new ResponseEntity<List<User>>(users,HttpStatus.OK);
    }

    //rotta per i singoli user by ID
    @GetMapping(value="/{idUser}", produces= {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<User> getById(@PathVariable("idUser") int idUser ){
        if (usSer.existsById(idUser)) {
            return ResponseEntity.ofNullable(usSer.getUserById(idUser));
        }
        return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
    }

    //rotta per il login
    @PostMapping(value="/login")
    public ResponseEntity<?> login(@RequestParam("idUser") int idUser,
                                   @RequestParam("password") String password) {

        User user = usSer.getUserById(idUser);
        if (user != null && password.equals(user.getPassword())) {
            // Autenticazione riuscita,
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            // Autenticazione fallita
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

}
