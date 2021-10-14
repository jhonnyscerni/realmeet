package br.com.siberius.realmeet.domain.service;

import br.com.siberius.realmeet.api.model.AllocationDTO;
import br.com.siberius.realmeet.api.model.InputAllocationDTO;
import br.com.siberius.realmeet.api.model.filter.AllocationFilter;
import br.com.siberius.realmeet.domain.entity.Allocation;
import br.com.siberius.realmeet.domain.entity.Room;
import br.com.siberius.realmeet.domain.exception.EntidadeEmUsoException;
import br.com.siberius.realmeet.domain.exception.error.AllocationNotFoundException;
import br.com.siberius.realmeet.domain.mapper.AllocationMapper;
import br.com.siberius.realmeet.domain.repository.AllocationRepository;
import br.com.siberius.realmeet.infrastruct.repository.AllocationSpecification;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AllocationService {

    private static final String MSG_ALLOCATION_EM_USO
        = "Allocation de código %d não pode ser removida, pois está em uso";

    private final AllocationMapper allocationMapper;
    private final AllocationRepository allocationRepository;
    private final RoomService roomService;

    public AllocationDTO buscarPorIdDTO(Long id) {
        return allocationRepository.findById(id).map(allocationMapper::toModel)
            .orElseThrow(() -> new AllocationNotFoundException(id));
    }

    public Allocation buscarPorId(Long id) {
        return allocationRepository.findById(id)
            .orElseThrow(() -> new AllocationNotFoundException(id));
    }

    public Page<AllocationDTO> buscarAllocation(AllocationFilter allocationFilter, Pageable pageable){
        Page<Allocation> allocations = allocationRepository.findAll(
            new AllocationSpecification(allocationFilter), pageable);
        List<AllocationDTO> pacienteModelList =
            allocations.getContent().stream().map(allocationMapper::toModel).collect(Collectors.toList());
        return new PageImpl<>(
            pacienteModelList, pageable, allocations.getTotalElements());
    }

    public List<AllocationDTO> buscarTodos() {
        return allocationRepository.findAll().stream().map(allocationMapper::toModel).collect(Collectors.toList());
    }

    public AllocationDTO salvar(InputAllocationDTO inputRoomDTO, Allocation allocation) {
        Room room = roomService.findById(inputRoomDTO.getRoomId());

        allocationMapper.update(allocation, inputRoomDTO);
       // allocationMapper.toEntity(allocation, room);
        allocationRepository.save(allocation);
        return allocationMapper.toModel(allocation, room);
    }

    public AllocationDTO update(Long id, InputAllocationDTO inputAllocationDTO) {
        Allocation allocation = buscarPorId(id);
        return salvar(inputAllocationDTO, allocation);
    }

    public AllocationDTO create(InputAllocationDTO inputRoomDTO) {
        return salvar(inputRoomDTO, Allocation.builder().build());
    }

    public void excluir(Long id) {
        try {
            allocationRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new AllocationNotFoundException(id);
        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(String.format(MSG_ALLOCATION_EM_USO, id));
        }
    }
}
