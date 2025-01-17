package sg.edu.ntu.ftbsolutionscrm.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import sg.edu.ntu.ftbsolutionscrm.entity.HDBUser;
import sg.edu.ntu.ftbsolutionscrm.service.HDBUserServiceImpl;

@ExtendWith(MockitoExtension.class)
public class HDBUserControllerTest {

    @InjectMocks
    private HDBUserController hdbUserController;

    @Mock
    private HDBUserServiceImpl hdbUserService;

    @Mock
    private BindingResult bindingResult;

    @Test
    public void testCreateHdbUser_validUser_returnsCreated() {
        HDBUser newHdbUser = new HDBUser();

        when(hdbUserService.createHdbUser(any(HDBUser.class))).thenReturn(newHdbUser);
        when(bindingResult.hasErrors()).thenReturn(false);

        ResponseEntity<HDBUser> responseEntity = hdbUserController.createHdbUser(newHdbUser, bindingResult);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(newHdbUser, responseEntity.getBody());
    }
}
