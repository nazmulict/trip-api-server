package com.nazmul.trip.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ResourceController {

    @GetMapping("/resource")
    public ResponseEntity<Map<String, String>> getResource() {
        Map<String, String> response = new HashMap<>();
        response.put("message", "Request successful");
        return ResponseEntity.ok(response);
    }
}