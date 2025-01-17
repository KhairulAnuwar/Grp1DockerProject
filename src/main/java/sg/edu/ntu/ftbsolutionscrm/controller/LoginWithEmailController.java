package sg.edu.ntu.ftbsolutionscrm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import sg.edu.ntu.ftbsolutionscrm.service.AuthenticationService;
import sg.edu.ntu.ftbsolutionscrm.service.RegistrationService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/api/auth")
public class LoginWithEmailController {

    private static final Logger logger = LoggerFactory.getLogger(LoginWithEmailController.class);

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private RegistrationService registrationService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> credentials) {
        String email = credentials.get("email");
        String password = credentials.get("password");

        logger.info("Login request received");

        if (email == null || password == null) {
            logger.warn("Missing email or password in login request. Email: {}, Password: {}", email, password == null ? "NOT PROVIDED" : "PROVIDED");
            return ResponseEntity.badRequest().body("Missing email or password");
        }

        logger.debug("Attempting authentication for email: {}", email);

        boolean isAuthenticated = authenticationService.authenticate(email, password);
        if (isAuthenticated) {
            logger.info("User successfully authenticated. Email: {}", email);
            return ResponseEntity.ok("Login successful");
        } else {
            logger.warn("Failed authentication attempt. Email: {}", email);
            return ResponseEntity.status(401).body("Invalid email or password");
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Map<String, String> userDetails) {
        String email = userDetails.get("email");
        String password = userDetails.get("password");

        logger.info("Registration request received");

        if (email == null || password == null) {
            logger.warn("Missing email or password in registration request. Email: {}, Password: {}", email, password == null ? "NOT PROVIDED" : "PROVIDED");
            return ResponseEntity.badRequest().body("Missing email or password");
        }

        logger.debug("Attempting registration for email: {}", email);

        boolean isRegistered = registrationService.register(email, password);
        if (isRegistered) {
            logger.info("User successfully registered. Email: {}", email);
            return ResponseEntity.ok("Registration successful");
        } else {
            logger.warn("Failed registration attempt. Email: {} already exists", email);
            return ResponseEntity.status(400).body("Email already exists");
        }
    }
}