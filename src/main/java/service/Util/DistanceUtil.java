package service.Util;

import model.Coordinate;

public class DistanceUtil {

    public static double getDistance(Coordinate x, Coordinate y) {
        if ((x.getLatitude() == y.getLatitude()) && (x.getLongitude() == y.getLongitude())) {
            return 0;
        } else {
            double theta = x.getLongitude() - y.getLongitude();
            double dist = Math.sin(Math.toRadians(x.getLatitude())) * Math.sin(Math.toRadians(y.getLatitude())) + Math.cos(Math.toRadians(x.getLatitude())) * Math.cos(Math.toRadians(y.getLatitude())) * Math.cos(Math.toRadians(theta));
            dist = Math.acos(dist);
            dist = Math.toDegrees(dist);
            dist = dist * 60 * 1.1515;
            dist = dist * 1.609344 * 1000;
            return Math.abs(dist);
        }
    }
}
