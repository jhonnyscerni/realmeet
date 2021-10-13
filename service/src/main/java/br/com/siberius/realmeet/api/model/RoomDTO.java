package br.com.siberius.realmeet.api.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoomDTO {

    @Schema(example = "1")
    private Long id;

    @Schema(example = "Room A")
    private String name;

    @Schema(example = "10")
    private Integer seats;

    @Schema(example = "true")
    private Boolean active;

    public void ativar() {
        setActive(true);
    }
}
