package info.heitor.tp2armenio.domain;

import info.heitor.tp2armenio.eventos.EstadoAssinaturaMudou;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

@Entity
@Table(name = "ASSINATURA")
@AllArgsConstructor@NoArgsConstructor@Data@Builder
public class Assinatura  {

    private static final Logger log = LoggerFactory.getLogger(Assinatura.class);

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Column(name = "CLIENTE_ID", nullable = false)
    private Long clienteId;

    @Column(name = "DATA_ASSINATURA")
    @Temporal(TemporalType.DATE)
    private Date dataAssinatura;

    @Column(name = "ESTADO")
    private EstadoAssinatura estado;

    @Column(name = "INTERVALO",nullable = false)
    private int intervalo; //Intervalo de dias para realização do pedido novamente

    @Column(name = "PEDIDO_ID", nullable = false)
    private Long pedidoId;

    public void alterarEstado(EstadoAssinatura novoEstado) {
        if (novoEstado == null) {
            throw new IllegalArgumentException("O estado não pode ser nulo");
        }
        this.estado = novoEstado;

        EstadoAssinaturaMudou evento = new EstadoAssinaturaMudou(this.id, novoEstado, new Date());
        publicarEvento(evento);
    }

    public void publicarEvento(EstadoAssinaturaMudou evento) {
        //EventoDeDominio.publicar(new assinaturaEvento(this.id);
        log.info("Evento publicado: Assinatura de id " + evento.getId() + " foi " + evento.getNovoStatus() + " em " + evento.getMomento());
    }

}
