package sg.edu.ntu.ftbsolutionscrm.service;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import sg.edu.ntu.ftbsolutionscrm.entity.Login;
import sg.edu.ntu.ftbsolutionscrm.repository.LoginRepository;

@ExtendWith(MockitoExtension.class)
public class AuthenticationServiceTest {

    @Mock
    private LoginRepository loginRepository;

    @InjectMocks
    private AuthenticationService authenticationService;

    @Test
    public void testAuthenticate_Success() {
        // 1. SETUP
        String email = "testuser@example.com";
        String password = "password123";
        Login mockLogin = new Login();
        mockLogin.setEmail(email);
        mockLogin.setPassword(password);

        when(loginRepository.findByEmail(email)).thenReturn(Optional.of(mockLogin));

        // 2. EXECUTE
        boolean isAuthenticated = authenticationService.authenticate(email, password);

        // 3. ASSERT
        assertTrue(isAuthenticated, "The user should be authenticated successfully");
    }

    @Test
    public void testAuthenticate_InvalidPassword() {
        // 1. SETUP
        String email = "testuser@example.com";
        String password = "wrongpassword";
        Login mockLogin = new Login();
        mockLogin.setEmail(email);
        mockLogin.setPassword("password123");

        when(loginRepository.findByEmail(email)).thenReturn(Optional.of(mockLogin));

        // 2. EXECUTE
        boolean isAuthenticated = authenticationService.authenticate(email, password);

        // 3. ASSERT
        assertFalse(isAuthenticated, "The user should not be authenticated with an incorrect password");
    }

    @Test
    public void testAuthenticate_UserNotFound() {
        // 1. SETUP
        String email = "nonexistentuser@example.com";
        String password = "password123";

        when(loginRepository.findByEmail(email)).thenReturn(Optional.empty());

        // 2. EXECUTE
        boolean isAuthenticated = authenticationService.authenticate(email, password);

        // 3. ASSERT
        assertFalse(isAuthenticated, "The user should not be authenticated if they are not found");
    }
}