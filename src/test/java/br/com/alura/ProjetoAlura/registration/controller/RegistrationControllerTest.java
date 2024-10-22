package br.com.alura.ProjetoAlura.registration.controller;

import br.com.alura.ProjetoAlura.course.model.Course;
import br.com.alura.ProjetoAlura.course.service.CourseService;
import br.com.alura.ProjetoAlura.registration.dto.NewRegistrationDTO;
import br.com.alura.ProjetoAlura.registration.dto.RegistrationAssembler;
import br.com.alura.ProjetoAlura.registration.dto.RegistrationReportItemAssembler;
import br.com.alura.ProjetoAlura.registration.model.Registration;
import br.com.alura.ProjetoAlura.registration.service.RegistrationService;
import br.com.alura.ProjetoAlura.user.User;
import br.com.alura.ProjetoAlura.user.UserService;
import br.com.alura.ProjetoAlura.util.DomainException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RegistrationControllerTest {

    @InjectMocks
    private RegistrationController controller;

    @Mock
    private CourseService courseService;

    @Mock
    private UserService userService;

    @Mock
    private RegistrationAssembler assembler;

    @Mock
    private RegistrationService registrationService;

    @Mock
    private RegistrationReportItemAssembler itemAssembler;

    @Test
    void shouldRegisterStudentInCourseSuccessfully() {
        when(courseService.findByCode(any())).thenReturn(new Course());
        when(userService.findByEmail(any())).thenReturn(new User());
        when(assembler.toEntity(any(), any())).thenReturn(new Registration());

        var result = controller.register(new NewRegistrationDTO());

        verify(registrationService, times(1)).register(any());

        assertEquals(HttpStatus.CREATED, result.getStatusCode());
    }

    @Test
    void shouldNotRegisterStudentInCourseWhenDoesFindCourseWithTheCodeInformed() {
        var dto = new NewRegistrationDTO();

        doThrow(DomainException.class).when(courseService).findByCode(any());

        assertThrows(DomainException.class, () -> controller.register(dto));

        verify(userService, times(0)).findByEmail(any());
        verify(assembler, times(0)).toEntity(any(), any());
        verify(registrationService, times(0)).register(any());
    }

    @Test
    void shouldNotRegisterStudentInCourseWhenDoesFindUserWithTheEmailAddressInformed() {
        var dto = new NewRegistrationDTO();

        when(courseService.findByCode(any())).thenReturn(new Course());
        doThrow(DomainException.class).when(userService).findByEmail(any());

        assertThrows(DomainException.class, () -> controller.register(dto));

        verify(assembler, times(0)).toEntity(any(), any());
        verify(registrationService, times(0)).register(any());
    }

    @Test
    void shouldGetReportWithRegistrationsSuccessfully() {
        when(registrationService.findRegistrationsOrderByCourseWithTheMostRegistrationNumber()).thenReturn(new ArrayList<>());
        when(itemAssembler.toItemList(any())).thenReturn(new ArrayList<>());

        var result = controller.report();

        assertNotNull(result);
    }
}