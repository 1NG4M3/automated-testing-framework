package gusev.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class StatusRequestResponse {
    @JsonProperty("description")
    private String description;
    @JsonProperty("statusCode")
    private Integer statusCode;
}