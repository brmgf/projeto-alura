package br.com.alura.ProjetoAlura.registration.service;

import br.com.alura.ProjetoAlura.enviroment.EnvRegistrationReportItemProjection;
import br.com.alura.ProjetoAlura.registration.model.Registration;
import br.com.alura.ProjetoAlura.registration.repository.RegistrationRepository;
import br.com.alura.ProjetoAlura.registration.validator.RegistrationValidator;
import br.com.alura.ProjetoAlura.util.DomainException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RegistrationServiceTest {

    @InjectMocks
    private RegistrationService service;

    @Mock
    private RegistrationValidator validator;

    @Mock
    private RegistrationRepository repository;

    @Test
    void shouldRegistrateStudentInCourseSuccessfully() {
        service.register(new Registration());

        verify(validator, times(1)).validate(any());
        verify(repository, times(1)).save(any());
    }

    @Test
    void shouldNotRegistrateStudentInCourseWhenItIsNotValid() {
        var registration = new Registration();

        doThrow(DomainException.class).when(validator).validate(any());

        assertThrows(DomainException.class, () -> service.register(registration));

        verify(repository, times(0)).save(any());
    }

    @Test
    void shouldFindRegistrationsForReportSuccessfully() {
        var projections = List.of(EnvRegistrationReportItemProjection.getProjection());

        when(repository.findRegistrationsOrderByCourseWithTheMostRegistrationNumber()).thenReturn(projections);

        var result = service.findRegistrationsOrderByCourseWithTheMostRegistrationNumber();

        assertNotNull(result);
        assertEquals(1, result.size());
    }
}