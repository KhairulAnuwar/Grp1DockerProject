package sg.edu.ntu.ftbsolutionscrm.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import lombok.Getter;
import lombok.Setter;
import sg.edu.ntu.ftbsolutionscrm.entity.ResaleHdb;
import sg.edu.ntu.ftbsolutionscrm.service.ResaleHDBServiceImpl;

import java.util.List;

//testing the push
@RestController
@RequestMapping("/resale-hdb")
@Setter
@Getter
public class ResaleHdbController {

    private static final Logger logger = LoggerFactory.getLogger(ResaleHdbController.class);

    @Autowired
    private ResaleHDBServiceImpl resaleHDBService;

    @GetMapping("")
    public ResponseEntity<List<ResaleHdb>> getAllResaleHDB() {
        logger.info("Getting all Resales HDB list");
        return new ResponseEntity<>(resaleHDBService.getAllResaleHDB(), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResaleHdb> getResaleHDBById(@PathVariable Long id) {
        logger.info("Getting the details of the Resales HDB : " + id);
        return new ResponseEntity<>(resaleHDBService.getResaleHDBById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ResaleHdb> createResaleHDB(@Valid @RequestBody ResaleHdb resaleHDB) {
        logger.info("New Resale HDB created");
        return new ResponseEntity<>(resaleHDBService.createResaleHDB(resaleHDB), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResaleHdb> updateResaleHDB(@PathVariable Long id,
            @Valid @RequestBody ResaleHdb resaleHDBDetails) {
        ResaleHdb updatedResaleHDB = resaleHDBService.updatResaleHDB(id, resaleHDBDetails);
        logger.info("Resale HDB with id : " + id + "updated");
        return new ResponseEntity<>(updatedResaleHDB, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void deleteResaleHDB(@PathVariable Long id) {
        logger.info("Resale HDB with id : " + id + "deleted");
        resaleHDBService.deleteResaleHDB(id);
    }
}
