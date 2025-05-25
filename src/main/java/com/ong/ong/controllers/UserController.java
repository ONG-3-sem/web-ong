package com.ong.ong.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @GetMapping("/student")
    public ResponseEntity<String> getStudent() {
        return ResponseEntity.ok("Sucesso!");

    }

    @GetMapping("/teacher")
    public ResponseEntity<String> getTeacher() {
        return ResponseEntity.ok("Sucesso!");

    }
}
