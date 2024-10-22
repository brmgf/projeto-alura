package br.com.alura.ProjetoAlura.course.validator;

import br.com.alura.ProjetoAlura.course.model.Course;
import br.com.alura.ProjetoAlura.user.Role;
import br.com.alura.ProjetoAlura.util.DomainException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Slf4j
@Component
public class CourseValidator {

    private static final String COURSE_CODE_REGEX = "^[a-z]+(-[a-z]+){0,10}$";
    private static final Pattern pattern_course_code = Pattern.compile(COURSE_CODE_REGEX);

    public void validate(Course course) {
        if (!pattern_course_code.matcher(course.getCode()).matches()) {
            log.error("Error validating course code: the code is invalid.");
            throw new DomainException("The code entered is not valid, please try again.");
        }

        if (!Role.INSTRUCTOR.equals(course.getInstructor().getRole())) {
            throw new DomainException("There is no registered instructor with the email address informed, please try again.");
        }
    }
}
