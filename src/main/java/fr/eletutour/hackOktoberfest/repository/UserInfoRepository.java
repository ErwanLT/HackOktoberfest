package fr.eletutour.hackOktoberfest.repository;

import fr.eletutour.hackOktoberfest.domain.user.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserInfoRepository extends JpaRepository<AppUser, Long> {
    Optional<AppUser> findByUsername(String username);
}
