package gusev.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Info {
    @JsonProperty("message")
    private String message;
    @JsonProperty("status")
    private String status;
}