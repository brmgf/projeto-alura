package br.com.alura.ProjetoAlura.registration.dto;

import br.com.alura.ProjetoAlura.course.model.Course;
import br.com.alura.ProjetoAlura.registration.model.Registration;
import br.com.alura.ProjetoAlura.user.User;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class RegistrationAssembler {

    public Registration toEntity(Course course, User user) {
        Registration registration = new Registration();
        registration.setRegistrationDateTime(LocalDateTime.now());
        registration.setCourse(course);
        registration.setUser(user);

        return registration;
    }
}
