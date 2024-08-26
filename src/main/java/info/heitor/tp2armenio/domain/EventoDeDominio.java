package info.heitor.tp2armenio.domain;

import java.util.Date;

public class EventoDeDominio {

    private Long id;
    private Date momento;

    public EventoDeDominio() {
    }

    public EventoDeDominio(Long id, Date momento) {
        this.id = id;
        this.momento = momento;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getMomento() {
        return momento;
    }

    public void setMomento(Date momento) {
        this.momento = momento;
    }

    public String getTipo() {
        return "EventoDeDominio";
    }
}
