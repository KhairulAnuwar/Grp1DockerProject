package sg.edu.ntu.ftbsolutionscrm.service;

import java.util.List;

import sg.edu.ntu.ftbsolutionscrm.entity.Salesperson;

public interface SalesPersonService {
    
  Salesperson createSalePerson(Salesperson salesperson);

  Salesperson getSalesPerson(Long id);

  List<Salesperson> getAllSalesPerson();

  Salesperson updateSalesPerson(Long id, Salesperson salesPerson);

  void deleteSalesPerson(Long id);

  List<Salesperson> getSalespersonbyFirstName(String firstName);


}
