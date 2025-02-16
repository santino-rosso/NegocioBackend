package ar.edu.um.programacion2.repository;

import ar.edu.um.programacion2.domain.VentaPersonalizacionOpcion;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the VentaPersonalizacionOpcion entity.
 */
@SuppressWarnings("unused")
@Repository
public interface VentaPersonalizacionOpcionRepository extends JpaRepository<VentaPersonalizacionOpcion, Long> {}
