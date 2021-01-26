import com.fasterxml.jackson.databind.ObjectMapper;
import model.Coordinate;
import model.Request;
import service.GoogleDirectionsService;
import service.PathPlotterService;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class PathPlotDriver {

    public static void main(String[] args) throws IOException {
        GoogleDirectionsService googleDirectionsService = new GoogleDirectionsService();
        PathPlotterService pathPlotterService = new PathPlotterService(googleDirectionsService);
        BufferedReader reader = new BufferedReader(new FileReader(args[0]));
        StringBuilder stringBuilder = new StringBuilder();
        String line = null;
        while ((line = reader.readLine()) != null) {
            stringBuilder.append(line);
        }
        com.fasterxml.jackson.databind.ObjectMapper objectMapper = new ObjectMapper();
        Request request = objectMapper.readValue(stringBuilder.toString().trim(), Request.class);
        Coordinate source = new Coordinate(request.getSource().getLatitude(), request.getDestination().getLongitude());
        Coordinate destination = new Coordinate(request.getDestination().getLatitude(), request.getDestination().getLongitude());
        List<Coordinate> plotList = pathPlotterService.getPathPlot(source, destination);
        System.out.println(objectMapper.writeValueAsString(plotList));
    }

}
