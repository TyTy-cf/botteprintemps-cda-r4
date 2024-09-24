package fr.cda.botteprintemps.exception;

import jakarta.persistence.EntityNotFoundException;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class CustomEntityNotFoundException extends EntityNotFoundException {

    private String field;

    private Object value;

    private String entity;

}
