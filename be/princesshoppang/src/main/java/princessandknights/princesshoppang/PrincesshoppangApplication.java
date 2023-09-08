package princessandknights.princesshoppang;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
public class PrincesshoppangApplication {

	public static void main(String[] args) {
		SpringApplication.run(PrincesshoppangApplication.class, args);

	}

}
