package service;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.leonard.PolylineUtils;
import io.leonard.Position;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import model.Coordinate;
import model.DirectionsResponse;
import service.Util.DistanceUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PathPlotterService {

    DirectionsService directionsService;

    public List<Coordinate> getPathPlot(Coordinate source, Coordinate destination) {
        List<Coordinate> plotList = new ArrayList<>();
        Double max = Double.MIN_VALUE;
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            DirectionsResponse directionsResponse = directionsService.getPathData(source, destination);
            List<Position> points = PolylineUtils.decode(directionsResponse.getRoutes().get(0).getOverview_polyline().get("points"), 5);
            for (int i = 0; i < points.size() - 1; i++) {
                Coordinate first = new Coordinate(points.get(i).getLatitude(), points.get(i).getLongitude());
                Coordinate last = new Coordinate(points.get(i + 1).getLatitude(), points.get(i + 1).getLongitude());
                if (DistanceUtil.getDistance(first, last) > max)
                    max = DistanceUtil.getDistance(first, last);
            }
            for (int i = 0; i < points.size(); ) {
                Coordinate current = new Coordinate(points.get(i).getLatitude(), points.get(i).getLongitude());
                plotList.add(current);
                i = findPointAtDistance(points, i, max);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return plotList;
    }

    private int findPointAtDistance(List<Position> points, int currentIndex, double distance) {
        int i = currentIndex + 1;
        Coordinate source = new Coordinate(points.get(currentIndex).getLatitude(), points.get(currentIndex).getLongitude());
        for (; i < points.size() && DistanceUtil.getDistance(source, new Coordinate(points.get(i).getLatitude(), points.get(i).getLongitude())) < distance; i++) ;
        if (i == currentIndex + 1) return i;
        else return i;
    }
}
