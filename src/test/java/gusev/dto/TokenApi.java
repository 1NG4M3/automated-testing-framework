package gusev.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class TokenApi {
    @JsonProperty("token")
    private String token;
}