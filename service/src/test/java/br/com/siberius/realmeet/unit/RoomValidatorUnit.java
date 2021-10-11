package br.com.siberius.realmeet.unit;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import br.com.siberius.realmeet.api.model.InputRoomDTO;
import br.com.siberius.realmeet.core.BaseUnitTest;
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
        InputRoomDTO inputRoomDTO = InputRoomDTO.builder()
            .name(TestConstants.DEFAULT_ROOM_NAME)
            .seats(TestConstants.DEFAULT_ROOM_SEATS).build();

        Set<ConstraintViolation<InputRoomDTO>> violations = validator.validate(inputRoomDTO);
        assertTrue(violations.isEmpty());
    }

    @Test
    void estValidateWhenRoomNameIsMissing() {
        InputRoomDTO inputRoomDTO = InputRoomDTO.builder()
            .name("")
            .seats(TestConstants.DEFAULT_ROOM_SEATS).build();

        Set<ConstraintViolation<InputRoomDTO>> violations = validator.validate(inputRoomDTO);
        assertFalse(violations.isEmpty());
    }

    @Test
    void testValidateWhenRoomSeatsIsNull() {
        InputRoomDTO inputRoomDTO = InputRoomDTO.builder()
            .name(TestConstants.DEFAULT_ROOM_NAME)
            .seats(null).build();

        Set<ConstraintViolation<InputRoomDTO>> violations = validator.validate(inputRoomDTO);
        assertFalse(violations.isEmpty());
    }

}
