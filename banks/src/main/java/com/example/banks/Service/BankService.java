package com.example.banks.Service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class BankService {

    @Value("${maps.service.url}")
    private String mapsUrl;

    private final RestTemplate restTemplate = new RestTemplate();

    public List getNearbyBanks(String zipcode) {
        String url = mapsUrl + "?zipcode=" + zipcode;

        // Calling the maps endpoint in banks

        return restTemplate.getForObject(url, List.class);
    }
}

