package info.heitor.tp2armenio.infra;

import info.heitor.tp2armenio.domain.Assinatura;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class AssinaturaController {

    @Autowired
    private AssinaturaService assinaturaService;

    @PostMapping
    public ResponseEntity<?> post(@RequestBody Assinatura assinatura) {
        if (assinatura.getIntervalo() < 7) {
            return ResponseEntity.badRequest().body("Intervalo deve ser maior que 7 dias");
        }
        if (assinatura.getClienteId() == 0) {
            return ResponseEntity.badRequest().body("Id do Cliente inválido");
        }
        if (assinatura.getPedidoId() == 0) {
            return ResponseEntity.badRequest().body("Id do Pedido inválido");
        }
        Assinatura assinaturaCriada = assinaturaService.criarAssinatura(assinatura);
        return ResponseEntity.ok(assinaturaCriada);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Assinatura> delete(@PathVariable Long id) {
        Assinatura assinatura = assinaturaService.pegarAssinatura(id);
        if (assinatura == null) {
            return ResponseEntity.badRequest().build();
        }
        assinaturaService.cancelarAssinatura(assinatura);
        return ResponseEntity.noContent().build();
    }

}
