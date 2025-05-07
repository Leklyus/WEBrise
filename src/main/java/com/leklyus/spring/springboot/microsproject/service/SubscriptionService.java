package com.leklyus.spring.springboot.microsproject.service;

import com.leklyus.spring.springboot.microsproject.model.Subscription;
import com.leklyus.spring.springboot.microsproject.model.User;
import com.leklyus.spring.springboot.microsproject.repository.SubscriptionRepository;
import com.leklyus.spring.springboot.microsproject.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class SubscriptionService {
    private static final int TOP_SUBSCRIPTIONS_LIMIT = 3;

    private final SubscriptionRepository subRepo;
    private final UserRepository userRepo;

    public Subscription addSubscription(Long userId, Subscription sub) {
        log.info("Add subscription user id: {}, subscription: {}", userId, sub);
        User user = userRepo.findById(userId).orElseThrow();
        sub.setUser(user);
        return subRepo.save(sub);
    }

    public List<Subscription> getSubscriptions(Long userId) {
        log.info("Get subscription user id: {}", userId);
        return subRepo.findByUserId(userId);
    }

    public void deleteSubscription(Long subId) {
        log.info("Delete subscription id: {}", subId);
        subRepo.deleteById(subId);
    }

    public List<String> getTopSubscriptions() {
        log.info("Get {} top subscriptions", TOP_SUBSCRIPTIONS_LIMIT);
        List<Object[]> results = subRepo.findTopSubscriptions(PageRequest.of(0, TOP_SUBSCRIPTIONS_LIMIT));
        log.info("Found {} subscriptions", results.size());
        List<String> top = new ArrayList<>();
        for (Object[] row : results) {
            top.add((String) row[0]);
        }
        return top;
    }
}