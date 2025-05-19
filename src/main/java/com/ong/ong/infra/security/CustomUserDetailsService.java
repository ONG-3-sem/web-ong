package com.ong.ong.infra.security;

import com.ong.ong.repositories.StudentRepository;
import com.ong.ong.repositories.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var alunoOpt = studentRepository.findByEmail(username);
        if(alunoOpt.isPresent()){
            var aluno = alunoOpt.get();
            return new org.springframework.security.core.userdetails.User(
                    aluno.getEmail(),
                    aluno.getSenha(),
                    List.of(new SimpleGrantedAuthority("ROLE_ALUNO")));
        }

        var professorOpt = teacherRepository.findByEmail(username);
        if(professorOpt.isPresent()){
            var professor = professorOpt.get();
            return new org.springframework.security.core.userdetails.User(
                    professor.getEmail(),
                    professor.getSenha(),
                    List.of(new SimpleGrantedAuthority("ROLE_PROFESSOR")));
        }

        throw new UsernameNotFoundException("User not found");
    }
}
