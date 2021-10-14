package br.com.siberius.realmeet.domain.service;

import br.com.siberius.realmeet.api.model.InputRoomDTO;
import br.com.siberius.realmeet.api.model.RoomDTO;
import br.com.siberius.realmeet.domain.entity.Room;
import br.com.siberius.realmeet.domain.exception.EntidadeEmUsoException;
import br.com.siberius.realmeet.domain.exception.NegocioException;
import br.com.siberius.realmeet.domain.exception.error.RoomNotFoundException;
import br.com.siberius.realmeet.domain.mapper.RoomMapper;
import br.com.siberius.realmeet.domain.repository.RoomRepository;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class RoomService {

    private static final String MSG_ROOM_EM_USO
        = "Room de código %d não pode ser removida, pois está em uso";

    private final RoomRepository roomRepository;

    private final RoomMapper roomMapper;

    public RoomDTO buscarPorIdActive(Long id) {
        Objects.requireNonNull(id);
        return roomRepository.findByIdAndActive(id, true).map(roomMapper::toModel).orElseThrow(() -> new RoomNotFoundException(id));
    }

    public Room findById(Long id) {
        Objects.requireNonNull(id);
        return roomRepository.findById(id).orElseThrow(() -> new RoomNotFoundException(id));
    }

    public List<RoomDTO> buscarTodos() {
        return roomRepository.findAll().stream().map(roomMapper::toModel).collect(Collectors.toList());
    }


    @Transactional
    public RoomDTO create(InputRoomDTO inputRoomDTO) {
        return salvar(inputRoomDTO, Room.builder().build());
    }

    @Transactional
    public RoomDTO update(Long id, InputRoomDTO inputRoomDTO) {
        Room room = findById(id);
        return salvar(inputRoomDTO, room);
    }

    @Transactional
    public RoomDTO salvar(InputRoomDTO inputRoomDTO, Room room) {
        Optional<Room> roomExistente = roomRepository.findByNameAndActive(inputRoomDTO.getName(), true);
        if (roomExistente.isPresent() && !roomExistente.get().equals(room)) {
            throw new NegocioException(
                String.format("Já existe um room com o nome %s Ativo", inputRoomDTO.getName()));
        }

        roomMapper.update(room, inputRoomDTO);
        roomRepository.save(room);
        return roomMapper.toModel(room);
    }

    @Transactional
    public void excluir(Long id) {
        try {
            roomRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new RoomNotFoundException(id);
        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(String.format(MSG_ROOM_EM_USO, id));
        }
    }

    @Transactional
    public void ativar(Long id) {
        RoomDTO roomDTOAtual = buscarPorIdActive(id);
        roomDTOAtual.ativar();
        roomRepository.save(roomMapper.toEntity(roomDTOAtual));
    }

    @Transactional
    public void desativar(Long id) {
        buscarPorIdActive(id);
        roomRepository.desactive(id);
    }

}
