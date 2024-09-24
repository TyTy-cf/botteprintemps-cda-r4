package fr.cda.botteprintemps.advisor;

import fr.cda.botteprintemps.custom_response.CustomResponse;
import fr.cda.botteprintemps.exception.CustomEntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
public class EntityNotFoundResponse {

    @ResponseBody
    @ExceptionHandler(CustomEntityNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_GATEWAY)
    CustomResponse entityNotFoundHandler(CustomEntityNotFoundException exception) {
        CustomResponse response = new CustomResponse();
        response.setStatus(HttpStatus.BAD_GATEWAY.value());
        response.setField(exception.getField());
        response.setValue(exception.getValue());
        response.setEntity(exception.getEntity());
        return response;
    }

}
