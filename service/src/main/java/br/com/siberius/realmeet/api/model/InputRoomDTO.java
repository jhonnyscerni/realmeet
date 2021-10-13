package br.com.siberius.realmeet.api.model;

import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InputRoomDTO {

    @NotBlank
    @Schema(example = "Room A")
    private String name;

    @NotNull
    @Schema(example = "10")
    private Integer seats;

}
