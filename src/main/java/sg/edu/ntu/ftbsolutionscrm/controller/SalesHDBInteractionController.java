package sg.edu.ntu.ftbsolutionscrm.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import sg.edu.ntu.ftbsolutionscrm.service.SalesHDBInteractionService;
import sg.edu.ntu.ftbsolutionscrm.DataTransferObject.SalesHDBInteractionDTO;
import sg.edu.ntu.ftbsolutionscrm.entity.SalesHDBInteraction;
import sg.edu.ntu.ftbsolutionscrm.repository.SalesHDBInteractionRepository;
import sg.edu.ntu.ftbsolutionscrm.repository.SalespersonRepository;
import sg.edu.ntu.ftbsolutionscrm.service.SalesHDBInteractionService;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/hdbsalesinteractions")
public class SalesHDBInteractionController 
{
    private  SalesHDBInteractionService salesHDBInteractionService;
    private static final Logger logger = LoggerFactory.getLogger(SalesHDBInteractionController.class);


    public SalesHDBInteractionController(SalesHDBInteractionService salesHDBInteractionService )
    {
        this.salesHDBInteractionService = salesHDBInteractionService;
    }
    //create
    @PostMapping("")                           
    public ResponseEntity<SalesHDBInteractionDTO> saveSalesHDBInteraction(@Valid @RequestBody SalesHDBInteractionDTO salesHDBInteractionDTO) 
    {
        SalesHDBInteractionDTO newSalesHDBInteraction = salesHDBInteractionService.saveSalesHDBInteraction(salesHDBInteractionDTO);
        logger.info("New Sale HDB Interaction is made ");
        return new ResponseEntity<>(newSalesHDBInteraction, HttpStatus.CREATED);

    }
    
    //get all
    @GetMapping("")
    public ResponseEntity<List<SalesHDBInteraction>> getAllSalesHDBInteractions()
    {
    logger.info("Retrieving All Sales HDB Transaction data records");
     return new ResponseEntity<>(salesHDBInteractionService.getAllSalesHDBInteractions(), HttpStatus.OK);
    }



    //read one particular 
    @GetMapping("/{id}")
    public ResponseEntity<SalesHDBInteractionDTO> getSalesHDBInteraction(@PathVariable Long id) { 
        logger.info("Retrieving  Sales HDB Transaction data records with id:"+ id);

        return new ResponseEntity<>(salesHDBInteractionService.getSalesHDBInteraction(id), HttpStatus.OK);

    }
    

    @PutMapping("/{id}")
    public ResponseEntity<SalesHDBInteraction> updateSalesHDBInteraction(
            @PathVariable Long id,
            @Valid @RequestBody SalesHDBInteractionDTO salesHDBInteractionDTO) {

        SalesHDBInteraction updatedInteraction = salesHDBInteractionService.updateSalesHDBInteraction(id, salesHDBInteractionDTO);
        
        logger.info("Updating Sales HDB Transaction data records with id:"+ id);

        return ResponseEntity.ok(updatedInteraction);
    }





    // Delete
  @DeleteMapping("/{id}")
  public ResponseEntity<HttpStatus> deleteSalesHDBInteraction(@PathVariable Long id) {
    salesHDBInteractionService.deleteSalesHDBInteraction(id);
    logger.info("Deleting Sales HDB Transaction data records with id:"+ id);

    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }



}
