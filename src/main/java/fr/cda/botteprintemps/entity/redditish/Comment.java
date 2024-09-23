package fr.cda.botteprintemps.entity.redditish;

import com.fasterxml.jackson.annotation.JsonView;
import fr.cda.botteprintemps.json_views.JsonViews;
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
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, columnDefinition = "TEXT")
    @JsonView(JsonViews.UserRedditishShow.class)
    private String content;

    @Column(nullable = false)
    @JsonView(JsonViews.UserRedditishShow.class)
    private LocalDateTime createdAt;

    @JsonView(JsonViews.UserRedditishShow.class)
    private LocalDateTime updatedAt;

    @ManyToOne
    private Comment commentFrom;

    @OneToMany(mappedBy = "commentFrom")
    private List<Comment> responses = new ArrayList<>();

    @ManyToOne
    @JoinColumn(nullable = false)
    private UserRedditish writer;

    @ManyToOne
    @JoinColumn(nullable = false)
    @JsonView(JsonViews.UserRedditishShow.class)
    private Thread thread;

    @OneToMany(mappedBy = "comment")
    private List<Reaction> reactions = new ArrayList<>();

}