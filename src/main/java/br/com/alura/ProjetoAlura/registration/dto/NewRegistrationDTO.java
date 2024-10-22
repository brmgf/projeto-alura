package br.com.alura.ProjetoAlura.registration.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class NewRegistrationDTO {

    @NotBlank(message = "It is mandatory to enter a course code.")
    private String courseCode;

    @NotBlank(message = "It is mandatory to inform the student's email address.")
    @Email
    private String studentEmail;
}
