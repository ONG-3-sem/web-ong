package com.ong.ong.domain.user;

import jakarta.persistence.Entity;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Teacher extends baseUser {
    private String nivelEnsino;
    private String materiaEspecializada;
    private String modalidadeEnsino;
}
