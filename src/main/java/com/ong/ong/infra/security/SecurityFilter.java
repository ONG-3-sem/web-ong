package com.ong.ong.infra.security;

import com.ong.ong.domain.user.Student;
import com.ong.ong.domain.user.Teacher;
import com.ong.ong.repositories.StudentRepository;
import com.ong.ong.repositories.TeacherRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String token = recoverToken(request);

        if (token != null) {
            String email = tokenService.validateToken(token);

            if (email != null) {
                Student student = studentRepository.findByEmail(email).orElse(null);

                if (student != null) {
                    var authorities = List.of(new SimpleGrantedAuthority("ROLE_STUDENT"));
                    var authentication = new UsernamePasswordAuthenticationToken(student, null, authorities);
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                } else {
                    Teacher teacher = teacherRepository.findByEmail(email)
                            .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
                    var authorities = List.of(new SimpleGrantedAuthority("ROLE_TEACHER"));
                    var authentication = new UsernamePasswordAuthenticationToken(teacher, null, authorities);
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        }

        filterChain.doFilter(request, response);
    }

    private String recoverToken(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        if (authHeader == null ) return null;
        return authHeader.replace("Bearer ", "");
    }
}
