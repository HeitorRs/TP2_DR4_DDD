package info.heitor.tp2armenio;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import info.heitor.tp2armenio.domain.Assinatura;
import info.heitor.tp2armenio.domain.EstadoAssinatura;
import info.heitor.tp2armenio.eventos.EstadoAssinaturaMudou;
import info.heitor.tp2armenio.infra.AssinaturaRepository;
import info.heitor.tp2armenio.infra.AssinaturaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Date;

public class AssinaturaServiceTest {

    @Mock
    private AssinaturaRepository assinaturaRepository;

    @InjectMocks
    private AssinaturaService assinaturaService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCriarAssinaturaPublicaEvento() {

        Long idAssinatura = 123L;
        Assinatura assinatura = Assinatura.builder()
                .id(idAssinatura)
                .clienteId(1L)
                .dataAssinatura(null)
                .estado(null)
                .intervalo(30)
                .pedidoId(1L)
                .build();

        when(assinaturaRepository.save(assinatura)).thenReturn(assinatura);

        Assinatura response = assinaturaService.criarAssinatura(assinatura);

        assertEquals(assinatura, response);
    }


    @Test
    void testCancelarAssinatura() {

        Long idAssinatura = 123L;
        Date dataAssinatura = new Date();
        Assinatura assinatura = spy(new Assinatura());
        assinatura.setId(idAssinatura);
        assinatura.setDataAssinatura(dataAssinatura);
        when(assinaturaRepository.save(assinatura)).thenReturn(assinatura);

        assinaturaService.cancelarAssinatura(assinatura);

        ArgumentCaptor<EstadoAssinaturaMudou> eventoCaptor = ArgumentCaptor.forClass(EstadoAssinaturaMudou.class);
        verify(assinatura).publicarEvento(eventoCaptor.capture());

        EstadoAssinaturaMudou evento = eventoCaptor.getValue();
        assertEquals(EstadoAssinatura.CANCELADA, evento.getNovoStatus());
        assertEquals(idAssinatura, evento.getIdAssinatura());
        assertEquals(dataAssinatura, evento.quandoMudou());

        verify(assinaturaRepository).delete(assinatura);
    }
}