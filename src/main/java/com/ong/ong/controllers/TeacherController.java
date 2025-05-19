package com.ong.ong.controllers;

import com.ong.ong.domain.user.Teacher;
import com.ong.ong.dto.TeacherRegisterDto;
import com.ong.ong.repositories.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/professor")
public class TeacherController {

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<String> cadastrarProfessor(@RequestBody TeacherRegisterDto dto){
        if(teacherRepository.findByEmail(dto.email()).isPresent()){
            return ResponseEntity.badRequest().body("Email já cadastrado");
        }

        Teacher professor = new Teacher();
        professor.setNome(dto.nome());
        professor.setCpf(dto.cpf());
        professor.setEmail(dto.email());
        professor.setSenha(passwordEncoder.encode(dto.senha()));
        professor.setRg(dto.rg());
        professor.setDataNascimento(dto.dataNascimento());
        professor.setTelefone(dto.telefone());
        professor.setCep(dto.cep());
        professor.setEndereco(dto.endereco());
        professor.setNumero(dto.numero());
        professor.setComplemento(dto.complemento());
        professor.setCidade(dto.cidade());
        professor.setEstado(dto.estado());
        professor.setNivelEnsino(dto.nivelEnsino());
        professor.setMateriaEspecializada(dto.materiaEspecializada());
        professor.setModalidadeEnsino(dto.modalidadeEnsino());

        teacherRepository.save(professor);

        return ResponseEntity.ok("Professor cadastrado com sucesso");
    }
}
