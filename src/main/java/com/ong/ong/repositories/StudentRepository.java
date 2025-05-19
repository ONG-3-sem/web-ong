package com.ong.ong.repositories;

import com.ong.ong.domain.user.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, String> {
    Optional<Student> findByEmail(String email);
}
