package sg.edu.ntu.ftbsolutionscrm.exception;

public class HDBUserNotFoundException extends RuntimeException {
    public HDBUserNotFoundException(Long id) {
      super("Could not find HDB User with id: " + id + ".");
    }
  
  }

