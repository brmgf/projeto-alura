package br.com.alura.ProjetoAlura.registration.dto;

import br.com.alura.ProjetoAlura.course.model.Course;
import br.com.alura.ProjetoAlura.user.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class RegistrationAssemblerTest {

    @InjectMocks
    private RegistrationAssembler assembler;

    @Test
    void shouldConvertCourseAndUserToRegistrationSuccessfully() {
        var result = assembler.toEntity(new Course(), new User());

        assertNotNull(result);
        assertNotNull(result.getCourse());
        assertNotNull(result.getUser());
    }
}