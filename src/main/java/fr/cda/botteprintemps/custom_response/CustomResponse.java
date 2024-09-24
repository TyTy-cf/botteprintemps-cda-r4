package fr.cda.botteprintemps.custom_response;

import com.fasterxml.jackson.annotation.JsonView;
import fr.cda.botteprintemps.json_views.JsonViews;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomResponse {

    private int status;

    private String field;

    private Object value;

    private String entity;

}
