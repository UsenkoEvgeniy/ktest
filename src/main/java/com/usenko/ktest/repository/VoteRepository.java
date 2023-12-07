package com.usenko.ktest.repository;

import com.usenko.ktest.dto.Datapoint;
import com.usenko.ktest.model.Quote;
import com.usenko.ktest.model.User;
import com.usenko.ktest.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface VoteRepository extends JpaRepository<Vote, Long> {
    long deleteByUserAndQuote(User user, Quote quote);

    @Query(value = "SELECT localdate, SUM(vote) OVER (ORDER BY localdate) AS votes " +
            "FROM (SELECT DATE_TRUNC('day', creation_date) AS localdate, sum(vote) as vote " +
            "FROM quotes_votes WHERE quote_id = ?1 GROUP BY localdate)", nativeQuery = true)
    List<Datapoint> getGraph(long quoteId);
}
