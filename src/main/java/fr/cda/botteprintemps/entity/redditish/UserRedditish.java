package fr.cda.botteprintemps.entity.redditish;

import fr.cda.botteprintemps.slugger.SluggerInterface;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
public class UserRedditish implements SluggerInterface {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 120, nullable = false)
    private String email;

    @Column(length = 120, nullable = false)
    private String nickname;

    @Column(length = 120, nullable = false)
    private String password;

    private String activationCode;

    @Column(nullable = false)
    private LocalDateTime registeredAt = LocalDateTime.now();

    @Column(length = 120, nullable = false)
    private String slug;

    @OneToMany(mappedBy = "user")
    private List<UserFavoriteCategory> favorites = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Follow> follows = new ArrayList<>();

    @OneToMany(mappedBy = "writer")
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Reaction> reactions = new ArrayList<>();

    @Override
    public String getField() {
        return UUID.randomUUID() + "-" + nickname;
    }

}