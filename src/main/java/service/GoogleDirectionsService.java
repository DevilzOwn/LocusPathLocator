package service;

import exception.DirectionServiceException;
import model.Coordinate;
import model.DirectionsResponse;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.URL;
import java.net.URLEncoder;
import java.nio.file.DirectoryNotEmptyException;
import java.util.HashMap;
import java.util.Map;

public class GoogleDirectionsService implements DirectionsService {
    @Override
    public DirectionsResponse getPathData(Coordinate source, Coordinate destination) throws IOException {
        URL url = new URL("https://maps.googleapis.com/maps/api/directions/json?origin=12.94523,77.61896&destination=12.95944,77.66085&key=AIzaSyAb8ohmBXqtK4y2_a5CFnFnfLGiOsuwjIo");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("POST");
//        con.setRequestProperty("Content-Type", "application/json");

        con.setDoOutput(true);
        Map<String, String> parameters = new HashMap<>();
        parameters.put("origin", String.valueOf(source.getLatitude()) + "," + String.valueOf(source.getLongitude()));
        parameters.put("destination", String.valueOf(destination.getLatitude()) + "," + String.valueOf(destination.getLongitude()));
        parameters.put("key", "AIzaSyAb8ohmBXqtK4y2_a5CFnFnfLGiOsuwjIo");
        DataOutputStream out = new DataOutputStream(con.getOutputStream());
        out.writeBytes(ParameterStringBuilder.getParamsString(parameters));
        out.flush();
        out.close();

        if (con.getResponseCode() == 200) {
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer content = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();
            String stringifiedResponse = content.toString().replaceAll("\\s", "");
            ObjectMapper objectMapper = new ObjectMapper();
            DirectionsResponse directionsResponse = objectMapper.readValue(stringifiedResponse, DirectionsResponse.class);
            return directionsResponse;
        } else {
            throw new DirectionServiceException("Got response code: " + con.getResponseCode() + " from google directions");
        }
    }

    static class ParameterStringBuilder {
        public static String getParamsString(Map<String, String> params)
                throws UnsupportedEncodingException {
            StringBuilder result = new StringBuilder();

            for (Map.Entry<String, String> entry : params.entrySet()) {
                result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
                result.append("=");
                result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
                result.append("&");
            }

            String resultString = result.toString();
            return resultString.length() > 0
                    ? resultString.substring(0, resultString.length() - 1)
                    : resultString;
        }
    }
}