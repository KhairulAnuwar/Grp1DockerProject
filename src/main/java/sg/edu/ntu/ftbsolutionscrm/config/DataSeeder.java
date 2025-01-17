package sg.edu.ntu.ftbsolutionscrm.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import sg.edu.ntu.ftbsolutionscrm.entity.Login;
import sg.edu.ntu.ftbsolutionscrm.entity.FacebookLogin;
import sg.edu.ntu.ftbsolutionscrm.repository.LoginRepository;
import sg.edu.ntu.ftbsolutionscrm.repository.FacebookLoginRepository;

@Configuration
public class DataSeeder {

    @Value("${facebook.mock-uuid}") //get value from app.properties
    private String facebookMockUuid;

    @Bean
    //Make sure it runs after app start
    CommandLineRunner initDataBase(LoginRepository loginRepository, FacebookLoginRepository facebookLoginRepository) {
        return args -> {
            // Seed admin user
            loginRepository.findByEmail("admin@admin.com").orElseGet(() -> {
                Login user = new Login();
                user.setEmail("admin@admin.com");
                user.setPassword("123456");
                return loginRepository.save(user);
            });

            // Seed Facebook UID for mock test
            if (facebookLoginRepository.findByFacebookUid(facebookMockUuid).isEmpty()) {
                FacebookLogin facebookLogin = new FacebookLogin();
                facebookLogin.setFacebookUid(facebookMockUuid);
                facebookLoginRepository.save(facebookLogin);
                System.out.println("Database seeded with mock Facebook UID: " + facebookMockUuid);
            }
        };
    }
}
