package fr.cda.botteprintemps.controller_rest;

import com.fasterxml.jackson.annotation.JsonView;
import fr.cda.botteprintemps.custom_response.CustomResponse;
import fr.cda.botteprintemps.dto.redditish.UserDisplayDTO;
import fr.cda.botteprintemps.dto.redditish.UserRegisterDTO;
import fr.cda.botteprintemps.dto.redditish.UserUpdateDTO;
import fr.cda.botteprintemps.entity.User;
import fr.cda.botteprintemps.entity.redditish.UserRedditish;
import fr.cda.botteprintemps.json_views.JsonViews;
import fr.cda.botteprintemps.repository.redditish.UserRedditishRepository;
import fr.cda.botteprintemps.service.redditish.UserRedditishService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedModel;
import org.springframework.data.web.SortDefault;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@AllArgsConstructor
// Toutes les requêtes HTTP commençant par "/api/user" sont interceptées ICI
@RequestMapping(value = "/api/user", name = "app_user_")
public class UserRedditishRestController {

    private UserRedditishService userRedditishService;
    private UserRedditishRepository userRedditishRepository;

    // Requête HTTP de type GET
    @GetMapping(name = "list")
    public Page<UserDisplayDTO> list(
        @PageableDefault(
            size = 12, // nb Element par page
            sort = { "registeredAt" }, // order by
            direction = Sort.Direction.DESC
        ) Pageable pageable
    ) {
        return userRedditishService.listDTO(pageable);
    }

    @GetMapping(value = "/{slug}", name = "show")
    @JsonView(JsonViews.UserRedditishShow.class)
    public UserRedditish show(@PathVariable String slug) {
        return userRedditishService.findOneBySlug(slug);
    }

    @PutMapping(value = "/{id}", name = "update")
    public UserRedditish update(@PathVariable String id, @RequestBody UserUpdateDTO dto) {
        System.out.println(id);
        System.out.println(dto);
        // http://localhost:8080/api/user/bd7c0af3-5cf5-4c19-9930-3629b5fd0b7d
        return new UserRedditish();
    }

}