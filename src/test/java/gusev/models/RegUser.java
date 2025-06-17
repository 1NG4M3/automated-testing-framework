package gusev.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RegUser {
    @JsonProperty("login")
    private String login;
    @JsonProperty("pass")
    private String pass;
}
