package model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Steps {
    Map<String, String> distance;
    Map<String, String> duration;
    @JsonProperty("end_location")
    Coordinate endLocation;
    String html_instructions;
    String maneuver;
    private Map<String, String> polyline;
    @JsonProperty("start_location")
    Coordinate startLocation;
    String travel_mode;
}
