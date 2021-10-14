package br.com.siberius.realmeet.api.model.filter;

import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;
import java.time.OffsetDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AllocationFilter implements Serializable {

    @Schema(example = "Name Exemplo")
    private String employeeName;

    @Schema(example = "email@email.com")
    private String employeeEmail;

    @Schema(example = "Assunto exemplo")
    private String subject;

    @Schema(example = "2019-12-01T18:09:02.70844Z")
    private OffsetDateTime startAt;

    @Schema(example = "2019-15-01T18:09:02.70844Z")
    private OffsetDateTime endAt;
}
