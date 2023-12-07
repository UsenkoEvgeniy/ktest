package com.usenko.ktest.model.mapper;

import com.usenko.ktest.dto.NewQuoteDto;
import com.usenko.ktest.dto.QuoteDto;
import com.usenko.ktest.model.Vote;
import com.usenko.ktest.model.Quote;
import lombok.experimental.UtilityClass;

import java.time.LocalDateTime;

@UtilityClass
public class QuoteMapper {
    public Quote newQuoteToQuote(NewQuoteDto dto) {
        Quote quote = new Quote();
        quote.setContent(dto.getContent());
        quote.setCreationDate(LocalDateTime.now());
        return quote;
    }

    public QuoteDto toQuoteDto(Quote quote) {
        QuoteDto dto = new QuoteDto();
        dto.setId(quote.getId());
        dto.setContent(quote.getContent());
        dto.setCreationDate(quote.getCreationDate());
        dto.setModifiedDate(quote.getModifiedDate());
        dto.setRating(quote.getVotes().stream().mapToInt(Vote::getVote).sum());
        dto.setAuthorId(quote.getAuthor().getId());
        return dto;
    }
}
