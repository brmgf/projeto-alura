package br.com.alura.ProjetoAlura.registration.dto;

import br.com.alura.ProjetoAlura.enviroment.EnvRegistrationReportItemProjection;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class RegistrationReportItemAssemblerTest {

    @InjectMocks
    private RegistrationReportItemAssembler assembler;

    @Test
    void shouldConvertRegistrationReportItemProjectionToRegistrationReportItem() {
        var projections = List.of(EnvRegistrationReportItemProjection.getProjection());

        var result = assembler.toItemList(projections);

        assertNotNull(result);
        assertEquals(1, result.size());
    }
}