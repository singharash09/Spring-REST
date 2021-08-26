package com.example.SpringREST.Student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student,Long> {

    //@Query("SELECT * FROM student WHERE email=?1")
    Optional<Student> findByEmail(String email);
}
