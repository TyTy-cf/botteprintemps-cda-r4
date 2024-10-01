package fr.cda.botteprintemps.service.redditish;

import fr.cda.botteprintemps.dto.redditish.UserRegisterDTO;
import fr.cda.botteprintemps.entity.redditish.UserRedditish;
import fr.cda.botteprintemps.exception.CustomEntityNotFoundException;
import fr.cda.botteprintemps.repository.redditish.UserRedditishRepository;
import fr.cda.botteprintemps.service.interfaces.ServiceListInterface;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
@AllArgsConstructor
public class UserRedditishService implements
        ServiceListInterface<UserRedditish, Long, UserRegisterDTO, UserRedditish>,
        UserDetailsService {

    private UserRedditishRepository repository;
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public UserRedditish create(UserRegisterDTO o) {
        UserRedditish user = new UserRedditish();
        user.setRegisteredAt(LocalDateTime.now());
        user.setEmail(o.getEmail());
        user.setNickname(o.getNickname());
        user.setPassword(passwordEncoder.encode(o.getPassword()));
        return repository.saveAndFlush(user);
    }

    @Override
    public UserRedditish update(UserRedditish o, Long id) {
        o.setId(id);
        return repository.saveAndFlush(o);
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

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserRedditish user = repository.findByEmailAndActivationCodeIsNull(username)
                .orElseThrow(() -> new UsernameNotFoundException("Les cochons sont dans la baie"));

        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                userGrantedAuthority(user.getRoles())
        );
    }

    /**
     * Indiquer à Spring comment on va gérer les rôles dans notre application
     * Ici on part d'un JSON de rôles, libre à vous de faire autrement, mais
     * vous devrez passer par cette méthode pour "instancier" vos objets de type
     * "GrantedAuthority"
     *
     * @param jsonRoles
     * @return
     */
    private Collection<? extends GrantedAuthority> userGrantedAuthority(String jsonRoles) {
//    private Collection<? extends GrantedAuthority> userGrantedAuthority(List<Role> roles) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        // jsonRoles = ["ROLE_USER"] ou ["ROLE_USER", "ROLE_ADMIN"]
        List<String> roles = Collections.singletonList(jsonRoles);
        roles.forEach(r -> {
            // r => "ROLE_USER" puis "ROLE_ADMIN"
            authorities.add(new SimpleGrantedAuthority(r));
        });
        return authorities;
    }
}
