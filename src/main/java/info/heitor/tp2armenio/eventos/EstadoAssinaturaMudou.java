package info.heitor.tp2armenio.eventos;

import info.heitor.tp2armenio.domain.EstadoAssinatura;
import info.heitor.tp2armenio.domain.EventoDeDominio;

import java.util.Date;

public class EstadoAssinaturaMudou extends EventoDeDominio {

    private EstadoAssinatura novoStatus;


    public EstadoAssinaturaMudou() {
        super();
    }

    public EstadoAssinaturaMudou(Long idAssinatura, EstadoAssinatura novoStatus, Date momento) {
        super(idAssinatura, momento);
        this.novoStatus = novoStatus;
    }

    public Long getIdAssinatura() {
        return getId();
    }

    public EstadoAssinatura getNovoStatus() {
        return novoStatus;
    }

    public Date quandoMudou() {
        return getMomento();
    }

    @Override
    public String getTipo() {
        return "EstadoAssinaturaMudou";
    }
}
