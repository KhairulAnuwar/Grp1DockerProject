package sg.edu.ntu.ftbsolutionscrm.service;

import java.util.List;

import org.springframework.stereotype.Service;

//import entity
import sg.edu.ntu.ftbsolutionscrm.entity.HDBUser;
import sg.edu.ntu.ftbsolutionscrm.entity.SalesHDBInteraction;
import sg.edu.ntu.ftbsolutionscrm.entity.Salesperson;

import sg.edu.ntu.ftbsolutionscrm.exception.HDBSalesInteractionNotFoundException;
import sg.edu.ntu.ftbsolutionscrm.exception.HDBUserNotFoundException;
import sg.edu.ntu.ftbsolutionscrm.exception.SalesPersonNotFoundException;
//import repository
import sg.edu.ntu.ftbsolutionscrm.repository.SalesHDBInteractionRepository;
import sg.edu.ntu.ftbsolutionscrm.repository.SalespersonRepository;
import sg.edu.ntu.ftbsolutionscrm.repository.HdbUserRepository;

//import DTO
import sg.edu.ntu.ftbsolutionscrm.DataTransferObject.SalesHDBInteractionDTO;

@Service
public class SalesHDBInteractionServiceImpl implements SalesHDBInteractionService {

    private SalesHDBInteractionRepository salesHDBInteractionRepository;
    private SalespersonRepository salespersonRepository;
    private HdbUserRepository hdbUserRepository;

    public SalesHDBInteractionServiceImpl(SalesHDBInteractionRepository salesHDBInteractionRepository,
            SalespersonRepository salespersonRepository,
            HdbUserRepository hdbUserRepository) {
        this.salesHDBInteractionRepository = salesHDBInteractionRepository;
        this.salespersonRepository = salespersonRepository;
        this.hdbUserRepository = hdbUserRepository;

    }

    @Override
    public SalesHDBInteractionDTO saveSalesHDBInteraction(SalesHDBInteractionDTO salesHDBInteractionDTO) {
        SalesHDBInteraction salesHDBInteraction = new SalesHDBInteraction();

        // Fetch the Salesperson and HDBUser entities using the IDs provided in the DTO
        Salesperson salesperson = salespersonRepository.findById(salesHDBInteractionDTO.getSalesperson())
                .orElseThrow(() -> new SalesPersonNotFoundException(salesHDBInteractionDTO.getSalesperson()));
        HDBUser hdbUser = hdbUserRepository.findById(salesHDBInteractionDTO.getHdbUserId())
                .orElseThrow(() -> new HDBUserNotFoundException(salesHDBInteractionDTO.getHdbUserId()));

        // Set the values from DTO to the SalesHDBInteraction entity
        salesHDBInteraction.setSalesperson(salesperson);
        salesHDBInteraction.setHdbUser(hdbUser);
        salesHDBInteraction.setReview(salesHDBInteractionDTO.getReview());

        SalesHDBInteraction savedInteraction = salesHDBInteractionRepository.save(salesHDBInteraction);

        // Convert the saved entity back to DTO and return it
        return new SalesHDBInteractionDTO(
                savedInteraction.getSalesperson().getId(), // Extract ID from Salesperson
                savedInteraction.getHdbUser().getId(), // Extract ID from HDBUser
                savedInteraction.getReview());

        // Save the entity to the repository
        // return salesHDBInteractionRepository.save(salesHDBInteraction);
    }

    @Override
    public SalesHDBInteractionDTO getSalesHDBInteraction(Long id) {
        SalesHDBInteraction salesHDBInteraction = salesHDBInteractionRepository.findById(id)
                .orElseThrow(() -> new HDBSalesInteractionNotFoundException(id));

        return new SalesHDBInteractionDTO(
                salesHDBInteraction.getSalesperson().getId(), // Assuming Salesperson entity has an ID field
                salesHDBInteraction.getHdbUser().getId(), // Assuming HDBUser entity has an ID field
                salesHDBInteraction.getReview());
    }

    @Override
    public List<SalesHDBInteraction> getAllSalesHDBInteractions() {
        return salesHDBInteractionRepository.findAll();
    }

//     @Override
//     public SalesHDBInteraction updateSalesHDBInteraction(Long id, SalesHDBInteractionDTO dto) {
//         // Fetch the existing interaction
//         SalesHDBInteraction interactionToUpdate = salesHDBInteractionRepository.findById(id)
//                 .orElseThrow(() -> new HDBSalesInteractionNotFoundException(id));

//         // Fetch related entities by their IDs
//         Salesperson salesperson = salespersonRepository.findById(dto.getSalespersonId())
//                 .orElseThrow(() -> new SalespersonNotFoundException(dto.getSalespersonId()));

//         HDBUser hdbUser = hdbUserRepository.findById(dto.getHdbUserId())
//                 .orElseThrow(() -> new HDBUserNotFoundException(dto.getHdbUserId()));

//         // Update fields
//         interactionToUpdate.setSalesperson(salesperson);
//         interactionToUpdate.setHdbUser(hdbUser);
//         interactionToUpdate.setReview(dto.getReview());

//         // Save updated entity
//         return salesHDBInteractionRepository.save(interactionToUpdate);
//     }

