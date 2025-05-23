package com.ong.ong.domain.user;

import com.ong.ong.dto.StudentRegisterDto;
import com.ong.ong.dto.TeacherRegisterDto;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public abstract class BaseUser {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String nome;
    private String cpf;
    private String email;
    private String senha;
    private String rg;
    private LocalDate dataNascimento;
    private String telefone;
    private String cep;
    private String endereco;
    private String numero;
    private String complemento;
    private String cidade;
    private String estado;

    public BaseUser(StudentRegisterDto dto, PasswordEncoder encoder) {
        this.nome = dto.nome();
        this.cpf = dto.cpf();
        this.email = dto.email();
        this.senha = encoder.encode(dto.senha());
        this.rg = dto.rg();
        this.dataNascimento = dto.dataNascimento();
        this.telefone = dto.telefone();
        this.cep = dto.cep();
        this.endereco = dto.endereco();
        this.numero = dto.numero();
        this.complemento = dto.complemento();
        this.cidade = dto.cidade();
        this.estado = dto.estado();
    }


    public BaseUser(TeacherRegisterDto dto, PasswordEncoder encoder) {
        this.nome = dto.nome();
        this.cpf = dto.cpf();
        this.email = dto.email();
        this.senha = encoder.encode(dto.senha());
        this.rg = dto.rg();
        this.dataNascimento = dto.dataNascimento();
        this.telefone = dto.telefone();
        this.cep = dto.cep();
        this.endereco = dto.endereco();
        this.numero = dto.numero();
        this.complemento = dto.complemento();
        this.cidade = dto.cidade();
        this.estado = dto.estado();
    }

}
