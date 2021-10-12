package br.com.siberius.realmeet.unit.room;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

import br.com.siberius.realmeet.api.model.InputRoomDTO;
import br.com.siberius.realmeet.api.model.RoomDTO;
import br.com.siberius.realmeet.core.BaseUnitTest;
import br.com.siberius.realmeet.domain.entity.Room;
import br.com.siberius.realmeet.domain.exception.NegocioException;
import br.com.siberius.realmeet.domain.exception.error.RoomNotFoundException;
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


class RoomServiceUnit extends BaseUnitTest {

    private RoomService victim;

    @Mock
    private RoomRepository roomRepository;

    @BeforeEach
    private void setup() {
        victim = new RoomService(roomRepository, MapperUtils.roomMapper());
    }

    @Test
    void getRoomSucess() {
        Room room = Room.builder()
            .id(TestConstants.DEFAULT_ROOM_ID)
            .name(TestConstants.DEFAULT_ROOM_NAME)
            .seats(TestConstants.DEFAULT_ROOM_SEATS).build();
        Mockito.when(roomRepository.findById(TestConstants.DEFAULT_ROOM_ID)).thenReturn(Optional.of(room));

        RoomDTO roomDTO = victim.findByIdActive(TestConstants.DEFAULT_ROOM_ID);
        Assertions.assertEquals(room.getId(), roomDTO.getId());
        Assertions.assertEquals(room.getName(), roomDTO.getName());
        Assertions.assertEquals(room.getSeats(), roomDTO.getSeats());
    }

    @Test
    void getRoomNotFound() {
        Mockito.when(roomRepository.findById(TestConstants.DEFAULT_ROOM_ID)).thenReturn(Optional.empty());
        Assertions.assertThrows(RoomNotFoundException.class, () -> victim.findByIdActive(TestConstants.DEFAULT_ROOM_ID));
    }

    @Test
    void testCreateRoomSucess() {
        InputRoomDTO inputRoomDTO = InputRoomDTO.builder()
            .name(TestConstants.DEFAULT_ROOM_NAME)
            .seats(TestConstants.DEFAULT_ROOM_SEATS).build();

        RoomDTO roomDTO = victim.create(inputRoomDTO);

        Assertions.assertEquals(inputRoomDTO.getName(), roomDTO.getName());
        Assertions.assertEquals(inputRoomDTO.getSeats(), roomDTO.getSeats());

        Mockito.verify(roomRepository).save(any());
    }

    @Test
    void testValidateWhenRoomNameIsDuplicate() {
        Room room = Room.builder()
            .id(TestConstants.DEFAULT_ROOM_ID)
            .name(TestConstants.DEFAULT_ROOM_NAME)
            .seats(TestConstants.DEFAULT_ROOM_SEATS).build();
        given(roomRepository.findByNameAndActive(TestConstants.DEFAULT_ROOM_NAME, true))
            .willReturn(Optional.of(room));
        InputRoomDTO inputRoomDTO = InputRoomDTO.builder()
            .name(TestConstants.DEFAULT_ROOM_NAME)
            .seats(TestConstants.DEFAULT_ROOM_SEATS).build();

        Assertions.assertThrows(NegocioException.class, () -> victim.create(inputRoomDTO));
    }

}
