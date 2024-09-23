package fr.cda.botteprintemps.repository.redditish;

import fr.cda.botteprintemps.entity.redditish.Category;
import fr.cda.botteprintemps.entity.redditish.Thread;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends
        JpaRepository<Category, Long> {

}
