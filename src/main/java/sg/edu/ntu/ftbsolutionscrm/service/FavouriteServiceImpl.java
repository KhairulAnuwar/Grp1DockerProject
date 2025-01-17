package sg.edu.ntu.ftbsolutionscrm.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.edu.ntu.ftbsolutionscrm.dto.FavouriteDTO;
import sg.edu.ntu.ftbsolutionscrm.entity.Favourite;
import sg.edu.ntu.ftbsolutionscrm.entity.HDBUser;
import sg.edu.ntu.ftbsolutionscrm.entity.ResaleHdb;
import sg.edu.ntu.ftbsolutionscrm.repository.FavouriteRepository;
import sg.edu.ntu.ftbsolutionscrm.repository.HdbUserRepository;
import sg.edu.ntu.ftbsolutionscrm.repository.ResaleHDBRepository;

@Service
public class FavouriteServiceImpl {

    @Autowired
    private ResaleHDBRepository resaleHDBRepository;

    @Autowired
    private HdbUserRepository hdbUserRepository;

    @Autowired
    private FavouriteRepository favouriteRepository;

    public List<Favourite> getAllFavouriteDTOs() {
        return favouriteRepository.findAll();
    }

    public void addFavourite(Long userId, Long flatId) {
        Optional<HDBUser> userOpt = hdbUserRepository.findById(userId);
        Optional<ResaleHdb> flatOpt = resaleHDBRepository.findById(flatId);
        if (userOpt.isPresent() && flatOpt.isPresent()) {
            Favourite favourite = new Favourite();
            favourite.setUser(userOpt.get());
            favourite.setFlat(flatOpt.get());
            favouriteRepository.save(favourite);
        } else {
            throw new RuntimeException("User or Flat not found");

        }
    }

    public List<FavouriteDTO> getAllFavouritesForUser(Long userId) {
        // Fetch all favourites for the user
        List<Favourite> favourites = favouriteRepository.findByUserId(userId);

        // Map to DTO
        return favourites.stream()
                .map(fav -> new FavouriteDTO(
                        fav.getId(),
                        fav.getUser().getId(),
                        fav.getFlat().getId(),
                        fav.getFlat().getTown(),
                        fav.getFlat().getFlatType(),
                        fav.getFlat().getStreetName()))
                .toList();
    }

    public void deleteFavourite(Long favouriteId) {
        // Check if the favourite exists
        Favourite favourite = favouriteRepository.findById(favouriteId)
                .orElseThrow(() -> new IllegalArgumentException("Favourite not found with ID: " + favouriteId));

        // Delete the favourite
        favouriteRepository.delete(favourite);
    }
}
