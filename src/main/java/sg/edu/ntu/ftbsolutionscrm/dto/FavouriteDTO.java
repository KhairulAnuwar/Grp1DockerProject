package sg.edu.ntu.ftbsolutionscrm.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FavouriteDTO {
    private Long favouriteId;
    private Long userId;
    private String firstName;
    private Long flatId;
    private String town;
    private String flatType;
    private String address;

    // Constructor
    public FavouriteDTO(Long favouriteId, Long userId, Long flatId, String town, String flatType, String address) {
        this.favouriteId = favouriteId;
        this.userId = userId;
        this.flatId = flatId;
        this.town = town;
        this.flatType = flatType;
        this.address = address;
    }

}
