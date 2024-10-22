package br.com.alura.ProjetoAlura.course.validator;

import br.com.alura.ProjetoAlura.course.model.Course;
import br.com.alura.ProjetoAlura.user.Role;
import br.com.alura.ProjetoAlura.user.User;
import br.com.alura.ProjetoAlura.util.DomainException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class CourseValidatorTest {

    @InjectMocks
    private CourseValidator validator;

    @Test
    void shouldValidateCourseSuccessfully() {
        var course = Course.builder().code("unit-tests")
                .instructor(User.builder().role(Role.INSTRUCTOR).build())
                .build();

        assertDoesNotThrow(() -> validator.validate(course));
    }

    @Test
    void shouldNotValidateCourseWhenCodeIsNotValid() {
        var course = Course.builder().code("***")
                .instructor(User.builder().role(Role.INSTRUCTOR).build())
                .build();

        var error = assertThrows(DomainException.class, () -> validator.validate(course));

        assertEquals("The code entered is not valid, please try again.", error.getMessage());
    }

    @Test
    void shouldNotValidateCourseWhenUserIsNotInstructor() {
        var course = Course.builder().code("unit-tests")
                .instructor(User.builder().role(Role.STUDENT).build())
                .build();

        var error = assertThrows(DomainException.class, () -> validator.validate(course));

        assertEquals("There is no registered instructor with the email address informed, please try again.", error.getMessage());
    }
}