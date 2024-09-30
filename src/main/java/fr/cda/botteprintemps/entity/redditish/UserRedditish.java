package fr.cda.botteprintemps.entity.redditish;

import com.fasterxml.jackson.annotation.JsonView;
import fr.cda.botteprintemps.json_views.JsonViews;
import fr.cda.botteprintemps.slugger.SluggerInterface;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
public class UserRedditish implements SluggerInterface, UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 120, nullable = false)
    @JsonView(JsonViews.UserRedditishShow.class)
    private String email;

    @Column(length = 120, nullable = false)
    @JsonView(JsonViews.UserRedditishMinimalView.class)
    private String nickname;

    @Column(length = 120, nullable = false)
    private String password;

    private String activationCode;

    private String roles;

    @Column(nullable = false)
    @JsonView(JsonViews.UserRedditishMinimalView.class)
    private LocalDateTime registeredAt = LocalDateTime.now();

    @Column(length = 120, nullable = false)
    @JsonView(JsonViews.UserRedditishMinimalView.class)
    private String slug = "";

    @OneToMany(mappedBy = "user")
    @JsonView(JsonViews.UserRedditishShow.class)
    private List<UserFavoriteCategory> favorites = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    @JsonView(JsonViews.UserRedditishShow.class)
    private List<Follow> follows = new ArrayList<>();

    @OneToMany(mappedBy = "writer")
    @JsonView(JsonViews.UserRedditishShow.class)
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Reaction> reactions = new ArrayList<>();

    @Override
    public String getField() {
        return UUID.randomUUID() + "-" + nickname;
    }

    @JsonView(JsonViews.UserRedditishMinimalView.class)
    public boolean isActive() {
        return activationCode == null;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isEnabled() {
        return isActive();
    }
}