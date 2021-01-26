package service;

import model.Coordinate;
import model.Request;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.BufferedReader;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class PathPlotterServiceTest {

    PathPlotterService pathPlotterService;

    @Before
    public void setUp() {
        GoogleDirectionsService googleDirectionsService = new GoogleDirectionsService();
        this.pathPlotterService = new PathPlotterService(googleDirectionsService);
    }

    @Test
    public void testPlotPath() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("./src/main/resources/input.json"));
        StringBuilder stringBuilder = new StringBuilder();
        String line = null;
        while ((line = reader.readLine()) != null) {
            stringBuilder.append(line);
        }
        ObjectMapper objectMapper = new ObjectMapper();
        Request request = objectMapper.readValue(stringBuilder.toString().trim(), Request.class);
        Coordinate source = new Coordinate(request.getSource().getLatitude(), request.getDestination().getLongitude());
        Coordinate destination = new Coordinate(request.getDestination().getLatitude(), request.getDestination().getLongitude());
        List<Coordinate> plotList = pathPlotterService.getPathPlot(source, destination);
        System.out.println(objectMapper.writeValueAsString(plotList));
    }
}