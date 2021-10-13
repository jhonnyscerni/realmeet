package br.com.siberius.realmeet.api.model;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.OffsetDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AllocationDTO {

    @Schema(example = "1")
    private Long id;

    private RoomDTO roomDTO;

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
