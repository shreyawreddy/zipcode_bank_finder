package com.example.maps.Service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.*;

@Service
public class MapsService {

    // value is in application yml file
    @Value("${google.api.key}")
    private String apiKey;

    private final RestTemplate restTemplate = new RestTemplate();

    public List<String> findNearbyBanks(String zipcode) {

        // we need place api for getting near by bank but to get it we need latitude and
        // logituide so we use geolocation api to first get the coordinates and then we can
        // proceed with place api Get coordinates from zipcode

        String geoUrl = "https://maps.googleapis.com/maps/api/geocode/json?address=" + zipcode + "&key=" + apiKey;
        Map geoResponse = restTemplate.getForObject(geoUrl, Map.class);

        List results = (List) geoResponse.get("results");
        if (results == null || results.isEmpty()) {
            return List.of("Invalid zipcode or no results.");
        }

        // we can use geoResponse class to get the response but since we need only 2 fields
        // so using Map. (json of map api is in sample json folder for understanding)
        Map location = (Map) ((Map) ((Map) results.get(0)).get("geometry")).get("location");
        double lat = (Double) location.get("lat");
        double lng = (Double) location.get("lng");

        //using coordinates to search nearby banks and radius is 10 miles so 16093 meters
        String placesUrl = String.format(
            "https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=%f,%f&radius=16093&type=bank&key=%s",
            lat, lng, apiKey
        );

        Map placesResponse = restTemplate.getForObject(placesUrl, Map.class);
        List<Map<String, Object>> places = (List<Map<String, Object>>) placesResponse.get("results");

        // returning bank names (check sample banks json)
        List<String> bankNames = new ArrayList<>();
        for (Map<String, Object> place : places) {
            bankNames.add((String) place.get("name"));
        }

        return bankNames;
    }
}

