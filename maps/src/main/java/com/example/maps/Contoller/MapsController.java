package com.example.maps.Contoller;

import com.example.maps.Service.MapsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/maps")
public class MapsController {

    @Autowired
    private MapsService mapsService;

    @GetMapping("/banksIn10Miles")
    public List<String> getNearbyBanks(@RequestParam String zipcode) {

        return mapsService.findNearbyBanks(zipcode);
    }
}
