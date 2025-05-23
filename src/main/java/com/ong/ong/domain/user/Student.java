package com.ong.ong.domain.user;

import com.ong.ong.dto.StudentRegisterDto;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;

@Entity
@Table(name = "alunos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Student extends BaseUser {
    private String nomeEscolaAtual;

    public Student(StudentRegisterDto dto, PasswordEncoder encoder) {
        super(dto, encoder);
        this.nomeEscolaAtual = dto.nomeEscolaAtual();
    }

}
