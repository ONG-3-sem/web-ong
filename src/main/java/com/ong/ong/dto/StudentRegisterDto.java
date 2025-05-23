package com.ong.ong.dto;

import java.time.LocalDate;

public record StudentRegisterDto(
        String nome,
        String cpf,
        String email,
        String senha,
        String rg,
        LocalDate dataNascimento,
        String telefone,
        String cep,
        String endereco,
        String numero,
        String complemento,
        String cidade,
        String estado,
        String nomeEscolaAtual
) {}
