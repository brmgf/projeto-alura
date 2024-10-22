package br.com.alura.ProjetoAlura.user;

import br.com.alura.ProjetoAlura.util.DomainException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository repository;

    @Transactional(readOnly = true)
    public User getInstructorByEmail(String email) {
        return repository.findByEmailAndRole(email, Role.INSTRUCTOR).orElseThrow(
                () -> new DomainException("There is no registered instructor with the email address informed, please try again.")
        );
    }

    @Transactional(readOnly = true)
    public User findByEmail(String email) {
        return repository.findByEmail(email).orElseThrow(
                () -> new DomainException("There is no registered user with the email address informed, please try again.")
        );
    }
}
