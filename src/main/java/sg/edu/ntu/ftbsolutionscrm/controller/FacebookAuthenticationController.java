package sg.edu.ntu.ftbsolutionscrm.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import sg.edu.ntu.ftbsolutionscrm.service.FacebookAuthenticationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/api/auth")
public class FacebookAuthenticationController {

    private static final Logger logger = LoggerFactory.getLogger(FacebookAuthenticationController.class);

    @Autowired
    private FacebookAuthenticationService facebookAuthenticationService;

    @PostMapping("/facebook-login")
    public ResponseEntity<?> authenticateWithFacebook(@RequestBody Map<String, String> requestBody) {
        logger.info("Received Facebook login request");

        String facebookUid = requestBody.get("facebookUid");

        if (facebookUid == null || facebookUid.isEmpty()) {
            logger.warn("Facebook UID is missing or empty in request body");
            return ResponseEntity.badRequest().body("Missing or invalid Facebook UID");
        }

        logger.debug("Attempting to authenticate Facebook UID: {}", facebookUid);

        boolean isAuthenticated = facebookAuthenticationService.authenticateWithFacebook(facebookUid);

        if (isAuthenticated) {
            logger.info("Facebook authentication successful for UID: {}", facebookUid);
            return ResponseEntity.ok("Facebook login successful");
        } else {
            logger.warn("Facebook authentication failed for UID: {}", facebookUid);
            return ResponseEntity.status(401).body("Facebook authentication failed");
        }
    }
}