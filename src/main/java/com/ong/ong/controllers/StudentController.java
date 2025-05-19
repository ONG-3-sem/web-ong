package com.ong.ong.controllers;

import com.ong.ong.domain.user.Student
        ;
import com.ong.ong.dto.StudentRegisterDto;
import com.ong.ong.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/aluno")
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<String> cadastrarAluno(@RequestBody StudentRegisterDto dto){
        if(studentRepository.findByEmail(dto.email()).isPresent()){
            return ResponseEntity.badRequest().body("Email já cadastrado");
        }

        Student aluno = new Student();
        aluno.setNome(dto.nome());
        aluno.setCpf(dto.cpf());
        aluno.setEmail(dto.email());
        aluno.setSenha(passwordEncoder.encode(dto.senha()));
        aluno.setRg(dto.rg());
        aluno.setDataNascimento(dto.dataNascimento());
        aluno.setTelefone(dto.telefone());
        aluno.setCep(dto.cep());
        aluno.setEndereco(dto.endereco());
        aluno.setNumero(dto.numero());
        aluno.setComplemento(dto.complemento());
        aluno.setCidade(dto.cidade());
        aluno.setEstado(dto.estado());
        aluno.setNomeEscolaAtual(dto.nomeEscolaAtual());

        studentRepository.save(aluno);

        return ResponseEntity.ok("Aluno cadastrado com sucesso");
    }
}
