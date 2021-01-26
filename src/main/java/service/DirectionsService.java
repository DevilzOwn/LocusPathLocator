package service;

import model.Coordinate;
import model.DirectionsResponse;

import java.io.IOException;
import java.net.ProtocolException;

public interface DirectionsService {
    public DirectionsResponse getPathData(Coordinate source, Coordinate destination) throws IOException;
}
