package com.usenko.ktest.service;

import com.usenko.ktest.dto.NewQuoteDto;
import com.usenko.ktest.dto.QuoteDto;
import com.usenko.ktest.dto.Datapoint;

import java.util.List;

public interface QuoteService {
    QuoteDto createQuote(NewQuoteDto newQuote, Long userId);

    QuoteDto getById(Long id);

    QuoteDto getRandom();

    List<QuoteDto> getTop();

    List<QuoteDto> getWorst();

    QuoteDto updateQuote(NewQuoteDto newQuote, Long id, Long userId);

    void deleteById(Long id, Long userId);

    void addVote(Boolean like, Long quoteId, Long userId);

    void deleteVote(Long quoteId, Long userId);

    List<QuoteDto> getQuotes(Integer page, Integer qty);

    List<Datapoint> getGraph(Long id);
}
