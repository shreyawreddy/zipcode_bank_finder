package com.example.banks.Controller;

import com.example.banks.Service.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/banks")
public class BankController {

    @Autowired
    private BankService bankService;

    @GetMapping("/nearby")
    public List<String> getNearbyBanks(@RequestParam String zipcode) {

        return bankService.getNearbyBanks(zipcode);
    }
}

