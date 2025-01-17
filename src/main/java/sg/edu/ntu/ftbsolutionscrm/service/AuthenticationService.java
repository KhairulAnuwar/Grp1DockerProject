package sg.edu.ntu.ftbsolutionscrm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.edu.ntu.ftbsolutionscrm.repository.LoginRepository;

@Service
public class AuthenticationService {

    @Autowired
    private LoginRepository loginRepository;

    public boolean authenticate(String email, String password) {
        return loginRepository.findByEmail(email).map(user -> user.getPassword().equals(password)).orElse(false);
    }
    
}