package br.com.alura.ProjetoAlura.registration.repository;

import br.com.alura.ProjetoAlura.registration.dto.RegistrationReportItemProjection;
import br.com.alura.ProjetoAlura.registration.model.Registration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RegistrationRepository extends JpaRepository<Registration, Long> {

    boolean existsByCourseIdAndUserId(Long courseId, Long userId);

    @Query(nativeQuery = true, value =
            """
                SELECT
                    c.name AS courseName,
                    c.code AS courseCode,
                    u.name AS instructorName,
                    u.email AS instructorEmail,
                    COUNT(r.course_id) AS totalRegistrations
                FROM REGISTRATION r
                JOIN COURSE c on r.course_id = c.id
                JOIN USER u on c.user_id = u.id
                GROUP BY r.course_id
                ORDER BY COUNT(r.course_id) DESC;
            """)
    List<RegistrationReportItemProjection> findRegistrationsOrderByCourseWithTheMostRegistrationNumber();
}
