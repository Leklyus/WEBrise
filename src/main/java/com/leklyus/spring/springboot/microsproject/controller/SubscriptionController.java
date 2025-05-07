package com.leklyus.spring.springboot.microsproject.controller;

import com.leklyus.spring.springboot.microsproject.service.SubscriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/subscriptions")
public class SubscriptionController {
    private final SubscriptionService service;

    @GetMapping("/top")
    public ResponseEntity<List<String>> getTopSubscriptions() {
        return ResponseEntity.ok(service.getTopSubscriptions());
    }
}