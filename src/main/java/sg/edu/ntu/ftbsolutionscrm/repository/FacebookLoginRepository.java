package sg.edu.ntu.ftbsolutionscrm.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import sg.edu.ntu.ftbsolutionscrm.entity.FacebookLogin;

public interface FacebookLoginRepository extends JpaRepository<FacebookLogin, Long> {

    Optional<FacebookLogin> findByFacebookUid(String facebookUid);
}
