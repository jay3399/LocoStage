package com.example.locostage.infrastructure.external;

import com.example.locostage.ui.request.LocationRequest;
import com.fasterxml.jackson.databind.util.JSONPObject;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;
import org.springframework.web.reactive.function.client.WebClient;


public class LocationService {

    public static String getCountryName(LocationRequest locationRequest)
            throws IOException, URISyntaxException {
        String apiKey = "AIzaSyDFup_J_QaBCzInKt3luX3bxlq_mTKOtV4";
        String urlString = String.format(
                "https://maps.googleapis.com/maps/api/geocode/json?latlng=%f,%f&key=%s",
                locationRequest.getLatitude(), locationRequest.getLongitude(), apiKey);

        WebClient webClient = WebClient.create();

        Map<String, Object> response = webClient.get().uri(new URI(urlString))
                .retrieve()
                .bodyToMono(Map.class)
                .block();

        if (response != null) {

            Map<String, Object> firstResult = (Map<String, Object>) ((List<?>) response.get("results")).get(0);


            List<Map<String, Object>> addressComponents = (List<Map<String, Object>>) firstResult.get(
                    "address_components");

            for (Map<String, Object> component : addressComponents) {

                List<String> types = (List<String>) component.get("types");

                if (types.contains("country")) {
                    return (String) component.get("long_name");
                }

            }
        }
        return null;
    }
}

