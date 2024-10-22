package br.com.alura.ProjetoAlura.registration.service;

import br.com.alura.ProjetoAlura.registration.repository.RegistrationRepository;
import br.com.alura.ProjetoAlura.registration.validator.RegistrationValidator;
import br.com.alura.ProjetoAlura.registration.dto.RegistrationReportItemProjection;
import br.com.alura.ProjetoAlura.registration.model.Registration;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class RegistrationService {

    private final RegistrationValidator validator;
    private final RegistrationRepository repository;

    @Transactional
    public void register(Registration registration) {
        validator.validate(registration);
        repository.save(registration);
    }

    @Transactional(readOnly = true)
    public List<RegistrationReportItemProjection> findRegistrationsOrderByCourseWithTheMostRegistrationNumber() {
        return repository.findRegistrationsOrderByCourseWithTheMostRegistrationNumber();
    }
}
