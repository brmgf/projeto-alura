package br.com.alura.ProjetoAlura.course;

import br.com.alura.ProjetoAlura.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class NewCourseDTODisassembler {

    private final UserService userService;

    public Course toEntity(NewCourseDTO newCourseDTO) {
        Course newCourse = new Course();
        newCourse.setName(newCourseDTO.getName());
        newCourse.setCode(newCourseDTO.getCode());
        newCourse.setDescription(newCourseDTO.getDescription());
        newCourse.setStatus(Status.ACTIVE);
        newCourse.setInstructor(userService.getInstructorByEmail(newCourseDTO.getInstructorEmail()));

        return newCourse;
    }
}
