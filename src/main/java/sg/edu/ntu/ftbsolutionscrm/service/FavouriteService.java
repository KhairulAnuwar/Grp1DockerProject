package sg.edu.ntu.ftbsolutionscrm.service;

import java.util.List;

import sg.edu.ntu.ftbsolutionscrm.dto.FavouriteDTO;
import sg.edu.ntu.ftbsolutionscrm.entity.Favourite;

public interface FavouriteService {
    List<Favourite> getAllFavouriteDTOs();
    void addFavourite(Long userId, Long flatId);
    List<FavouriteDTO> getAllFavouritesForUser(Long userId);
    void deleteFavourite(Long favouriteId);
}
