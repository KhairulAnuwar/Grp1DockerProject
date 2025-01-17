package sg.edu.ntu.ftbsolutionscrm.exception;

public class HDBSalesInteractionNotFoundException extends RuntimeException{
    public HDBSalesInteractionNotFoundException(Long id)
    {
        super("Could not find HDB sales interaction with id: " + id + ".");

    }
}
