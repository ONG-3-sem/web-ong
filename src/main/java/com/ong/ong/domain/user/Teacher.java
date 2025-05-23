package com.ong.ong.domain.user;

import com.ong.ong.dto.TeacherRegisterDto;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;

@Entity
@Table(name = "professores")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Teacher extends BaseUser {
    private String nivelEnsino;
    private String materiaEspecializada;
    private String modalidadeEnsino;

   public Teacher(TeacherRegisterDto dto, PasswordEncoder encoder){
       super(dto, encoder);
       this.nivelEnsino = dto.nivelEnsino();
       this.materiaEspecializada = dto.materiaEspecializada();
       this.modalidadeEnsino = dto.modalidadeEnsino();
   }
}
