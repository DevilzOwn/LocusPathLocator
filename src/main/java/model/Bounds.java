package model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Bounds {
    @JsonProperty("northeast")
    private Coordinate northeast;

    @JsonProperty("southwest")
    private Coordinate southwest;
}
