package br.com.alura.ProjetoAlura.course;

import br.com.alura.ProjetoAlura.util.DomainException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public String validateCode(String code) {
        if (!pattern_course_code.matcher(code).matches()) {
            log.error("Error validating course code: the code is invalid.");
            throw new DomainException("The code entered is not valid, please try again.");
        }

        return code;
    }
}
