package fr.cda.botteprintemps.repository.redditish;

import fr.cda.botteprintemps.entity.redditish.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends
        JpaRepository<Comment, Long> {

}
