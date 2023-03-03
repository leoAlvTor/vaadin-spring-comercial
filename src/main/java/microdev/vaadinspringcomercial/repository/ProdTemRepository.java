package microdev.vaadinspringcomercial.repository;

import microdev.vaadinspringcomercial.model.ProdTem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdTemRepository extends JpaRepository<ProdTem, String> {
}