package br.com.siberius.realmeet.unit;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import br.com.siberius.realmeet.api.model.CreateRoomDTO;
import br.com.siberius.realmeet.core.BaseUnitTest;
import br.com.siberius.realmeet.domain.entity.Room;
import br.com.siberius.realmeet.utils.TestConstants;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RoomValidatorUnit extends BaseUnitTest {

    private Validator validator;

    @BeforeEach
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void testValidateWhenRoomIsValid() {
        CreateRoomDTO createRoomDTO = CreateRoomDTO.builder()
            .name(TestConstants.DEFAULT_ROOM_NAME)
            .seats(TestConstants.DEFAULT_ROOM_SEATS).build();

        Set<ConstraintViolation<CreateRoomDTO>> violations = validator.validate(createRoomDTO);
        assertTrue(violations.isEmpty());
    }

    @Test
    void estValidateWhenRoomNameIsMissing() {
        CreateRoomDTO createRoomDTO = CreateRoomDTO.builder()
            .name("")
            .seats(TestConstants.DEFAULT_ROOM_SEATS).build();

        Set<ConstraintViolation<CreateRoomDTO>> violations = validator.validate(createRoomDTO);
        assertFalse(violations.isEmpty());
    }

    @Test
    void testValidateWhenRoomSeatsIsNull() {
        CreateRoomDTO createRoomDTO = CreateRoomDTO.builder()
            .name(TestConstants.DEFAULT_ROOM_NAME)
            .seats(null).build();

        Set<ConstraintViolation<CreateRoomDTO>> violations = validator.validate(createRoomDTO);
        assertFalse(violations.isEmpty());
    }

}
