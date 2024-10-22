package br.com.alura.ProjetoAlura.registration.dto;

public interface RegistrationReportItemProjection {

    String getCourseName();
    String getCourseCode();
    String getInstructorName();
    String getInstructorEmail();
    Long getTotalRegistrations();
}
