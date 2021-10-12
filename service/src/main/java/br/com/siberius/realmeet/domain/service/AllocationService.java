package br.com.siberius.realmeet.domain.service;

import br.com.siberius.realmeet.api.model.AllocationDTO;
import br.com.siberius.realmeet.api.model.InputAllocationDTO;
import br.com.siberius.realmeet.api.model.RoomDTO;
import br.com.siberius.realmeet.domain.entity.Allocation;
import br.com.siberius.realmeet.domain.entity.Room;
import br.com.siberius.realmeet.domain.exception.EntidadeEmUsoException;
import br.com.siberius.realmeet.domain.exception.error.AllocationNotFoundException;
import br.com.siberius.realmeet.domain.mapper.AllocationMapper;
import br.com.siberius.realmeet.domain.repository.AllocationRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class AllocationService {

    private static final String MSG_ALLOCATION_EM_USO
        = "Allocation de código %d não pode ser removida, pois está em uso";

    private final AllocationMapper allocationMapper;
    private final AllocationRepository allocationRepository;
    private final RoomService roomService;

    public AllocationDTO findByIdDTO(Long id) {
        return allocationRepository.findById(id).map(allocationMapper::toModel)
            .orElseThrow(() -> new AllocationNotFoundException(id));
    }

    public Allocation findById(Long id) {
        return allocationRepository.findById(id)
            .orElseThrow(() -> new AllocationNotFoundException(id));
    }

    public List<AllocationDTO> findAll() {
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
        Allocation allocation = findById(id);
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
