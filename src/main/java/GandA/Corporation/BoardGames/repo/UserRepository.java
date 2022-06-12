package GandA.Corporation.BoardGames.repo;

import GandA.Corporation.BoardGames.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u FROM User u WHERE u.mail = :email")
    User getUserByEmail(@Param("email") String email);

    @Query("SELECT u FROM User u WHERE u.id = :id")
    User getUserById(Long id);

}
