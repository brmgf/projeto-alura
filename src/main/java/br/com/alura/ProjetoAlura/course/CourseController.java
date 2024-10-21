package br.com.alura.ProjetoAlura.course;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class CourseController {

    private final NewCourseDTODisassembler disassembler;
    private final CourseService courseService;

    @PostMapping("/course/new")
    public ResponseEntity createCourse(@Valid @RequestBody NewCourseDTO newCourseDTO) {
        Course newCourse = disassembler.toEntity(newCourseDTO);
        courseService.registerCourse(newCourse);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/course/{code}/inactive")
    public ResponseEntity createCourse(@PathVariable("code") String courseCode) {
        Course course = courseService.findByCode(courseCode);
        courseService.inactive(course);
        return ResponseEntity.ok().build();
    }

}
