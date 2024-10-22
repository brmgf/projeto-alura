package br.com.alura.ProjetoAlura.user;

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
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @InjectMocks
    private UserService service;

    @Mock
    private UserRepository repository;

    @Test
    void shouldFindUserByEmailSuccessfully() {
        when(repository.findByEmail(any())).thenReturn(Optional.of(new User()));

        var result = service.findByEmail("charles@alura.com.br");

        assertNotNull(result);
    }

    @Test
    void shouldNotFindUserByEmailWhenItDoesNotExists() {
        when(repository.findByEmail(any())).thenReturn(Optional.empty());

        var error = assertThrows(DomainException.class, () -> service.findByEmail("charles@alura.com.br"));

        assertEquals("There is no registered user with the email address informed, please try again.", error.getMessage());
    }
}