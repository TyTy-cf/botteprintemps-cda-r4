package fr.cda.botteprintemps.service.redditish;

import fr.cda.botteprintemps.dto.redditish.UserRegisterDTO;
import fr.cda.botteprintemps.entity.redditish.UserRedditish;
import fr.cda.botteprintemps.exception.CustomEntityNotFoundException;
import fr.cda.botteprintemps.repository.redditish.UserRedditishRepository;
import fr.cda.botteprintemps.service.interfaces.ServiceListInterface;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserRedditishService implements
        ServiceListInterface<UserRedditish, Long, UserRegisterDTO, UserRedditish> {

    private UserRedditishRepository repository;

    @Override
    public UserRedditish create(UserRegisterDTO o) {
        UserRedditish user = new UserRedditish();
        user.setRegisteredAt(LocalDateTime.now());
        user.setEmail(o.getEmail());
        user.setNickname(o.getNickname());
        user.setPassword(o.getPassword());
        return repository.saveAndFlush(user);
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
        return repository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public List<UserRedditish> list() {
        return repository.findAll();
    }

    public UserRedditish findOneBySlug(String slug) {
//        Optional<UserRedditish> optional = repository.findOneBySlug(slug);
//        if (optional.isEmpty()) {
//            throw new EntityNotFoundException();
//        }
//        return optional.get();
        return repository.findOneBySlug(slug).orElseThrow(() ->
            new CustomEntityNotFoundException(
                "slug",
                slug,
                UserRedditish.class.getSimpleName()
            )
        );
    }
}
