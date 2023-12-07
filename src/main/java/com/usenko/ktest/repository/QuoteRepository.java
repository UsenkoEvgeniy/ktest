package com.usenko.ktest.repository;

import com.usenko.ktest.model.Quote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface QuoteRepository extends JpaRepository<Quote, Long> {
    @Query("SELECT q FROM Quote q ORDER BY RAND() LIMIT 1")
    Quote findRandom();

    @Query("SELECT q FROM Quote q LEFT JOIN q.votes v GROUP BY q ORDER BY SUM(v.vote) DESC LIMIT 10")
    List<Quote> findTop();

    @Query("SELECT q FROM Quote q LEFT JOIN q.votes v GROUP BY q ORDER BY SUM(v.vote) ASC LIMIT 10")
    List<Quote> findWorst();
}
