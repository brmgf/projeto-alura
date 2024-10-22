package br.com.alura.ProjetoAlura.course.service;

import br.com.alura.ProjetoAlura.course.model.Course;
import br.com.alura.ProjetoAlura.course.model.Status;
import br.com.alura.ProjetoAlura.course.repository.CourseRepository;
import br.com.alura.ProjetoAlura.course.validator.CourseValidator;
import br.com.alura.ProjetoAlura.util.DomainException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Slf4j
@RequiredArgsConstructor
@Service
public class CourseService {

    private final CourseValidator validator;
    private final CourseRepository repository;

    @Transactional
    public void registerCourse(@Valid Course course) {
        validator.validate(course);
        repository.save(course);
    }

    @Transactional(readOnly = true)
    public Course findByCode(String code) {
        return repository.findByCode(code).orElseThrow(
                () -> new DomainException("There is no course with an informed code, please try again.")
        );
    }

    @Transactional
    public void inactive(@Valid Course course) {
        if (Status.ACTIVE.equals(course.getStatus())) {
            course.setStatus(Status.INACTIVE);
            course.setInactivateDateTime(LocalDateTime.now());
            repository.save(course);
        }
    }
}
