package general.springboothomework.controller;

import general.springboothomework.entity.Student;
import general.springboothomework.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;


    @PostMapping
    public Student createStudent(@RequestBody Student student) {
        return studentRepository.save(student);
    }

    @GetMapping
    public List<Student> getAllStudents(@RequestParam("page") int page, @RequestParam("size") int size) {
        return studentRepository.findAll(PageRequest.of(page, size)).getContent();
    }

    @GetMapping("/by-gender")
    public List<Student> getStudentsByGender(@RequestParam("gender") String gender) {
        return studentRepository.findByGender(gender);
    }

    @GetMapping("/by-year")
    public List<Student> getStudentsByYearRange(@RequestParam("startYear") int startYear, @RequestParam("endYear") int endYear) {
        return studentRepository.findByBirthYearRange(startYear, endYear);
    }

    @GetMapping("/by-year-native")
    public List<Student> getStudentsByYearRangeNative(@RequestParam("startYear") int startYear, @RequestParam("endYear") int endYear) {
        return studentRepository.findByBirthYearRangeNative(startYear, endYear);
    }

    @GetMapping("/by-group/{groupId}")
    public List<Student> getStudentsByGroup(@PathVariable Long groupId) {
        return studentRepository.findByGroupId(groupId);
    }
}