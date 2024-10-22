package br.com.alura.ProjetoAlura.course.service;

import br.com.alura.ProjetoAlura.course.model.Course;
import br.com.alura.ProjetoAlura.course.model.Status;
import br.com.alura.ProjetoAlura.course.repository.CourseRepository;
import br.com.alura.ProjetoAlura.course.validator.CourseValidator;
import br.com.alura.ProjetoAlura.util.DomainException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CourseServiceTest {

    @InjectMocks
    private CourseService service;

    @Mock
    private CourseValidator validator;

    @Mock
    private CourseRepository repository;

    @Test
    void shouldRegisterCourseSuccessfully() {
        service.registerCourse(new Course());

        verify(validator, times(1)).validate(any());
        verify(repository, times(1)).save(any());
    }

    @Test
    void shouldNotRegisterCourseWhenItIsNotValid() {
        var course = new Course();

        doThrow(DomainException.class).when(validator).validate(any());

        assertThrows(DomainException.class, () -> service.registerCourse(course));

        verify(validator, times(1)).validate(any());
        verify(repository, times(0)).save(any());
    }

    @Test
    void shouldFindCourseByCodeSuccessfully() {
        when(repository.findByCode(any())).thenReturn(Optional.of(new Course()));

        var result = service.findByCode("unit-tests");

        assertNotNull(result);
    }

    @Test
    void shouldNotFindCourseByCodeWhenItDoesNotExists() {
        when(repository.findByCode(any())).thenReturn(Optional.empty());

        var error = assertThrows(DomainException.class, () -> service.findByCode("unit-tests"));

        assertEquals("There is no course with an informed code, please try again.", error.getMessage());
    }

    @Test
    void shouldInactiveActiveCourseSuccessfully() {
        var course = Course.builder().status(Status.ACTIVE).build();

        service.inactive(course);

        verify(repository, times(1)).save(any());
    }
}