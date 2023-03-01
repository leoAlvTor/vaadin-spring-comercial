package microdev.vaadinspringcomercial.controller;

import microdev.vaadinspringcomercial.model.UserModel;
import microdev.vaadinspringcomercial.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public Optional<UserModel> loginUser(String email, String password){
        return userRepository.findByCorreoAndPassword(email, password);
    }

}
