package pl.coderslab.web.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pl.coderslab.model.Student;
import pl.coderslab.service.StudentService;

@RestController
@RequestMapping("/api")
public class StudentResource {

    private final StudentService studentService;

    public StudentResource(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping(value = "/student-by-last-name/{last-name}")
    public ResponseEntity<Student> getStudentByLastName(@PathVariable("last-name") String lastName) {
        return ResponseEntity.ok(studentService.getStudentByLastName(lastName));
    }
}
