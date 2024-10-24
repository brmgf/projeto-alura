package br.com.alura.ProjetoAlura.registration.validator;

import br.com.alura.ProjetoAlura.course.model.Status;
import br.com.alura.ProjetoAlura.registration.model.Registration;
import br.com.alura.ProjetoAlura.registration.repository.RegistrationRepository;
import br.com.alura.ProjetoAlura.user.Role;
import br.com.alura.ProjetoAlura.util.DomainException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class RegistrationValidator {

    private final RegistrationRepository repository;

    public void validate(Registration registration) {
        var course = registration.getCourse();
        var user = registration.getUser();

        if (!Status.ACTIVE.equals(course.getStatus())) {
            throw new DomainException("It is not possible to complete the registration because the course is inactive.");
        }

        // Fiquei com dúvida se um instrutor poderia se matricular em um curso,
        // mas como não achei esse cenário nos requisitos, permiti a matrícula
        // porém com uma validação se ele não está se matriculando em um curso
        // ministrado por ele mesmo
        if (Role.INSTRUCTOR.equals(registration.getUser().getRole())
                && course.getInstructor().getId().equals(registration.getUser().getId())) {
            throw new DomainException("The instructor cannot register for their own course.");
        }

        boolean existsRegistration = repository.existsByCourseIdAndUserId(course.getId(), user.getId());
        if (existsRegistration) {
            throw new DomainException("This student is already registered in this course");
        }
    }
}
