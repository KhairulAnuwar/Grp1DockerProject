package sg.edu.ntu.ftbsolutionscrm.controller;

import sg.edu.ntu.ftbsolutionscrm.entity.Salesperson;
//import sg.edu.ntu.ftbsolutionscrm.service.SalesPersonService;
import sg.edu.ntu.ftbsolutionscrm.service.SalesPersonService;
import org.springframework.web.bind.annotation.RestController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;

import java.util.List;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/salesperson")
public class SalesperonController 
{

private SalesPersonService salesPersonService;

private static final Logger logger = LoggerFactory.getLogger(SalesperonController.class);


    public SalesperonController(SalesPersonService salesPersonService)
    {
        this.salesPersonService=salesPersonService;
    }

    //Create Salesperson
 @PostMapping("")
    public ResponseEntity<Salesperson> createSalePerson(@Valid @RequestBody Salesperson salesperson) 
    {
     Salesperson newNewSalesPerson = salesPersonService.createSalePerson(salesperson);
     logger.error("Salesperson data ID not found");
     return new ResponseEntity<>(newNewSalesPerson,HttpStatus.CREATED);
    }
//read one salesperson
@GetMapping("/{id}")
  public ResponseEntity<Salesperson> getSalesPerson(@PathVariable Long id) {
    logger.info("Salesperson data record"+id +" is being read.");
    return new ResponseEntity<>(salesPersonService.getSalesPerson(id), HttpStatus.OK);
  }

  //get all Salespersons
  @GetMapping("")
  public ResponseEntity<List<Salesperson>> getAllSalesPerson() {
    logger.info("Retrieving all Salesperson data records");
    return new ResponseEntity<>(salesPersonService.getAllSalesPerson(), HttpStatus.OK);
  }

   // Update
  @PutMapping("/{id}")
  public ResponseEntity<Salesperson> updateSalesPerson(@PathVariable Long id, @RequestBody Salesperson salesperson) {
    Salesperson updatedSalesperson = salesPersonService.updateSalesPerson(id, salesperson);
    logger.info("Updating Salesperson data record:"+id);
    return new ResponseEntity<>(updatedSalesperson, HttpStatus.OK);
  }


  // Delete
  @DeleteMapping("/{id}")
  public ResponseEntity<HttpStatus> deleteSalesPerson(@PathVariable Long id) {
    salesPersonService.deleteSalesPerson(id);
    logger.info(" Salesperson data record:"+id+ "deleted");
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }


  // localhost:8080/customers/search?firstName=Stephen
  @GetMapping("/search")
  public ResponseEntity <List<Salesperson>> searchSalesperson(@RequestParam String firstName) {
    List<Salesperson> foundSalesperson = salesPersonService.getSalespersonbyFirstName(firstName);
    logger.info(" Searched Salesperson data record by FirstName="+firstName);
    return new ResponseEntity<>(foundSalesperson, HttpStatus.OK);
  }





}
