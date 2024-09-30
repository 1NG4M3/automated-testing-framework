package gusev.dto;

import lombok.Data;

import java.util.List;

@Data
public class UserLoginsResponse {
    private List<String> logins;
}