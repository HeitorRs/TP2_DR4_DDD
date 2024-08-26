package info.heitor.tp2armenio.infra;

import info.heitor.tp2armenio.domain.Assinatura;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AssinaturaRepository extends JpaRepository<Assinatura, Long> {
}
