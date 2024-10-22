package br.com.alura.ProjetoAlura.registration.dto;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RegistrationReportItemAssembler {

    private RegistrationReportItem toItem(RegistrationReportItemProjection projection) {
        return RegistrationReportItem.builder()
                .courseName(projection.getCourseName())
                .courseCode(projection.getCourseCode())
                .instructorName(projection.getInstructorName())
                .instructorEmail(projection.getInstructorEmail())
                .totalRegistrations(projection.getTotalRegistrations())
                .build();
    }

    public List<RegistrationReportItem> toItemList(List<RegistrationReportItemProjection> projections) {
        return projections.stream().map(this::toItem).toList();
    }
}
