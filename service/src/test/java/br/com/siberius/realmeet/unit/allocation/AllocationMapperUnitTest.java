package br.com.siberius.realmeet.unit.allocation;

import static br.com.siberius.realmeet.utils.MapperUtils.allocationMapper;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import br.com.siberius.realmeet.api.model.AllocationDTO;
import br.com.siberius.realmeet.api.model.InputAllocationDTO;
import br.com.siberius.realmeet.api.model.RoomDTO;
import br.com.siberius.realmeet.core.BaseUnitTest;
import br.com.siberius.realmeet.domain.entity.Allocation;
import br.com.siberius.realmeet.domain.entity.Room;
import br.com.siberius.realmeet.domain.entity.embeddable.Employee;
import br.com.siberius.realmeet.domain.mapper.AllocationMapper;
import br.com.siberius.realmeet.utils.TestConstants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AllocationMapperUnitTest extends BaseUnitTest {

    private AllocationMapper victim;

    @BeforeEach
    void setupEach() {
        victim = allocationMapper();
    }


    @Test
    void testupdate() {
        InputAllocationDTO inputAllocationDTO =
            InputAllocationDTO.builder()
                .subject("Alterar Subject")
                .roomId(2L)
                .employeeName("Alterar Name")
                .employeeEmail("Alterar Email")
                .startAt(TestConstants.DEFAULT_ALLOCATION_START_AT)
                .endAt(TestConstants.DEFAULT_ALLOCATION_END_AT)
                .build();
        Room room = Room.builder()
            .id(TestConstants.DEFAULT_ROOM_ID)
            .name(TestConstants.DEFAULT_ROOM_NAME)
            .seats(TestConstants.DEFAULT_ROOM_SEATS).build();
        Allocation allocation =
            Allocation.builder()
                .id(1L)
                .subject(TestConstants.DEFAULT_ALLOCATION_SUBJECT)
                .room(room)
                .employee(Employee.builder().name(TestConstants.DEFAULT_EMPLOYEE_NAME).email(TestConstants.DEFAULT_EMPLOYEE_EMAIL).build())
                .startAt(TestConstants.DEFAULT_ALLOCATION_START_AT)
                .endAt(TestConstants.DEFAULT_ALLOCATION_END_AT).build();

        victim.update(allocation, inputAllocationDTO);

        assertEquals(inputAllocationDTO.getSubject(), allocation.getSubject());
        assertNotNull(allocation.getRoom().getId());
        assertEquals(inputAllocationDTO.getEmployeeName(), allocation.getEmployee().getName());
        assertEquals(inputAllocationDTO.getEmployeeEmail(), allocation.getEmployee().getEmail());
        assertEquals(inputAllocationDTO.getStartAt(), allocation.getStartAt());
        assertEquals(inputAllocationDTO.getEndAt(), allocation.getEndAt());
    }

    @Test
    void testFromAllocationDTOToEntity() {
        RoomDTO roomDTO = RoomDTO.builder()
            .id(TestConstants.DEFAULT_ROOM_ID)
            .name(TestConstants.DEFAULT_ROOM_NAME)
            .seats(TestConstants.DEFAULT_ROOM_SEATS).build();
        AllocationDTO allocationDTO =
            AllocationDTO.builder()
                .subject(TestConstants.DEFAULT_ALLOCATION_SUBJECT)
                .roomDTO(roomDTO)
                .employeeName(TestConstants.DEFAULT_EMPLOYEE_NAME)
                .employeeEmail(TestConstants.DEFAULT_EMPLOYEE_EMAIL)
                .startAt(TestConstants.DEFAULT_ALLOCATION_START_AT)
                .endAt(TestConstants.DEFAULT_ALLOCATION_END_AT)
                .build();

        Allocation allocation = victim.toEntity(allocationDTO);

        assertEquals(allocationDTO.getSubject(), allocation.getSubject());
        assertNotNull(allocation.getRoom().getId());
        assertEquals(allocationDTO.getEmployeeName(), allocation.getEmployee().getName());
        assertEquals(allocationDTO.getEmployeeEmail(), allocation.getEmployee().getEmail());
        assertEquals(allocationDTO.getStartAt(), allocation.getStartAt());
        assertEquals(allocationDTO.getEndAt(), allocation.getEndAt());
    }

    @Test
    void testFromEntityToAllocationDTO() {
        Room room = Room.builder()
            .id(TestConstants.DEFAULT_ROOM_ID)
            .name(TestConstants.DEFAULT_ROOM_NAME)
            .seats(TestConstants.DEFAULT_ROOM_SEATS).build();
        Allocation allocation =
            Allocation.builder()
                .id(1L)
                .subject(TestConstants.DEFAULT_ALLOCATION_SUBJECT)
                .room(room)
                .employee(Employee.builder().name(TestConstants.DEFAULT_EMPLOYEE_NAME).email(TestConstants.DEFAULT_EMPLOYEE_EMAIL).build())
                .startAt(TestConstants.DEFAULT_ALLOCATION_START_AT)
                .endAt(TestConstants.DEFAULT_ALLOCATION_END_AT).build();
        AllocationDTO allocationDTO = victim.toModel(allocation);

        assertEquals(allocation.getSubject(), allocationDTO.getSubject());
        assertEquals(allocation.getId(), allocationDTO.getId());
        assertNotNull(allocationDTO.getRoomDTO().getId());
        assertEquals(allocation.getRoom().getId(), allocationDTO.getRoomDTO().getId());
        assertEquals(allocation.getEmployee().getName(), allocationDTO.getEmployeeName());
        assertEquals(allocation.getEmployee().getEmail(), allocationDTO.getEmployeeEmail());
        assertEquals(allocation.getStartAt(), allocationDTO.getStartAt());
        assertEquals(allocation.getEndAt(), allocationDTO.getEndAt());
    }
}