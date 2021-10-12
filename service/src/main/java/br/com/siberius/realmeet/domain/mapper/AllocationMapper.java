package br.com.siberius.realmeet.domain.mapper;

import br.com.siberius.realmeet.api.model.AllocationDTO;
import br.com.siberius.realmeet.api.model.InputAllocationDTO;
import br.com.siberius.realmeet.domain.entity.Allocation;
import br.com.siberius.realmeet.domain.entity.Room;
import br.com.siberius.realmeet.utils.ModelMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface AllocationMapper extends ModelMapper<Allocation, AllocationDTO> {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updateAt", ignore = true)
    @Mapping(source = "allocationDTO.employeeName", target = "allocation.employee.name")
    @Mapping(source = "allocationDTO.employeeEmail", target = "allocation.employee.email")
    @Mapping(source = "roomId", target = "room.id")
    void update(@MappingTarget Allocation allocation, InputAllocationDTO allocationDTO);

    @Mapping(source = "entity.room", target = "roomDTO")
    @Mapping(source = "entity.employee.name", target = "employeeName")
    @Mapping(source = "entity.employee.email", target = "employeeEmail")
    AllocationDTO toModel(Allocation entity);

    @Mapping(source = "entity.id", target = "id")
    @Mapping(source = "room", target = "roomDTO")
    @Mapping(source = "entity.employee.name", target = "employeeName")
    @Mapping(source = "entity.employee.email", target = "employeeEmail")
    AllocationDTO toModel(Allocation entity, Room room);

    @Mapping(source = "model.employeeName", target = "employee.name")
    @Mapping(source = "model.employeeEmail", target = "employee.email")
    @Mapping(source = "model.roomDTO", target = "room")
    Allocation toEntity(AllocationDTO model);

//    @Mapping(target = "id", ignore = true)
//    @Mapping(source = "room", target = "room")
//    Allocation toEntity(Allocation model, Room room);
}
