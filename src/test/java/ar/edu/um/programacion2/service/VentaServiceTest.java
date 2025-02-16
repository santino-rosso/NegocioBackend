package ar.edu.um.programacion2.service;

import static net.bytebuddy.matcher.ElementMatchers.any;
import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import ar.edu.um.programacion2.domain.User;
import ar.edu.um.programacion2.domain.Venta;
import ar.edu.um.programacion2.repository.*;
import ar.edu.um.programacion2.service.dto.AdicionalVentaDTO;
import ar.edu.um.programacion2.service.dto.UserDTO;
import ar.edu.um.programacion2.service.dto.VentaDTO;
import ar.edu.um.programacion2.service.dto.VentaServicioDTO;
import ar.edu.um.programacion2.service.mapper.VentaMapper;
import java.math.BigDecimal;
import java.util.Optional;
import java.util.Set;
import javax.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

@ExtendWith(MockitoExtension.class)
class VentaServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private VentaMapper ventaMapper;

    @Mock
    private WebClient webClient;

    @InjectMocks
    private VentaService ventaService;

    @Test
    void prepararVentaSuccessfully() {
        VentaDTO ventaDTO = new VentaDTO();
        User user = new User();
        user.setId(1L);
        ventaDTO.setUser(new UserDTO());
        ventaDTO.getUser().setId(1L);
        ventaDTO.setAdicionales(Set.of());
        ventaDTO.setCaracteristicas(Set.of());
        ventaDTO.setPersonalizaciones(Set.of());

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(ventaMapper.toEntity(ventaDTO)).thenReturn(new Venta());

        Venta result = ventaService.prepararVenta(ventaDTO);

        assertNotNull(result);
        assertEquals(user, result.getUser());
    }

    @Test
    void prepararVentaThrowsExceptionWhenUserNotFound() {
        VentaDTO ventaDTO = new VentaDTO();
        ventaDTO.setUser(new UserDTO());
        ventaDTO.getUser().setId(1L);

        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> ventaService.prepararVenta(ventaDTO));
    }

    @Test
    void realizarVentaServicioSuccessfully() {
        VentaDTO ventaDTO = new VentaDTO();
        AdicionalVentaDTO adicionalVentaDTO = new AdicionalVentaDTO();
        adicionalVentaDTO.setId(1L);
        adicionalVentaDTO.setPrecio(BigDecimal.valueOf(100.0));
        ventaDTO.setAdicionales(Set.of(adicionalVentaDTO));
        ventaDTO.setPersonalizaciones(Set.of());

        VentaServicioDTO result = ventaService.realizarVentaServicio(ventaDTO);

        assertNotNull(result);
        assertEquals(1, result.getAdicionales().size());
        assertEquals(BigDecimal.valueOf(100.0), result.getAdicionales().iterator().next().getPrecio());
    }
}
