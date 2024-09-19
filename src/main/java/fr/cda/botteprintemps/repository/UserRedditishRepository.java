package fr.cda.botteprintemps.repository;

import fr.cda.botteprintemps.entity.redditish.UserRedditish;
import fr.cda.botteprintemps.repository.interfaces.SlugRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRedditishRepository extends
        JpaRepository<UserRedditish, Long>,
        SlugRepository<UserRedditish, Long> {

    Optional<UserRedditish> findByNickname(String nickname);

}
