package br.com.siberius.realmeet.unit.room;

import br.com.siberius.realmeet.api.model.InputRoomDTO;
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
    public void testUpdate() {
        Room room = Room.builder()
            .name(TestConstants.DEFAULT_ROOM_NAME)
            .seats(TestConstants.DEFAULT_ROOM_SEATS).build();

        InputRoomDTO inputRoomDTO = InputRoomDTO.builder()
            .name(TestConstants.DEFAULT_ROOM_NAME)
            .seats(TestConstants.DEFAULT_ROOM_SEATS).build();

        victin.update(room, inputRoomDTO);

        Assertions.assertEquals(room.getName(), inputRoomDTO.getName());
        Assertions.assertEquals(room.getSeats(), inputRoomDTO.getSeats());
    }
}
