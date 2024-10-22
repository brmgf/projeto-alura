package br.com.alura.ProjetoAlura.registration.controller;

import br.com.alura.ProjetoAlura.course.model.Course;
import br.com.alura.ProjetoAlura.course.service.CourseService;
import br.com.alura.ProjetoAlura.registration.service.RegistrationService;
import br.com.alura.ProjetoAlura.registration.dto.NewRegistrationDTO;
import br.com.alura.ProjetoAlura.registration.dto.RegistrationAssembler;
import br.com.alura.ProjetoAlura.registration.dto.RegistrationReportItem;
import br.com.alura.ProjetoAlura.registration.dto.RegistrationReportItemAssembler;
import br.com.alura.ProjetoAlura.registration.dto.RegistrationReportItemProjection;
import br.com.alura.ProjetoAlura.registration.model.Registration;
import br.com.alura.ProjetoAlura.user.User;
import br.com.alura.ProjetoAlura.user.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class RegistrationController {

    private final CourseService courseService;
    private final UserService userService;
    private final RegistrationAssembler assembler;
    private final RegistrationService registrationService;
    private final RegistrationReportItemAssembler itemAssembler;

    @PostMapping("/registration/new")
    public ResponseEntity register(@Valid @RequestBody NewRegistrationDTO newRegistration) {
        Course course = courseService.findByCode(newRegistration.getCourseCode());
        User user = userService.findByEmail(newRegistration.getStudentEmail());
        Registration registration = assembler.toEntity(course, user);

        registrationService.register(registration);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/registration/report")
    public ResponseEntity<List<RegistrationReportItem>> report() {
        List<RegistrationReportItemProjection> projections = registrationService.findRegistrationsOrderByCourseWithTheMostRegistrationNumber();
        List<RegistrationReportItem> items = itemAssembler.toItemList(projections);

        return ResponseEntity.ok(items);
    }

}
