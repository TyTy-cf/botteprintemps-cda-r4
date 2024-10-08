package fr.cda.botteprintemps.dto.redditish;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDisplayDTO {

    private String email;

    private String nickname;

    private LocalDateTime registeredAt;

}
