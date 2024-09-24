package fr.cda.botteprintemps.dto.redditish;

import fr.cda.botteprintemps.validation.constraint.LegitPassword;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserRegisterDTO {

    @NotBlank
    @Email(message = "L'email doit être correct")
    private String email;

    @NotBlank
    private String nickname;

    @LegitPassword
    private String password;

    @NotBlank(message = "Le mot de passe doit être renseigné")
    private String confirmedPassword;

}
