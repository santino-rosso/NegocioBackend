package ar.edu.um.programacion2.service.mapper;

import ar.edu.um.programacion2.domain.Adicional;
import ar.edu.um.programacion2.domain.Caracteristica;
import ar.edu.um.programacion2.domain.Venta;
import ar.edu.um.programacion2.service.dto.*;
import java.util.Set;
import java.util.stream.Collectors;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Venta} and its DTO {@link VentaDTO}.
 */
@Mapper(componentModel = "spring")
public interface VentaMapper extends EntityMapper<VentaDTO, Venta> {
    @Mapping(target = "adicionales", source = "adicionales", qualifiedByName = "adicionalIdSet")
    @Mapping(target = "caracteristicas", source = "caracteristicas", qualifiedByName = "caracteristicaIdSet")
    VentaDTO toDto(Venta s);

    @Mapping(target = "removeAdicionales", ignore = true)
    @Mapping(target = "removeCaracteristicas", ignore = true)
    Venta toEntity(VentaDTO ventaDTO);

    @Named("adicionalId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "nombre", source = "nombre")
    @Mapping(target = "descripcion", source = "descripcion")
    @Mapping(target = "precio", source = "precio")
    AdicionalVentaDTO toDtoAdicionalId(Adicional adicional);

    @Named("adicionalIdSet")
    default Set<AdicionalVentaDTO> toDtoAdicionalIdSet(Set<Adicional> adicional) {
        return adicional.stream().map(this::toDtoAdicionalId).collect(Collectors.toSet());
    }

    @Named("caracteristicaId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "nombre", source = "nombre")
    @Mapping(target = "descripcion", source = "descripcion")
    CaracteristicaVentaDTO toDtoCaracteristicaId(Caracteristica caracteristica);

    @Named("caracteristicaIdSet")
    default Set<CaracteristicaVentaDTO> toDtoCaracteristicaIdSet(Set<Caracteristica> caracteristica) {
        return caracteristica.stream().map(this::toDtoCaracteristicaId).collect(Collectors.toSet());
    }
}
