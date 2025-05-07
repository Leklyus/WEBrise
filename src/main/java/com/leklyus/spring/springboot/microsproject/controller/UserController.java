package com.leklyus.spring.springboot.microsproject.controller;

import com.leklyus.spring.springboot.microsproject.model.Subscription;
import com.leklyus.spring.springboot.microsproject.model.User;
import com.leklyus.spring.springboot.microsproject.service.SubscriptionService;
import com.leklyus.spring.springboot.microsproject.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final SubscriptionService subscriptionService;

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        log.info("Request createUser: {}", user);
        return ResponseEntity.ok(userService.createUser(user));
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id) {
        log.info("Request getUser by id: {}", id);
        return userService.getUser(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user) {
        log.info("Request updateUser by id: {}, user: {}", id, user);
        return ResponseEntity.ok(userService.updateUser(id, user));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        log.info("Request deleteUser by id: {}", id);
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/subscriptions")
    public ResponseEntity<Subscription> addSubscription(@PathVariable Long id, @RequestBody Subscription sub) {
        log.info("Request addSubscription by id: {}, subscriptions: {}", id, sub);
        return ResponseEntity.ok(subscriptionService.addSubscription(id, sub));
    }

    @GetMapping("/{id}/subscriptions")
    public ResponseEntity<List<Subscription>> getSubscriptions(@PathVariable Long id) {
        log.info("Request getSubscription by id: {}", id);
        return ResponseEntity.ok(subscriptionService.getSubscriptions(id));
    }

    @DeleteMapping("/{id}/subscriptions/{subId}")
    public ResponseEntity<Void> deleteSubscription(@PathVariable Long id, @PathVariable Long subId) {
        log.info("Request deleteSubscription by id: {}", id);
        subscriptionService.deleteSubscription(subId);
        return ResponseEntity.noContent().build();
    }
}
