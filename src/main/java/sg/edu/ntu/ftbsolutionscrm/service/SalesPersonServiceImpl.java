package sg.edu.ntu.ftbsolutionscrm.service;

import java.util.List;

import org.springframework.stereotype.Service;

import sg.edu.ntu.ftbsolutionscrm.entity.Salesperson;
import sg.edu.ntu.ftbsolutionscrm.exception.SalesPersonNotFoundException;
import sg.edu.ntu.ftbsolutionscrm.repository.SalespersonRepository;


@Service
public class SalesPersonServiceImpl implements SalesPersonService {

    private SalespersonRepository salespersonRepository;

    public SalesPersonServiceImpl(SalespersonRepository salespersonRepository)
    {
        this.salespersonRepository = salespersonRepository;
    }



    @Override
    public Salesperson createSalePerson(Salesperson salesPerson) {
        return salespersonRepository.save(salesPerson);

    }


 
    @Override
    public Salesperson getSalesPerson(Long id) {
        return salespersonRepository.findById(id).orElseThrow(() -> new SalesPersonNotFoundException(id));

    }

    @Override
    public List<Salesperson> getAllSalesPerson() {
        return salespersonRepository.findAll();

    }


   
    //Updates Salesperson
    @Override
    public Salesperson updateSalesPerson(Long id, Salesperson salesPerson) {
        Salesperson salespersonToUpdate = salespersonRepository.findById(id).orElseThrow(() -> new SalesPersonNotFoundException(id));
        // Update the customer object that was retrieved
        salespersonToUpdate.setFirstName(salesPerson.getFirstName());
        salespersonToUpdate.setLastName(salesPerson.getLastName());
        salespersonToUpdate.setEmail(salesPerson.getEmail());
        salespersonToUpdate.setContactNo(salesPerson.getContactNo());
        salespersonToUpdate.setYearsInService(salesPerson.getYearsInService());
        salespersonToUpdate.setAwards(salesPerson.getAwards());
        // Save updated salesperson back to db
        return salespersonRepository.save(salespersonToUpdate);
    }


 
   

    @Override
    public void deleteSalesPerson(Long id) {
        salespersonRepository.deleteById(id);
    }



    @Override
    public List<Salesperson> getSalespersonbyFirstName(String firstName) {
        List<Salesperson> foundSalesperson = salespersonRepository.findByFirstNameIgnoreCase(firstName);
        return foundSalesperson;
    
    }



    
    

}
