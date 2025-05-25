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

    // ==== LOGIN ====

    @PostMapping("/login/student")
    public ResponseEntity<?> loginStudent(@RequestBody LoginRequestDto body){
        var student = studentRepository.findByEmail(body.email())
                .orElseThrow(() -> new RuntimeException("Student not found"));

        if(passwordEncoder.matches(body.password(), student.getSenha())) {
            String token = tokenService.generateToken(student);
            return ResponseEntity.ok(new ResponseDto(student.getNome(), token));
        }
        return ResponseEntity.status(401).body("Invalid credentials");
    }

    @PostMapping("/login/teacher")
    public ResponseEntity<?> loginTeacher(@RequestBody LoginRequestDto body){
        var teacher = teacherRepository.findByEmail(body.email())
                .orElseThrow(() -> new RuntimeException("Teacher not found"));

        if(passwordEncoder.matches(body.password(), teacher.getSenha())) {
            String token = tokenService.generateToken(teacher);
            return ResponseEntity.ok(new ResponseDto(teacher.getNome(), token));
        }
        return ResponseEntity.status(401).body("Invalid credentials");
    }

    // ==== REGISTER ====

    @PostMapping("/register/student")
    public ResponseEntity<?> registerStudent(@RequestBody StudentRegisterDto body){
        if (studentRepository.findByEmail(body.email()).isPresent()) {
            return ResponseEntity.badRequest().body("Student already exists");
        }

        Student newStudent = new Student();
        newStudent.setNome(body.nome());
        newStudent.setCpf(body.cpf());
        newStudent.setEmail(body.email());
        newStudent.setSenha(passwordEncoder.encode(body.senha()));
        newStudent.setRg(body.rg());
        newStudent.setDataNascimento(body.dataNascimento());
        newStudent.setTelefone(body.telefone());
        newStudent.setCep(body.cep());
        newStudent.setEndereco(body.endereco());
        newStudent.setNumero(body.numero());
        newStudent.setComplemento(body.complemento());
        newStudent.setCidade(body.cidade());
        newStudent.setEstado(body.estado());
        newStudent.setNomeEscolaAtual(body.nomeEscolaAtual());

        studentRepository.save(newStudent);

        String token = tokenService.generateToken(newStudent);
        return ResponseEntity.ok(new ResponseDto(newStudent.getNome(), token));
    }


    @PostMapping("/register/teacher")
    public ResponseEntity<?> registerTeacher(@RequestBody TeacherRegisterDto body){
        if (teacherRepository.findByEmail(body.email()).isPresent()) {
            return ResponseEntity.badRequest().body("Teacher already exists");
        }
        Teacher newTeacher = new Teacher();
        newTeacher.setNome(body.nome());
        newTeacher.setCpf(body.cpf());
        newTeacher.setEmail(body.email());
        newTeacher.setSenha(passwordEncoder.encode(body.senha()));
        newTeacher.setRg(body.rg());
        newTeacher.setDataNascimento(body.dataNascimento());
        newTeacher.setTelefone(body.telefone());
        newTeacher.setCep(body.cep());
        newTeacher.setEndereco(body.endereco());
        newTeacher.setNumero(body.numero());
        newTeacher.setComplemento(body.complemento());
        newTeacher.setCidade(body.cidade());
        newTeacher.setEstado(body.estado());
        newTeacher.setNivelEnsino(body.nivelEnsino());
        newTeacher.setMateriaEspecializada(body.materiaEspecializada());
        newTeacher.setModalidadeEnsino(body.modalidadeEnsino());

        teacherRepository.save(newTeacher);

        String token = tokenService.generateToken(newTeacher);
        return ResponseEntity.ok(new ResponseDto(newTeacher.getNome(), token));
    }

}