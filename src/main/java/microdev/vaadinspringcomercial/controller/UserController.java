package microdev.vaadinspringcomercial.controller;

import microdev.vaadinspringcomercial.model.UserModel;
import microdev.vaadinspringcomercial.repository.ProdTemRepository;
import microdev.vaadinspringcomercial.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserController {

    private final UserRepository userRepository;
    //DELETE
    public final ProdTemRepository prodTemRepository;

    public UserController(UserRepository userRepository, ProdTemRepository prodTemRepository){
        this.userRepository = userRepository;
        this.prodTemRepository = prodTemRepository;
    }

    public Optional<UserModel> loginUser(String email, String password){
        return userRepository.findByCorreoAndPassword(email, password);
    }

}
