package com.ong.ong.domain.user;

import jakarta.persistence.Entity;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Student extends baseUser {
    private String nomeEscolaAtual;
}
