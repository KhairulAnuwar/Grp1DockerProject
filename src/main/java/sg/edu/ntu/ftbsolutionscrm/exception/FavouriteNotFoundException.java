package sg.edu.ntu.ftbsolutionscrm.exception;

public class FavouriteNotFoundException extends RuntimeException {
    public FavouriteNotFoundException(Long id){
        super("Could not find Favourites with id: "+ id + ".");
    }
    
}
