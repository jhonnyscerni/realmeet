package br.com.siberius.realmeet.unit;

import br.com.siberius.realmeet.api.model.CreateRoomDTO;
import br.com.siberius.realmeet.api.model.RoomDTO;
import br.com.siberius.realmeet.core.BaseUnitTest;
import br.com.siberius.realmeet.domain.entity.Room;
import br.com.siberius.realmeet.domain.mapper.RoomMapper;
import br.com.siberius.realmeet.utils.MapperUtils;
import br.com.siberius.realmeet.utils.TestConstants;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RoomMapperUnit extends BaseUnitTest {

    private RoomMapper victin;

    @BeforeEach
    void setup() {
        victin = MapperUtils.roomMapper();
    }

    @Test
    public void testFromEntityToDto() {
        Room room = Room.builder()
            .id(TestConstants.DEFAULT_ROOM_ID)
            .name(TestConstants.DEFAULT_ROOM_NAME)
            .seats(TestConstants.DEFAULT_ROOM_SEATS).build();
        RoomDTO roomDTO = victin.toModel(room);

        Assertions.assertEquals(room.getId(), roomDTO.getId());
        Assertions.assertEquals(room.getName(), roomDTO.getName());
        Assertions.assertEquals(room.getSeats(), roomDTO.getSeats());
    }

    @Test
    public void testCreateRoomDtoToEntity() {
        CreateRoomDTO createRoomDTO = CreateRoomDTO.builder()
            .name(TestConstants.DEFAULT_ROOM_NAME)
            .seats(TestConstants.DEFAULT_ROOM_SEATS).build();
        Room room = victin.fromCreateRoomDtoToEntity(createRoomDTO);

        Assertions.assertEquals(room.getName(), createRoomDTO.getName());
        Assertions.assertEquals(room.getSeats(), createRoomDTO.getSeats());
    }
}
