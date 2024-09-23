package fr.cda.botteprintemps.configuration;

import fr.cda.botteprintemps.entity.redditish.Category;
import fr.cda.botteprintemps.entity.redditish.Comment;
import fr.cda.botteprintemps.entity.redditish.Thread;
import fr.cda.botteprintemps.entity.redditish.UserRedditish;
import fr.cda.botteprintemps.repository.redditish.CategoryRepository;
import fr.cda.botteprintemps.repository.redditish.CommentRepository;
import fr.cda.botteprintemps.repository.redditish.ThreadRepository;
import fr.cda.botteprintemps.repository.redditish.UserRedditishRepository;
import lombok.AllArgsConstructor;
import net.datafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

@Component
@AllArgsConstructor
public class InitDataLoaderConfig implements CommandLineRunner {

    private UserRedditishRepository userRedditishRepository;
    private CommentRepository commentRepository;
    private ThreadRepository threadRepository;
    private CategoryRepository categoryRepository;

    @Override
    public void run(String... args) {
        createUsers();
        createCategory();
        createThreads();
        createComments();
    }

    private void createCategory() {
        if (categoryRepository.count() != 2) {
            Category category = new Category();
            category.setName("Jeux");
            category.setSlug("");
            categoryRepository.save(category);

            Category wow = new Category();
            wow.setName("World of Warcraft");
            wow.setParent(category);
            wow.setSlug("");
            categoryRepository.save(wow);
        }
    }

    private void createThreads() {
        Faker faker = new Faker(Locale.of("fr"));
        if (threadRepository.count() != 50) {
            for (long i = 1L; i <= 50L; i++) {
                Thread thread = new Thread();
                thread.setCreatedAt(generateRandomDate());
                thread.setTitle(faker.worldOfWarcraft().hero());
                thread.setCategory(categoryRepository.findById(2L).get());
                thread.setSlug("");
                threadRepository.save(thread);
            }
        }
    }

    private void createComments() {
        Faker faker = new Faker(Locale.of("fr"));
        if (commentRepository.count() != 1001) {
            for (long i = 1L; i <= 1000L; i++) {
                Random rand = new Random();
                Comment comment = new Comment();
                comment.setCreatedAt(generateRandomDate());
                comment.setContent(faker.lorem().sentence(125));
                UserRedditish user = userRedditishRepository.findById(rand.nextLong(1, 500)).get();
                comment.setWriter(user);
                Thread thread = threadRepository.findById(rand.nextLong(1, 50)).get();
                comment.setThread(thread);
                commentRepository.save(comment);
            }
        }
    }

    private void createUsers() {
        Faker faker = new Faker(Locale.of("fr"));
        List<String> duplicates = new ArrayList<>();
        if (userRedditishRepository.count() != 500) {
            for (long i = 1L; i <= 500L; i++) {
                UserRedditish user = new UserRedditish();
                String name;
                do {
                    name = faker.name().name().replace(" ", "");
                } while (duplicates.contains(name));
                duplicates.add(name);
                user.setId(i);
                user.setNickname(name);
                user.setEmail(faker.internet().emailAddress());
                user.setPassword(faker.internet().password(8, 12));
                user.setRegisteredAt(LocalDateTime.now());
                user.setSlug("");

//                User u = new User();
//                u.setFirstName(faker.name().firstName());
//                u.setLastName(faker.name().lastName());
//                u.setRoles("[\"ROLE_USER\"]");
//                u.setEmail(faker.internet().emailAddress(u.getFirstName() + "." + u.getLastName()));

                userRedditishRepository.save(user);
            }
        }
    }

    private LocalDateTime generateRandomDate() {
        Faker faker = new Faker();
        Instant randomDate = faker.timeAndDate().between(
                Instant.now().minusMillis(999999999),
                Instant.now());
        return randomDate.atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

}
