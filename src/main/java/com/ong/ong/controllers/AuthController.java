package com.ong.ong.controllers;

import com.ong.ong.domain.user.Student;
import com.ong.ong.domain.user.Teacher;
import com.ong.ong.dto.*;
import com.ong.ong.infra.security.TokenService;
import com.ong.ong.repositories.StudentRepository;
import com.ong.ong.repositories.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;


    @PostMapping("/aluno/register")
    public ResponseEntity<ResponseDto> registerAluno(@RequestBody StudentRegisterDto dto) {
        if (studentRepository.findByEmail(dto.email()).isPresent()) {
            return ResponseEntity.badRequest().build();
        }

        Student student = new Student(dto, passwordEncoder);
        studentRepository.save(student);

        String token = tokenService.generateToken(student);
        return ResponseEntity.ok(new ResponseDto(student.getEmail(), token));
    }

    @PostMapping("/aluno/login")
    public ResponseEntity<ResponseDto> loginAluno(@RequestBody LoginRequestDto dto) {
        Optional<Student> studentOpt = studentRepository.findByEmail(dto.email());

        if (studentOpt.isPresent() && passwordEncoder.matches(dto.password(), studentOpt.get().getSenha())) {
            String token = tokenService.generateToken(studentOpt.get());
            return ResponseEntity.ok(new ResponseDto(studentOpt.get().getEmail(), token));
        }

        return ResponseEntity.status(401).build();
    }


    @PostMapping("/professor/register")
    public ResponseEntity<ResponseDto> registerProfessor(@RequestBody TeacherRegisterDto dto) {
        if (teacherRepository.findByEmail(dto.email()).isPresent()) {
            return ResponseEntity.badRequest().build();
        }

        Teacher teacher = new Teacher(dto, passwordEncoder);
        teacherRepository.save(teacher);

        String token = tokenService.generateToken(teacher);
        return ResponseEntity.ok(new ResponseDto(teacher.getEmail(), token));
    }

    @PostMapping("/professor/login")
    public ResponseEntity<ResponseDto> loginProfessor(@RequestBody LoginRequestDto dto) {
        Optional<Teacher> teacherOpt = teacherRepository.findByEmail(dto.email());

        if (teacherOpt.isPresent() && passwordEncoder.matches(dto.password(), teacherOpt.get().getSenha())) {
            String token = tokenService.generateToken(teacherOpt.get());
            return ResponseEntity.ok(new ResponseDto(teacherOpt.get().getEmail(), token));
        }

        return ResponseEntity.status(401).build();
    }
}
