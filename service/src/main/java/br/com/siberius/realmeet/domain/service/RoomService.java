package br.com.siberius.realmeet.domain.service;

import br.com.siberius.realmeet.api.model.RoomDTO;
import br.com.siberius.realmeet.domain.entity.Room;
import br.com.siberius.realmeet.domain.exception.error.RoomNotFoundException;
import br.com.siberius.realmeet.domain.mapper.RoomMapper;
import br.com.siberius.realmeet.domain.repository.RoomRepository;
import java.util.Objects;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RoomService {

    private final RoomRepository roomRepository;

    private final RoomMapper roomMapper;

    public RoomDTO findById(Long id) {
        Objects.requireNonNull(id);
        Room room = roomRepository.findById(id).orElseThrow(() -> new RoomNotFoundException(id));
        return roomMapper.toModel(room);
    }

}
