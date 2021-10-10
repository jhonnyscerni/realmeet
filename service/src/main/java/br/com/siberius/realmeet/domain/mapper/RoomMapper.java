package br.com.siberius.realmeet.domain.mapper;

import br.com.siberius.realmeet.api.model.CreateRoomDTO;
import br.com.siberius.realmeet.api.model.RoomDTO;
import br.com.siberius.realmeet.domain.entity.Room;
import br.com.siberius.realmeet.utils.ModelMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoomMapper extends ModelMapper<Room, RoomDTO> {

    Room fromCreateRoomDtoToEntity(CreateRoomDTO createRoomDTO);
}
