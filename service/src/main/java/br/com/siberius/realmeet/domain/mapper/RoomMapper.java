package br.com.siberius.realmeet.domain.mapper;

import br.com.siberius.realmeet.api.model.InputRoomDTO;
import br.com.siberius.realmeet.api.model.RoomDTO;
import br.com.siberius.realmeet.domain.entity.Room;
import br.com.siberius.realmeet.utils.ModelMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface RoomMapper extends ModelMapper<Room, RoomDTO> {

    @Mapping(target = "id", ignore = true)
    void update(@MappingTarget Room entity, InputRoomDTO model);
}
