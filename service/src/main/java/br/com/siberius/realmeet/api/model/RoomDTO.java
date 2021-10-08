package br.com.siberius.realmeet.api.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RoomDTO {

    private Long id;

    private String name;

    private Integer seats;

    private Boolean active;
}
