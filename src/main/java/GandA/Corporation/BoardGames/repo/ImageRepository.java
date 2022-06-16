package GandA.Corporation.BoardGames.repo;

import GandA.Corporation.BoardGames.domain.Image;
import GandA.Corporation.BoardGames.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ImageRepository extends JpaRepository<Image, Long> {

}
