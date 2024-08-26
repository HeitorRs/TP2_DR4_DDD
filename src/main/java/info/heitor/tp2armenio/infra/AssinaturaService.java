package info.heitor.tp2armenio.infra;

import info.heitor.tp2armenio.domain.Assinatura;
import info.heitor.tp2armenio.domain.EstadoAssinatura;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;


@Service
public class AssinaturaService {
    @Autowired
    private AssinaturaRepository assinaturaRepository;

    public Assinatura pegarAssinatura(Long id){
        return assinaturaRepository.findById(id).orElse(null);
    }

    public Assinatura criarAssinatura(Assinatura assinatura) {

        assinatura.setDataAssinatura(new Date());
        assinatura.setEstado(EstadoAssinatura.ATIVA);
        assinaturaRepository.save(assinatura);
        assinatura.alterarEstado(assinatura.getEstado());

        return assinatura;
    }

    public void cancelarAssinatura(Assinatura assinatura){
        assinatura.alterarEstado(EstadoAssinatura.CANCELADA);
        assinaturaRepository.delete(assinatura);
    }
}
