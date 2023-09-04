package princessandknights.princesshoppang.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import princessandknights.princesshoppang.user.entity.User;

public interface UserRepository extends JpaRepository<User,Long> {

}
