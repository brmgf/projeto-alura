package br.com.alura.ProjetoAlura.enviroment;

import br.com.alura.ProjetoAlura.registration.dto.RegistrationReportItemProjection;

public class EnvRegistrationReportItemProjection {

    public static RegistrationReportItemProjection getProjection() {
        return new RegistrationReportItemProjection() {

            @Override
            public String getCourseName() {
                return "Unit Tests";
            }

            @Override
            public String getCourseCode() {
                return "unit-tests";
            }

            @Override
            public String getInstructorName() {
                return "Maria";
            }

            @Override
            public String getInstructorEmail() {
                return "maria@alura.com.br";
            }

            @Override
            public Long getTotalRegistrations() {
                return 10L;
            }
        };
    }
}
