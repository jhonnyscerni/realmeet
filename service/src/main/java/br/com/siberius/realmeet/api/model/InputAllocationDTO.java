package br.com.siberius.realmeet.api.model;

import java.time.OffsetDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InputAllocationDTO {

    private Long roomId;

    private String employeeName;

    private String employeeEmail;

    private String subject;

    private OffsetDateTime startAt;

    private OffsetDateTime endAt;

}