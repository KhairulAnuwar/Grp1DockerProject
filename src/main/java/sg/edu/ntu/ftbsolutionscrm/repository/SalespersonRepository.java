package sg.edu.ntu.ftbsolutionscrm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import sg.edu.ntu.ftbsolutionscrm.entity.Salesperson;
import java.util.List;

public interface SalespersonRepository extends JpaRepository<Salesperson,Long> {
    
    List<Salesperson> findByFirstNameIgnoreCase(String firstName);


}
