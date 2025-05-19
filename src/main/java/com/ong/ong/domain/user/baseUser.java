package com.ong.ong.domain.user;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public abstract class baseUser {
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
}
