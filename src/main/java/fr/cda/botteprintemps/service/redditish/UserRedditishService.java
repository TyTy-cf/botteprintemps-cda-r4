package fr.cda.botteprintemps.service.redditish;

import fr.cda.botteprintemps.entity.redditish.UserRedditish;
import fr.cda.botteprintemps.repository.redditish.UserRedditishRepository;
import fr.cda.botteprintemps.service.interfaces.ServiceListInterface;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserRedditishService implements ServiceListInterface<UserRedditish, Long, UserRedditish, UserRedditish> {

    private UserRedditishRepository repository;

    @Override
    public UserRedditish create(UserRedditish o) {
        o.setRegisteredAt(LocalDateTime.now());
        repository.saveAndFlush(o);
        return o;
    }

    @Override
    public UserRedditish update(UserRedditish o, Long id) {
        o.setId(id);
        repository.flush();
        return o;
    }

    @Override
    public void delete(UserRedditish object) {
        if (object != null) {
            repository.delete(object);
        }
    }

    @Override
    public UserRedditish findOneById(Long id) {
        return repository.findById(id).orElseThrow();
    }

    @Override
    public List<UserRedditish> list() {
        return repository.findAll();
    }

    public UserRedditish findOneBySlug(String slug) {
        return repository.findOneBySlug(slug).orElseThrow();
    }
}
