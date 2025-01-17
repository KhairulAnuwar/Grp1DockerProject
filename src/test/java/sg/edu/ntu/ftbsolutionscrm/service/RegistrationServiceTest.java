package sg.edu.ntu.ftbsolutionscrm.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import sg.edu.ntu.ftbsolutionscrm.entity.Login;
import sg.edu.ntu.ftbsolutionscrm.repository.LoginRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class RegistrationServiceTest {

    @Mock
    private LoginRepository loginRepository;

    @InjectMocks
    private RegistrationService registrationService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void register_ShouldReturnTrue_WhenEmailDoesNotExist() {
        // Setup
        String email = "newuser@example.com";
        String password = "securepassword";
        when(loginRepository.findByEmail(email)).thenReturn(Optional.empty());

        // Execute
        boolean result = registrationService.register(email, password);

        // Assert
        assertTrue(result);
        verify(loginRepository, times(1)).findByEmail(email);
        verify(loginRepository, times(1)).save(any(Login.class));
    }

    @Test
    void register_ShouldReturnFalse_WhenEmailAlreadyExists() {
        // Setup
        String email = "existinguser@example.com";
        String password = "securepassword";
        Login existingUser = new Login();
        existingUser.setEmail(email);
        existingUser.setPassword(password);

        when(loginRepository.findByEmail(email)).thenReturn(Optional.of(existingUser));

        // Execute
        boolean result = registrationService.register(email, password);

        // Assert
        assertFalse(result);
        verify(loginRepository, times(1)).findByEmail(email);
        verify(loginRepository, never()).save(any(Login.class));
    }
}