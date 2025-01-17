package sg.edu.ntu.ftbsolutionscrm.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.edu.ntu.ftbsolutionscrm.entity.Login;
import sg.edu.ntu.ftbsolutionscrm.repository.LoginRepository;

@Service
public class RegistrationService {

    @Autowired
    private LoginRepository loginRepository;

    public boolean register(String email, String password) {
        Optional<Login> existingUser = loginRepository.findByEmail(email);
        if (existingUser.isPresent()) {
            return false;
        }

        Login newUser = new Login();
        newUser.setEmail(email);
        newUser.setPassword(password);
        loginRepository.save(newUser);
        return true;
    }
}
