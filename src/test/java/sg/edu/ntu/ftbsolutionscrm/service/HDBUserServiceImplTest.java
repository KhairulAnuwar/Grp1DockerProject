package sg.edu.ntu.ftbsolutionscrm.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import sg.edu.ntu.ftbsolutionscrm.entity.HDBUser;
import sg.edu.ntu.ftbsolutionscrm.repository.HdbUserRepository;

@ExtendWith(MockitoExtension.class)
public class HDBUserServiceImplTest {

    @InjectMocks
    private HDBUserServiceImpl hdbUserService;

    @Mock
    private HdbUserRepository hdbUserRepository;

    @Test
    public void testCreateHdbUser() {
        // Arrange (setup mocks)
        HDBUser hdbUser = new HDBUser();
        // Set other fields of hdbUser as needed

        HDBUser savedHdbUser = new HDBUser();
        savedHdbUser.setId(1L);
        // Set other fields of savedHdbUser as needed

        when(hdbUserRepository.save(hdbUser)).thenReturn(savedHdbUser);

        // Act (call the method)
        HDBUser createdHdbUser = hdbUserService.createHdbUser(hdbUser);

        // Assert (verify behavior and results)
        assertEquals(savedHdbUser, createdHdbUser);
        verify(hdbUserRepository).save(hdbUser);
    }
}