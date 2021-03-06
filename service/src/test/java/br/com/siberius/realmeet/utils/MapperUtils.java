package br.com.siberius.realmeet.utils;

import br.com.siberius.realmeet.domain.mapper.AllocationMapper;
import br.com.siberius.realmeet.domain.mapper.RoomMapper;
import lombok.AllArgsConstructor;
import org.mapstruct.factory.Mappers;

@AllArgsConstructor
public final class MapperUtils {

    public static RoomMapper roomMapper() {
        return Mappers.getMapper(RoomMapper.class);
    }

    public static AllocationMapper allocationMapper() {
        return Mappers.getMapper(AllocationMapper.class);
    }
}