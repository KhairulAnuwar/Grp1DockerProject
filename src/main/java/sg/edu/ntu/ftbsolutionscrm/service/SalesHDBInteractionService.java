package sg.edu.ntu.ftbsolutionscrm.service;
import java.util.List;

import sg.edu.ntu.ftbsolutionscrm.DataTransferObject.SalesHDBInteractionDTO;
import sg.edu.ntu.ftbsolutionscrm.entity.SalesHDBInteraction;


public interface SalesHDBInteractionService 
{
    // SalesHDBInteraction saveSalesHDBInteraction(SalesHDBInteraction salesHDBInteraction);
    // SalesHDBInteraction getSalesHDBInteraction(Long id);
    // List<SalesHDBInteraction> getAllSalesHDBInteractions();
    // SalesHDBInteraction updateSalesHDBInteraction(Long id, SalesHDBInteraction salesHDBInteraction);
    // void deleteSalesHDBInteraction(Long id);


    //change to use DTO

    //save
    SalesHDBInteractionDTO saveSalesHDBInteraction(SalesHDBInteractionDTO salesHDBInteractionDTO);

    //retrieve a particular SalesHDBInteraction by its ID
    SalesHDBInteractionDTO getSalesHDBInteraction(Long id);

     // get all SalesHDBInteractions
     List<SalesHDBInteraction> getAllSalesHDBInteractions();

     // update an existing SalesHDBInteraction
     SalesHDBInteraction updateSalesHDBInteraction(Long id, SalesHDBInteractionDTO salesHDBInteractionDTO);
 
     // delete a SalesHDBInteraction by its ID
     void deleteSalesHDBInteraction(Long id);
 }


