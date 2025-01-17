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

import sg.edu.ntu.ftbsolutionscrm.entity.FacebookLogin;
import sg.edu.ntu.ftbsolutionscrm.repository.FacebookLoginRepository;

@ExtendWith(MockitoExtension.class)
public class FacebookAuthenticationServiceTest {

    @Mock
    private FacebookLoginRepository facebookLoginRepository;

    @InjectMocks
    private FacebookAuthenticationService facebookAuthenticationService;

    @Test
    public void testAuthenticateWithFacebook_Success() {
        // 1. SETUP
        String facebookUid = "valid-facebook-uid";
        FacebookLogin mockFacebookLogin = new FacebookLogin();
        mockFacebookLogin.setFacebookUid(facebookUid);

        when(facebookLoginRepository.findByFacebookUid(facebookUid)).thenReturn(Optional.of(mockFacebookLogin));

        // 2. EXECUTE
        boolean isAuthenticated = facebookAuthenticationService.authenticateWithFacebook(facebookUid);

        // 3. ASSERT
        assertTrue(isAuthenticated, "The user should be authenticated successfully with a valid Facebook UID");
    }

    @Test
    public void testAuthenticateWithFacebook_InvalidUid() {
        // 1. SETUP
        String facebookUid = "invalid-facebook-uid";

        when(facebookLoginRepository.findByFacebookUid(facebookUid)).thenReturn(Optional.empty());

        // 2. EXECUTE
        boolean isAuthenticated = facebookAuthenticationService.authenticateWithFacebook(facebookUid);

        // 3. ASSERT
        assertFalse(isAuthenticated, "The user should not be authenticated with an invalid Facebook UID");
    }

    @Test
    public void testAuthenticateWithFacebook_NullUid() {
        // 1. SETUP
        String facebookUid = null;

        when(facebookLoginRepository.findByFacebookUid(facebookUid)).thenReturn(Optional.empty());

        // 2. EXECUTE
        boolean isAuthenticated = facebookAuthenticationService.authenticateWithFacebook(facebookUid);

        // 3. ASSERT
        assertFalse(isAuthenticated, "The user should not be authenticated when the Facebook UID is null");
    }
}