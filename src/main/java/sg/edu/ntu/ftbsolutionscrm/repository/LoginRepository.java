package sg.edu.ntu.ftbsolutionscrm.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import sg.edu.ntu.ftbsolutionscrm.entity.Login;


public interface LoginRepository extends JpaRepository<Login, Long> {

    Optional<Login> findByEmail(String email);
}
    
