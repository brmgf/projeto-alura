package br.com.alura.ProjetoAlura.registration.validator;

import br.com.alura.ProjetoAlura.course.model.Course;
import br.com.alura.ProjetoAlura.course.model.Status;
import br.com.alura.ProjetoAlura.registration.model.Registration;
import br.com.alura.ProjetoAlura.registration.repository.RegistrationRepository;
import br.com.alura.ProjetoAlura.user.Role;
import br.com.alura.ProjetoAlura.user.User;
import br.com.alura.ProjetoAlura.util.DomainException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RegistrationValidatorTest {

    @InjectMocks
    private RegistrationValidator validator;

    @Mock
    private RegistrationRepository repository;

    @Test
    void shouldValidateRegistrationSuccessfully() {
        var registration = Registration.builder()
                .course(Course.builder().status(Status.ACTIVE).build())
                .user(User.builder().role(Role.STUDENT).build())
                .build();

        assertDoesNotThrow(() -> validator.validate(registration));
    }

    @Test
    void shouldNotValidateRegistrationWhenCourseIsNotActive() {
        var registration = Registration.builder()
                .course(Course.builder().status(Status.INACTIVE).build())
                .user(User.builder().role(Role.STUDENT).build())
                .build();

        var error = assertThrows(DomainException.class, () -> validator.validate(registration));

        assertEquals("It is not possible to complete the registration because the course is inactive.", error.getMessage());
    }

    @Test
    void shouldNotValidateRegistrationWhenInstructorIsTryingToRegisterInTheirOwnCourse() {
        var registration = Registration.builder()
                .course(Course.builder().status(Status.ACTIVE).instructor(User.builder().id(1L).build()).build())
                .user(User.builder().id(1L).role(Role.INSTRUCTOR).build())
                .build();

        var error = assertThrows(DomainException.class, () -> validator.validate(registration));

        assertEquals("The instructor cannot register for their own course.", error.getMessage());
    }

    @Test
    void shouldNotValidateRegistrationWhenStudentIsAlreadyRegistratedInCourse() {
        var registration = Registration.builder()
                .course(Course.builder().status(Status.ACTIVE).build())
                .user(User.builder().role(Role.STUDENT).build())
                .build();

        when(repository.existsByCourseIdAndUserId(any(), any())).thenReturn(true);

        var error = assertThrows(DomainException.class, () -> validator.validate(registration));

        assertEquals("This student is already registered in this course", error.getMessage());
    }
}