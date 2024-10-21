package br.com.alura.ProjetoAlura.user;

import br.com.alura.ProjetoAlura.util.DomainException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository repository;

    @Transactional(readOnly = true)
    public User getInstructorByEmail(String email) {
        Optional<User> userOpt = repository.findByEmail(email);

        if (!userOpt.isPresent()) {
            log.error("Error finding instructor by email address: no registered user.");
            throw new DomainException("There is no registered user with the email address informed, please try again.");
        }

        User user = userOpt.get();
        if (!Role.INSTRUCTOR.equals(user.getRole())) {
            log.error("Error finding instructor by email adress: email address does not belong to an instructor.");
            throw new DomainException("The email address provided is not from an instructor, please try again.");
        }

        return user;
    }
}
