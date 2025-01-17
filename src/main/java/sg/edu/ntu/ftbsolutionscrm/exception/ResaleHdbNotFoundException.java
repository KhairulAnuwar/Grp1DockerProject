package sg.edu.ntu.ftbsolutionscrm.exception;

public class ResaleHdbNotFoundException extends RuntimeException {
    public ResaleHdbNotFoundException(Long id) {
        super("Could not find Resale HDB with id: " + id + ".");
    }

}
