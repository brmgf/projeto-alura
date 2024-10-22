package br.com.alura.ProjetoAlura.course.controller;

import br.com.alura.ProjetoAlura.course.dto.NewCourseDTO;
import br.com.alura.ProjetoAlura.course.dto.NewCourseDTODisassembler;
import br.com.alura.ProjetoAlura.course.model.Course;
import br.com.alura.ProjetoAlura.course.service.CourseService;
import br.com.alura.ProjetoAlura.util.DomainException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CourseControllerTest {

    @InjectMocks
    private CourseController controller;

    @Mock
    private NewCourseDTODisassembler disassembler;

    @Mock
    private CourseService courseService;

    @Test
    void shouldCreateCourseSuccessfully() {
        when(disassembler.toEntity(any())).thenReturn(new Course());

        var result = controller.createCourse(new NewCourseDTO());

        verify(courseService, times(1)).registerCourse(any());

        assertEquals(HttpStatus.CREATED, result.getStatusCode());
    }

    @Test
    void shouldNotCreateCourseWhenDoesNotExistsUserWithTheEmailAddressInformed() {
        var dto = new NewCourseDTO();

        doThrow(DomainException.class).when(disassembler).toEntity(any());

        assertThrows(DomainException.class, () -> controller.createCourse(dto));

        verify(courseService, times(0)).registerCourse(any());
    }

    @Test
    void shouldInactiveCourseSuccessfully() {
        when(courseService.findByCode(any())).thenReturn(new Course());

        var result = controller.inactiveCourse("unit-tests");

        verify(courseService, times(1)).inactive(any());

        assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    @Test
    void shouldNotInactiveCourseWhenDoesFindCourseWithTheCodeInformed() {
        doThrow(DomainException.class).when(courseService).findByCode(any());

        assertThrows(DomainException.class, () -> controller.inactiveCourse("unit-tests"));

        verify(courseService, times(0)).inactive(any());
    }
}