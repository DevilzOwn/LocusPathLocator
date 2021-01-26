package model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DirectionsResponse {

    @JsonProperty("geocoded_waypoints")
    List<GeocodedWaypoints> geocodedWaypoints;
    List<Routes> routes;
    String status;

}
