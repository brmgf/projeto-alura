package br.com.alura.ProjetoAlura.course;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class NewCourseDTO {

    @NotBlank(message = "It is mandatory to enter a name.")
    @Length(max = 255)
    private String name;

    @NotBlank(message = "It is mandatory to enter a code.")
    @Length(min = 4, max = 10, message = "The code must be 4-10 characters long.")
    private String code;

    private String description;

    @NotBlank(message = "It is mandatory to enter the instructor email address.")
    @Email
    private String instructorEmail;
}
