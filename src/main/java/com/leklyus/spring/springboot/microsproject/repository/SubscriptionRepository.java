package com.leklyus.spring.springboot.microsproject.repository;

import com.leklyus.spring.springboot.microsproject.model.Subscription;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {
    List<Subscription> findByUserId(Long userId);

    @Query("SELECT s.service_name, COUNT(s) as cnt FROM Subscription s GROUP BY s.service_name ORDER BY cnt DESC")
    List<Object[]> findTopSubscriptions(Pageable pageable);
}
