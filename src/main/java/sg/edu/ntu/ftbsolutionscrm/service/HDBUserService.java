package sg.edu.ntu.ftbsolutionscrm.service;

import java.util.ArrayList;
import java.util.List;

import sg.edu.ntu.ftbsolutionscrm.entity.HDBUser;

public interface HDBUserService {
  HDBUser createHdbUser(HDBUser hdbUser);

  HDBUser getHDBUser(Long id);

  ArrayList<HDBUser> getAllHdbUsers();

  HDBUser updateHDBUser(Long id, HDBUser HDBUser);

  void deleteHdbUser(Long id);

  List<HDBUser> getHdbUserByFirstNameIgnoreCaseAndLastNameIgnoreCase(String firstName, String lastName);

}
