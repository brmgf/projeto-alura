package br.com.alura.ProjetoAlura.course;

import br.com.alura.ProjetoAlura.util.DomainException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.regex.Pattern;

@Slf4j
@RequiredArgsConstructor
@Service
public class CourseService {

    private static final String COURSE_CODE_REGEX = "^[a-z]+(-[a-z]+){0,10}$";
    private static final Pattern pattern_course_code = Pattern.compile(COURSE_CODE_REGEX);

    private final CourseRepository repository;

    @Transactional
    public void registerCourse(@Valid Course course) {
        this.validateCode(course.getCode());
        repository.save(course);
    }

    private String validateCode(String code) {
        if (!pattern_course_code.matcher(code).matches()) {
            log.error("Error validating course code: the code is invalid.");
            throw new DomainException("The code entered is not valid, please try again.");
        }

        return code;
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
