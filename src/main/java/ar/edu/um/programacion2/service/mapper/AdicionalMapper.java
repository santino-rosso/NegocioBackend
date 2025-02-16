package ar.edu.um.programacion2.service.mapper;

import ar.edu.um.programacion2.domain.Adicional;
import ar.edu.um.programacion2.domain.Dispositivo;
import ar.edu.um.programacion2.service.dto.AdicionalDTO;
import ar.edu.um.programacion2.service.dto.DispositivoDTO;
import java.util.Set;
import java.util.stream.Collectors;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Adicional} and its DTO {@link AdicionalDTO}.
 */
@Mapper(componentModel = "spring")
public interface AdicionalMapper extends EntityMapper<AdicionalDTO, Adicional> {
    @Mapping(target = "dispositivos", source = "dispositivos", qualifiedByName = "dispositivoIdSet")
    AdicionalDTO toDto(Adicional s);

    @Mapping(target = "dispositivos", ignore = true)
    @Mapping(target = "removeDispositivos", ignore = true)
    Adicional toEntity(AdicionalDTO adicionalDTO);

    @Named("dispositivoId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    DispositivoDTO toDtoDispositivoId(Dispositivo dispositivo);

    @Named("dispositivoIdSet")
    default Set<DispositivoDTO> toDtoDispositivoIdSet(Set<Dispositivo> dispositivo) {
        return dispositivo.stream().map(this::toDtoDispositivoId).collect(Collectors.toSet());
    }
}
