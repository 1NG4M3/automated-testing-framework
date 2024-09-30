package gusev.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class CarBrands {
    @JsonProperty("String")
    private List<CarBrandsAndModels> carBrandsAndModels;

    @Data
    public static class CarBrandsAndModels {
        @JsonProperty("brand")
        private String brand;
        @JsonProperty("models")
        private String[] models;
    }
}