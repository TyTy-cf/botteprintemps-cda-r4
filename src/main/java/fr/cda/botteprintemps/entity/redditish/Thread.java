package fr.cda.botteprintemps.entity.redditish;

import com.fasterxml.jackson.annotation.JsonView;
import fr.cda.botteprintemps.json_views.JsonViews;
import fr.cda.botteprintemps.slugger.SluggerInterface;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
public class Thread implements SluggerInterface {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @JsonView(JsonViews.UserRedditishShow.class)
    private String title;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    @JsonView(JsonViews.UserRedditishShow.class)
    private String slug;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Category category;

    @OneToMany(mappedBy = "thread")
    private List<Comment> comments = new ArrayList<>();

    @Override
    public String getField() {
        return category.getName() + "-" + title;
    }
}