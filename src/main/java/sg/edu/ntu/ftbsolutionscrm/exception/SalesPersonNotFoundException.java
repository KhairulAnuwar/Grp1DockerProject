package sg.edu.ntu.ftbsolutionscrm.exception;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

//import sg.edu.ntu.ftbsolutionscrm.controller.SalesperonController;

public class SalesPersonNotFoundException extends RuntimeException {

    //private static final Logger logger = LoggerFactory.getLogger(SalesPersonNotFoundException.class);

    public SalesPersonNotFoundException(Long id){

        super("Could not find Salesperson with id: "+ id + ".");
    }
    
}
