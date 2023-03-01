package microdev.vaadinspringcomercial.repository;

import microdev.vaadinspringcomercial.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserModel, String> {

    Optional<UserModel> findByCorreoAndPassword(String correo, String password);

}
