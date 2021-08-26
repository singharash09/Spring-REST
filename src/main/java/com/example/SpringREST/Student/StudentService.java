package com.example.SpringREST.Student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired //dependency injection
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    public Optional<Student> getStudentById(Long studentId) {
        boolean exists = studentRepository.existsById(studentId);
        if(!exists)
            throw new IllegalStateException("student with id: " + studentId + "does not exists");
        return studentRepository.findById(studentId);
    }

    public void addNewStudent(Student student) {
        Optional<Student> s = studentRepository.findByEmail(student.getEmail());
        if(s.isPresent())
            throw new IllegalStateException("email taken");
        studentRepository.save(student);
    }

    public void deleteStudent(Long studentId) {
        boolean exists = studentRepository.existsById(studentId);
        if(!exists)
            throw new IllegalStateException("student with id ");
        studentRepository.deleteById(studentId);
    }

    @Transactional
    public void updateStudent(Long studentId, String name, String email) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalStateException("Student with id " + studentId + " does not exist"));

        if(name!=null && name.length()>0) {
            student.setName(name);
        }

        if(email!=null && email.length()>0){
            Optional<Student> s = studentRepository.findByEmail(email);

            if(s.isPresent())
                throw new IllegalStateException("email taken");
            student.setEmail(email);
        }

    }
}
