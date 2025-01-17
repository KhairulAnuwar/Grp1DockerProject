package sg.edu.ntu.ftbsolutionscrm.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import sg.edu.ntu.ftbsolutionscrm.dto.FavouriteDTO;
import sg.edu.ntu.ftbsolutionscrm.entity.Favourite;
import sg.edu.ntu.ftbsolutionscrm.entity.HDBUser;
import sg.edu.ntu.ftbsolutionscrm.entity.ResaleHdb;
import sg.edu.ntu.ftbsolutionscrm.repository.FavouriteRepository;
import sg.edu.ntu.ftbsolutionscrm.repository.HdbUserRepository;
import sg.edu.ntu.ftbsolutionscrm.repository.ResaleHDBRepository;

@ExtendWith(MockitoExtension.class)
public class FavouriteServiceImplTest {

    @Mock
    private FavouriteRepository favouriteRepository;

    @Mock
    private HdbUserRepository hdbUserRepository;

    @Mock
    private ResaleHDBRepository resaleHDBRepository;

    @InjectMocks
    private FavouriteServiceImpl favouriteService;

      @Test
    public void testGetAllFavouriteDTOs() {
        Favourite favourite = new Favourite();
        when(favouriteRepository.findAll()).thenReturn(List.of(favourite));

        List<Favourite> result = favouriteService.getAllFavouriteDTOs();

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(favouriteRepository, times(1)).findAll();
    }

     @Test
    public void testAddFavourite() {
        Long userId = 1L;
        Long flatId = 1L;

        HDBUser user = new HDBUser();
        ResaleHdb flat = new ResaleHdb();

        when(hdbUserRepository.findById(userId)).thenReturn(Optional.of(user));
        when(resaleHDBRepository.findById(flatId)).thenReturn(Optional.of(flat));

        favouriteService.addFavourite(userId, flatId);

        verify(favouriteRepository, times(1)).save(any(Favourite.class));
    }

    @Test
   public void testAddFavouriteUserOrFlatNotFound() {
        Long userId = 1L;
        Long flatId = 1L;

        when(hdbUserRepository.findById(userId)).thenReturn(Optional.empty());
        when(resaleHDBRepository.findById(flatId)).thenReturn(Optional.of(new ResaleHdb()));

        Exception exception = assertThrows(RuntimeException.class, () -> favouriteService.addFavourite(userId, flatId));
        assertEquals("User or Flat not found", exception.getMessage());

        verify(favouriteRepository, never()).save(any(Favourite.class));
    }

        @Test
    public void testGetAllFavouritesForUser() {
        Long userId = 1L;

        Favourite favourite = new Favourite();
        favourite.setId(1L);
        HDBUser user = new HDBUser();
        user.setId(userId);
        favourite.setUser(user);

        ResaleHdb flat = new ResaleHdb();
        flat.setId(2L);
        flat.setTown("Test Town");
        flat.setFlatType("3-room");
        flat.setStreetName("Test Street");
        favourite.setFlat(flat);

        when(favouriteRepository.findByUserId(userId)).thenReturn(List.of(favourite));

        List<FavouriteDTO> result = favouriteService.getAllFavouritesForUser(userId);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Test Town", result.get(0).getTown());
        verify(favouriteRepository, times(1)).findByUserId(userId);
    }

    @Test
   public void testDeleteFavourite() {
        Long favouriteId = 1L;

        Favourite favourite = new Favourite();
        when(favouriteRepository.findById(favouriteId)).thenReturn(Optional.of(favourite));

        favouriteService.deleteFavourite(favouriteId);

        verify(favouriteRepository, times(1)).delete(favourite);
    }

    @Test
    public void testDeleteFavouriteNotFound() {
        Long favouriteId = 1L;

        when(favouriteRepository.findById(favouriteId)).thenReturn(Optional.empty());

        Exception exception = assertThrows(IllegalArgumentException.class, () -> favouriteService.deleteFavourite(favouriteId));
        assertEquals("Favourite not found with ID: " + favouriteId, exception.getMessage());

        verify(favouriteRepository, never()).delete(any(Favourite.class));
    }
}


