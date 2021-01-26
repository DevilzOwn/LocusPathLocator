package model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.List;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Routes {
    Bounds bounds;
    String copyrights;
    List<Legs> legs;
    Map<String, String> overview_polyline;
    String summary;
    List<String> warnings;
    List<String> waypoint_order;
}
