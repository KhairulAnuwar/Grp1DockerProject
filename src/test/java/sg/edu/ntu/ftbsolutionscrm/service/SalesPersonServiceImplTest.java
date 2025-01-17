package sg.edu.ntu.ftbsolutionscrm.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import sg.edu.ntu.ftbsolutionscrm.entity.Salesperson;
import sg.edu.ntu.ftbsolutionscrm.exception.SalesPersonNotFoundException;
import sg.edu.ntu.ftbsolutionscrm.repository.SalespersonRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class SalesPersonServiceImplTest {
    
    @Mock
    private SalespersonRepository salespersonRepository;

    @InjectMocks
    private SalesPersonServiceImpl salesPersonService;

    private Salesperson salesperson;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        salesperson = new Salesperson();
        salesperson.setId(1L);
        salesperson.setFirstName("John");
        salesperson.setLastName("Doe");
        salesperson.setEmail("john.doe@example.com");
        salesperson.setContactNo("12345678");
        salesperson.setYearsInService(5);
        salesperson.setAwards("Top Seller");
    }

    @Test
    void createSalePerson_shouldReturnSavedSalesperson() {
        when(salespersonRepository.save(any(Salesperson.class))).thenReturn(salesperson);

        Salesperson savedSalesperson = salesPersonService.createSalePerson(salesperson);

        assertNotNull(savedSalesperson);
        assertEquals("John", savedSalesperson.getFirstName());
        verify(salespersonRepository, times(1)).save(salesperson);
    }

    @Test
    void getSalesPerson_shouldReturnSalespersonWhenFound() {
        when(salespersonRepository.findById(1L)).thenReturn(Optional.of(salesperson));

        Salesperson foundSalesperson = salesPersonService.getSalesPerson(1L);

        assertNotNull(foundSalesperson);
        assertEquals(1L, foundSalesperson.getId());
        verify(salespersonRepository, times(1)).findById(1L);
    }

    @Test
    void getSalesPerson_shouldThrowExceptionWhenNotFound() {
        when(salespersonRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(SalesPersonNotFoundException.class, () -> salesPersonService.getSalesPerson(1L));
        verify(salespersonRepository, times(1)).findById(1L);
    }

}
