package br.com.siberius.realmeet.unit;

import br.com.siberius.realmeet.api.model.RoomDTO;
import br.com.siberius.realmeet.core.BaseUnitTest;
import br.com.siberius.realmeet.domain.entity.Room;
import br.com.siberius.realmeet.domain.repository.RoomRepository;
import br.com.siberius.realmeet.domain.service.RoomService;
import br.com.siberius.realmeet.utils.MapperUtils;
import br.com.siberius.realmeet.utils.TestConstants;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;


public class RoomServiceUnit extends BaseUnitTest {

    private RoomService victim;

    @Mock
    private RoomRepository roomRepository;

    @BeforeEach
    private void setup() {
        victim = new RoomService(roomRepository, MapperUtils.roomMapper());
    }

    @Test
    public void getRoom() {
        Room room = Room.builder()
            .id(TestConstants.DEFAULT_ROOM_ID)
            .name(TestConstants.DEFAULT_ROOM_NAME)
            .seats(TestConstants.DEFAULT_ROOM_SEATS).build();
        Mockito.when(roomRepository.findById(TestConstants.DEFAULT_ROOM_ID)).thenReturn(Optional.of(room));

        RoomDTO roomDTO = victim.findById(TestConstants.DEFAULT_ROOM_ID);
        Assertions.assertEquals(room.getId(), roomDTO.getId());
        Assertions.assertEquals(room.getName(), roomDTO.getName());
        Assertions.assertEquals(room.getSeats(), roomDTO.getSeats());
    }


}
