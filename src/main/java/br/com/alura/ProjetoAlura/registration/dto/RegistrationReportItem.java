package br.com.alura.ProjetoAlura.registration.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RegistrationReportItem {

    private String courseName;
    private String courseCode;
    private String instructorName;
    private String instructorEmail;
    private Long totalRegistrations;
}
