package gusev.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class RegistrationApi {

    @JsonProperty("info")
    private Info info;

    @JsonProperty("register_data")
    private RegisterData registerData;

    @Data
    public static class Info {
        @JsonProperty("message")
        private String message;

        @JsonProperty("status")
        private String status;

    }

    @Data
    public static class RegisterData {
        @JsonProperty("games")
        private List<Game> games;

        @JsonProperty("id")
        private int id;

        @JsonProperty("login")
        private String login;

        @JsonProperty("pass")
        private String pass;

    }

    @Data
    public static class Game {
        @JsonProperty("company")
        private String company;

        @JsonProperty("description")
        private String description;

        @JsonProperty("dlcs")
        private List<Dlc> dlcs;

        @JsonProperty("gameId")
        private int gameId;

        @JsonProperty("genre")
        private String genre;

        @JsonProperty("isFree")
        private boolean isFree;

        @JsonProperty("price")
        private double price;

        @JsonProperty("publish_date")
        private String publishDate;

        @JsonProperty("rating")
        private int rating;

        @JsonProperty("requiredAge")
        private boolean requiredAge;

        @JsonProperty("requirements")
        private Requirements requirements;

        @JsonProperty("tags")
        private List<String> tags;

        @JsonProperty("title")
        private String title;

    }

    @Data
    public static class Dlc {
        @JsonProperty("description")
        private String description;

        @JsonProperty("dlcName")
        private String dlcName;

        @JsonProperty("isDlcFree")
        private boolean isDlcFree;

        @JsonProperty("price")
        private double price;

        @JsonProperty("rating")
        private int rating;

        @JsonProperty("similarDlc")
        private SimilarDlc similarDlc;

    }

    @Data
    public static class SimilarDlc {
        @JsonProperty("dlcNameFromAnotherGame")
        private String dlcNameFromAnotherGame;

        @JsonProperty("isFree")
        private boolean isFree;

    }

    @Data
    public static class Requirements {
        @JsonProperty("hardDrive")
        private double hardDrive;

        @JsonProperty("osName")
        private String osName;

        @JsonProperty("ramGb")
        private double ramGb;

        @JsonProperty("videoCard")
        private String videoCard;

    }
}