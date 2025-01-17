package sg.edu.ntu.ftbsolutionscrm.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import sg.edu.ntu.ftbsolutionscrm.entity.ResaleHdb;
import sg.edu.ntu.ftbsolutionscrm.exception.ResaleHdbNotFoundException;
import sg.edu.ntu.ftbsolutionscrm.repository.ResaleHDBRepository;

@ExtendWith(MockitoExtension.class)
public class ResaleHDBServiceImplTest {

    @Mock
    private ResaleHDBRepository resaleHDBRepository;

    @InjectMocks
    ResaleHDBServiceImpl resaleHDBServiceImpl;

    @Test
    public void testCreateResaleHDB() {
        // 1. SETUP
        // Create an HDB
        ResaleHdb Hdb1 = new ResaleHdb("2024-01", "Yishun", "3 ROOM", "123", "Yishun Ave 1", "01-10", 70.0,
                "Model A", 1995,
                "Remaining 60 years", 300000);

        // Mock the save method
        when(resaleHDBRepository.save(Hdb1)).thenReturn(Hdb1);

        // 2. EXECUTE
        // Call the method that we want to test
        ResaleHdb savedHdb = resaleHDBServiceImpl.createResaleHDB(Hdb1);

        // 3. ASSERT
        assertEquals(Hdb1, savedHdb, "The saved HDB should be the same as the new HDB");

        // verify that the save method was called exactly one time
        verify(resaleHDBRepository, times(1)).save(Hdb1);
    }

    @Test
    public void testgetResaleHDBById() {
        // 1. SETUP
        ResaleHdb Hdb1 = new ResaleHdb("2024-01", "Yishun", "3 ROOM", "123", "Yishun Ave 1", "01-10", 70.0,
                "Model A", 1995,
                "Remaining 60 years", 300000);
        Long hdbId = 1L;

        // Mock the save method
        when(resaleHDBRepository.findById(hdbId)).thenReturn(Optional.of(Hdb1));

        // 2. EXECUTE
        ResaleHdb retrievedHdb = resaleHDBServiceImpl.getResaleHDBById(hdbId);

        // 3. ASSERT
        assertEquals(retrievedHdb, Hdb1);

    }

    @Test
    public void testGetHdbNotFound() {

        Long HdbId = 1L;
        when(resaleHDBRepository.findById(HdbId)).thenReturn(Optional.empty());

        assertThrows(ResaleHdbNotFoundException.class, () -> resaleHDBServiceImpl.getResaleHDBById(HdbId));
    }

}
