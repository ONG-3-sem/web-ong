package com.ong.ong.domain.user;

import com.ong.ong.dto.StudentRegisterDto;
import jakarta.persistence.Entity;
import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Student extends baseUser {
    private String nomeEscolaAtual;

    public Student(StudentRegisterDto dto, PasswordEncoder encoder) {
        this.nome = dto.nome();
        this.cpf = dto.cpf();
        this.email = dto.email();
        this.rg = dto.rg();
        this.dataNascimento = dto.dataNascimento();
        this.telefone = dto.telefone();
        this.nomeMae = dto.nomeMae();
        this.nomeEscolaAtual = dto.nomeEscolaAtual();
        this.cep = dto.cep();
        this.endereco = dto.endereco();
        this.numero = dto.numero();
        this.complemento = dto.complemento();
        this.cidade = dto.cidade();
        this.estado = dto.estado();
        this.senha = encoder.encode(dto.senha());
    }

}
