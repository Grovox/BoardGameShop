package GandA.Corporation.BoardGames.repo;

import GandA.Corporation.BoardGames.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u FROM User u WHERE u.mail = :mail")
    User getUserByMail(@Param("mail") String mail);

    @Query("SELECT u FROM User u WHERE u.id = :id")
    User getUserById(Long id);

    List<User> findByMailContainingIgnoreCase(String mail);

    User findByMail(String mail);
}
