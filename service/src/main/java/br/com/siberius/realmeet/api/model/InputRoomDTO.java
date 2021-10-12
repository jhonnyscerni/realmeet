package br.com.siberius.realmeet.api.model;

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
    private String name;

    @NotNull
    private Integer seats;

}
