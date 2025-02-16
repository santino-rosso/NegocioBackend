package ar.edu.um.programacion2.service.mapper;

import ar.edu.um.programacion2.domain.Opcion;
import ar.edu.um.programacion2.domain.Personalizacion;
import ar.edu.um.programacion2.domain.VentaPersonalizacionOpcion;
import ar.edu.um.programacion2.service.dto.*;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link VentaPersonalizacionOpcion} and its DTO {@link VentaPersonalizacionOpcionDTO}.
 */
@Mapper(componentModel = "spring")
public interface VentaPersonalizacionOpcionMapper extends EntityMapper<VentaPersonalizacionOpcionDTO, VentaPersonalizacionOpcion> {
    @Mapping(target = "opcion", source = "opcion", qualifiedByName = "toDtoOpcionVentaId")
    @Mapping(target = "personalizacion", source = "personalizacion", qualifiedByName = "toDtoPersonalizacionVentaId")
    VentaPersonalizacionOpcionDTO toDto(VentaPersonalizacionOpcion s);

    @Named("toDtoPersonalizacionVentaId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    PersonalizacionVentaDTO toDtoPersonalizacionVentaId(Personalizacion personalizacion);

    @Named("toDtoOpcionVentaId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    OpcionVentaDTO toDtoOpcionVentaId(Opcion opcion);
}
