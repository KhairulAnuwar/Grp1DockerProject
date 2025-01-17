package sg.edu.ntu.ftbsolutionscrm.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import sg.edu.ntu.ftbsolutionscrm.entity.HDBUser;
import sg.edu.ntu.ftbsolutionscrm.service.HDBUserServiceImpl;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/users")
public class HDBUserController {

  private HDBUserServiceImpl hdbUserService;

  private static final Logger logger = LoggerFactory.getLogger(HDBUserController.class);

  public HDBUserController(HDBUserServiceImpl hdbUserService) {
    this.hdbUserService = hdbUserService;
  }

  @PostMapping("")
  public ResponseEntity<HDBUser> createHdbUser(@Valid @RequestBody HDBUser hdbUser, BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      System.out.println(bindingResult.getAllErrors());
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    HDBUser newHdbUser = hdbUserService.createHdbUser(hdbUser);
    return new ResponseEntity<>(newHdbUser, HttpStatus.CREATED);
  }

  @GetMapping("")
  public ResponseEntity<ArrayList<HDBUser>> getAllCustomers() {
    ArrayList<HDBUser> allCustomers = hdbUserService.getAllHdbUsers();
    return new ResponseEntity<>(allCustomers, HttpStatus.OK);
  }

  @PutMapping("/{id}")
  public ResponseEntity<HDBUser> updateHdbUser(@PathVariable Long id, @RequestBody HDBUser hdbUser) {
    HDBUser updatedCustomer = hdbUserService.updateHdbUser(id, hdbUser);
    return new ResponseEntity<>(updatedCustomer, HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<HttpStatus> deleteCustomer(@PathVariable Long id) {
    hdbUserService.deleteHdbUser(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

}
