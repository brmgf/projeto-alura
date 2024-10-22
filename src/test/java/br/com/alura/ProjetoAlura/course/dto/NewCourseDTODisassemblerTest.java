package br.com.alura.ProjetoAlura.course.dto;

import br.com.alura.ProjetoAlura.user.UserService;
import br.com.alura.ProjetoAlura.util.DomainException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class NewCourseDTODisassemblerTest {

    @InjectMocks
    private NewCourseDTODisassembler disassembler;

    @Mock
    private UserService userService;

    @Test
    void shoudConvertNewCourseDTOToCourseSuccessfully() {
        var dto = new NewCourseDTO();

        var result = disassembler.toEntity(dto);

        verify(userService, times(1)).findByEmail(dto.getInstructorEmail());

        assertNotNull(result);
    }

    @Test
    void shouldNotConvertNewCourseDTOToCourseWhenDoesFindUserWithTheEmailAddressInformed() {
        var dto = new NewCourseDTO();

        doThrow(DomainException.class).when(userService).findByEmail(any());

        assertThrows(DomainException.class, () -> disassembler.toEntity(dto));

        verify(userService, times(1)).findByEmail(any());
    }
}