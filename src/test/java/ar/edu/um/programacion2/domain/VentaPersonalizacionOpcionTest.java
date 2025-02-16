package ar.edu.um.programacion2.domain;

import static org.assertj.core.api.Assertions.assertThat;

import ar.edu.um.programacion2.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class VentaPersonalizacionOpcionTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(VentaPersonalizacionOpcion.class);
        VentaPersonalizacionOpcion ventaPersonalizacionOpcion1 = new VentaPersonalizacionOpcion();
        ventaPersonalizacionOpcion1.setId(1L);
        VentaPersonalizacionOpcion ventaPersonalizacionOpcion2 = new VentaPersonalizacionOpcion();
        ventaPersonalizacionOpcion2.setId(ventaPersonalizacionOpcion1.getId());
        assertThat(ventaPersonalizacionOpcion1).isEqualTo(ventaPersonalizacionOpcion2);
        ventaPersonalizacionOpcion2.setId(2L);
        assertThat(ventaPersonalizacionOpcion1).isNotEqualTo(ventaPersonalizacionOpcion2);
        ventaPersonalizacionOpcion1.setId(null);
        assertThat(ventaPersonalizacionOpcion1).isNotEqualTo(ventaPersonalizacionOpcion2);
    }
}