    /*
     * 
     * 
     * public SalesHDBInteraction updateSalesHDBInteraction(Long id,
     * SalesHDBInteraction salesHDBInteraction)
     * {
     * SalesHDBInteraction HDBSaleInteractionToUpdate =
     * salesHDBInteractionRepository.findById(id).orElseThrow(() -> new
     * HDBSalesInteractionNotFoundException(id));
     * HDBSaleInteractionToUpdate.setSalesperson(salesHDBInteraction.getSalesperson(
     * ));
     * HDBSaleInteractionToUpdate.setHdbUser(salesHDBInteraction.getHdbUser());
     * 
     * HDBSaleInteractionToUpdate.setReview(salesHDBInteraction.getReview());
     * 
     * return salesHDBInteractionRepository.save(HDBSaleInteractionToUpdate);
     * 
     * 
     * 
     * }
     */

    @Override
    public void deleteSalesHDBInteraction(Long id) {
        salesHDBInteractionRepository.deleteById(id);
    }

@Override
public SalesHDBInteraction updateSalesHDBInteraction(Long id, SalesHDBInteractionDTO salesHDBInteractionDTO) 
{
      // Fetch the existing interaction
        SalesHDBInteraction interactionToUpdate = salesHDBInteractionRepository.findById(id)
                .orElseThrow(() -> new HDBSalesInteractionNotFoundException(id));

        // Fetch related entities by their IDs
        Salesperson salesperson = salespersonRepository.findById(salesHDBInteractionDTO.getSalesperson())
                .orElseThrow(() -> new SalesPersonNotFoundException(salesHDBInteractionDTO.getSalesperson()));

        HDBUser hdbUser = hdbUserRepository.findById(salesHDBInteractionDTO.getHdbUserId())
                .orElseThrow(() -> new HDBUserNotFoundException(salesHDBInteractionDTO.getHdbUserId()));

        // Update fields
        interactionToUpdate.setSalesperson(salesperson);
        interactionToUpdate.setHdbUser(hdbUser);
        interactionToUpdate.setReview(salesHDBInteractionDTO.getReview());

        // Save updated entity
        return salesHDBInteractionRepository.save(interactionToUpdate);
}

    // old methods currently using DTO
    // @Override
    // public SalesHDBInteraction saveSalesHDBInteraction(SalesHDBInteractionDTO
    // salesHDBInteractionDTO)
    // {
    // //SalesHDBInteraction
    // newSalesHDBInteraction=salesHDBInteractionRepository.save(salesHDBInteraction);
    // // return newSalesHDBInteraction;
    // SalesHDBInteraction salesHDBInteraction = new SalesHDBInteraction();

    // // Fetch the Salesperson and HDBUser entities using the IDs provided in the
    // DTO
    // Salesperson salesperson =
    // salespersonRepository.findById(salesHDBInteractionDTO.getSalesperson())
    // .orElseThrow(() -> new RuntimeException("Salesperson not found"));

    // }

    // @Override
    // public SalesHDBInteraction getSalesHDBInteraction(Long id) {
    // return salesHDBInteractionRepository.findById(id).orElseThrow(() -> new
    // HDBSalesInteractionNotFoundException(id));

    // }

    // @Override
    // public List<SalesHDBInteraction> getAllSalesHDBInteractions() {
    // return salesHDBInteractionRepository.findAll();
    // }

    // @Override
    // public SalesHDBInteraction updateSalesHDBInteraction(Long id,
    // SalesHDBInteraction salesHDBInteraction)
    // {
    // SalesHDBInteraction HDBSaleInteractionToUpdate =
    // salesHDBInteractionRepository.findById(id).orElseThrow(() -> new
    // HDBSalesInteractionNotFoundException(id));
    // //HDBSaleInteractionToUpdate.setSalespersonID(salesHDBInteraction.getSalespersonID());
    // //
    // HDBSaleInteractionToUpdate.setHdbUserId(salesHDBInteraction.getHdbUserId());

    // HDBSaleInteractionToUpdate.setReview(salesHDBInteraction.getReview());

    // return salesHDBInteractionRepository.save(HDBSaleInteractionToUpdate);

    // }

    // @Override
    // public void deleteSalesHDBInteraction(Long id) {
    // salesHDBInteractionRepository.deleteById(id);
    // }

}
