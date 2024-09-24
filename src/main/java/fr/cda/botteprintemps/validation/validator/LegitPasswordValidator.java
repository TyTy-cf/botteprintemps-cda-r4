package fr.cda.botteprintemps.validation.validator;

import fr.cda.botteprintemps.validation.constraint.LegitPassword;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class LegitPasswordValidator implements ConstraintValidator<LegitPassword, String> {

    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {
        if (!password.contains("@")) {
//            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Le mot de passe doit contenir un @");
            return false;
        }
        if (password.length() < 8) {
//            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Le mot de passe doit faire au moins 8 caractères");
            return false;
        }
        return true;
    }

}
