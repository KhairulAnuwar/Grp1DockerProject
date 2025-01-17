package sg.edu.ntu.ftbsolutionscrm.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import sg.edu.ntu.ftbsolutionscrm.entity.HDBUser;
import sg.edu.ntu.ftbsolutionscrm.repository.HdbUserRepository;
import sg.edu.ntu.ftbsolutionscrm.exception.HDBUserNotFoundException;



@Service
public class HDBUserServiceImpl implements HDBUserService {

  private HdbUserRepository hdbUserRepository;

  public HDBUserServiceImpl(HdbUserRepository hdbUserRepository) {
    this.hdbUserRepository = hdbUserRepository;
  }

  public  HDBUser createHdbUser(HDBUser hdbUser) {
    HDBUser newHdbUser = hdbUserRepository.save(hdbUser);
    return newHdbUser;
  }

  public HDBUser getHdbUser(Long id) {

    return hdbUserRepository.findById(id).orElseThrow(() -> new HDBUserNotFoundException(id));
  }

  public ArrayList<HDBUser> getAllHdbUsers() {
    List<HDBUser> allHdbUsers = hdbUserRepository.findAll();
    return (ArrayList<HDBUser>) allHdbUsers;
  }
  
  public HDBUser updateHdbUser(Long id, HDBUser hdbUser) {

    HDBUser hdbUserToUpdate = hdbUserRepository.findById(id).orElseThrow(() -> new HDBUserNotFoundException(id));
    hdbUserToUpdate.setFirstName(hdbUser.getFirstName());
    hdbUserToUpdate.setLastName(hdbUser.getLastName());
    hdbUserToUpdate.setEmail(hdbUser.getEmail());
    hdbUserToUpdate.setContactNo(hdbUser.getContactNo());
    hdbUserToUpdate.setYear_of_birth(hdbUser.getYear_of_birth());
    hdbUserToUpdate.setClose_to_school(hdbUser.getClose_to_school());
    hdbUserToUpdate.setClose_to_mall(hdbUser.getClose_to_mall());
    hdbUserToUpdate.setClose_to_transportation(hdbUser.getClose_to_transportation());
    hdbUserToUpdate.setClose_to_roadways(hdbUser.getClose_to_roadways());
    return hdbUserRepository.save(hdbUserToUpdate);

  }
  @Override
  public void deleteHdbUser(Long id) {
    hdbUserRepository.deleteById(id);
  }

  @Override
  public List<HDBUser> getHdbUserByFirstNameIgnoreCaseAndLastNameIgnoreCase(String firstName, String lastName) {
    List<HDBUser> foundHdbUsers = hdbUserRepository.findByFirstNameIgnoreCaseAndLastNameIgnoreCase(firstName, lastName);
    return foundHdbUsers;
  }

  @Override
  public HDBUser getHDBUser(Long id) {
    return hdbUserRepository.findById(id).orElseThrow(() -> new HDBUserNotFoundException(id));
  }

  @Override
  public HDBUser updateHDBUser(Long id, HDBUser HDBUser) {
    throw new UnsupportedOperationException("Unimplemented method 'updateHDBUser'");
  }


}
