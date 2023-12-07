package com.usenko.ktest.controller;

import com.usenko.ktest.dto.NewQuoteDto;
import com.usenko.ktest.dto.QuoteDto;
import com.usenko.ktest.dto.Datapoint;
import com.usenko.ktest.service.QuoteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/quote")
@RequiredArgsConstructor
@Validated
public class QuoteController {
    private final QuoteService quoteService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public QuoteDto createQuote(@Valid @RequestBody NewQuoteDto newQuote,
                                @RequestHeader("user-id") Long userId) {
        log.info("Post request quote: {} by: {} ", newQuote, userId);
        return quoteService.createQuote(newQuote, userId);
    }

    @GetMapping("/{id}")
    public QuoteDto getById(@PathVariable Long id) {
        log.info("Get request for quote id ", id);
        return quoteService.getById(id);
    }

    @GetMapping
    public List<QuoteDto> getQuotes(@RequestParam Integer page,
                                    @RequestParam Integer qty) {
        log.info("Get request for page {} with {} quotes", page, qty);
        return quoteService.getQuotes(page, qty);
    }

    @GetMapping("/rand")
    public QuoteDto getRandomQuote() {
        log.info("Get random quote");
        return quoteService.getRandom();
    }

    @GetMapping("/top")
    public List<QuoteDto> getTop() {
        log.info("Get top 10 quotes");
        return quoteService.getTop();
    }

    @GetMapping("/worst")
    public List<QuoteDto> getWorst() {
        log.info("Get worst 10 quotes");
        return quoteService.getWorst();
    }

    @PatchMapping("/{id}")
    public QuoteDto updateQuote(@Valid NewQuoteDto newQuote,
                                @RequestHeader("user-id") Long userId,
                                @PathVariable Long id) {
        log.info("Update quote id {} with text {} by user {}", id, newQuote, userId);
        return quoteService.updateQuote(newQuote, id, userId);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteQuote(@RequestHeader("user-id") Long userId,
                            @PathVariable Long id) {
        log.info("Delete quote by id {} by user {}", id, userId);
        quoteService.deleteById(id, userId);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/{quoteId}/vote")
    public void addLike(@PathVariable Long quoteId,
                        @RequestParam Boolean vote,
                        @RequestHeader("user-id") Long userId) {
        log.info("Add like {} to quoteId {} by {}", vote, quoteId, userId);
        quoteService.addVote(vote, quoteId, userId);
    }

    @DeleteMapping("/{quoteId}/vote")
    public void deleteLike(@PathVariable Long quoteId,
                           @RequestHeader("user-id") Long userId) {
        log.info("Delete vote for quoteId {} by {}", quoteId, userId);
        quoteService.deleteVote(quoteId, userId);
    }

    @GetMapping("/{id}/graph")
    public List<Datapoint> getGraph(@PathVariable Long id) {
        log.info("Get request for graph for id {}", id);
        return quoteService.getGraph(id);
    }
}