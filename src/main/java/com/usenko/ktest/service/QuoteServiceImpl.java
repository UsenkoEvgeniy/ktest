package com.usenko.ktest.service;

import com.usenko.ktest.dto.NewQuoteDto;
import com.usenko.ktest.dto.QuoteDto;
import com.usenko.ktest.dto.Datapoint;
import com.usenko.ktest.model.Quote;
import com.usenko.ktest.model.User;
import com.usenko.ktest.model.Vote;
import com.usenko.ktest.model.exceptions.ForbiddenOperationException;
import com.usenko.ktest.model.exceptions.NotFoundException;
import com.usenko.ktest.model.mapper.QuoteMapper;
import com.usenko.ktest.repository.QuoteRepository;
import com.usenko.ktest.repository.UserRepository;
import com.usenko.ktest.repository.VoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
@Transactional
public class QuoteServiceImpl implements QuoteService {
    private final QuoteRepository quoteRepository;
    private final UserRepository userRepository;
    private final VoteRepository voteRepository;
    private final Random generator = new Random();

    @Override
    public QuoteDto createQuote(NewQuoteDto newQuote, Long userId) {
        Quote quote = QuoteMapper.newQuoteToQuote(newQuote);
        User author = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("User", userId));
        quote.setAuthor(author);
        return QuoteMapper.toQuoteDto(quoteRepository.save(quote));
    }

    @Override
    @Transactional(readOnly = true)
    public QuoteDto getById(Long id) {
        return QuoteMapper.toQuoteDto(quoteRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Quote", id)));
    }

    @Override
    public List<QuoteDto> getQuotes(Integer page, Integer qty) {
        return quoteRepository.findAll(PageRequest.of(page, qty)).stream().map(QuoteMapper::toQuoteDto).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public QuoteDto getRandom() {
        return QuoteMapper.toQuoteDto(quoteRepository.findRandom());
    }

    @Override
    @Transactional(readOnly = true)
    public List<QuoteDto> getTop() {
        return quoteRepository.findTop().stream().map(QuoteMapper::toQuoteDto).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<QuoteDto> getWorst() {
        return quoteRepository.findWorst().stream().map(QuoteMapper::toQuoteDto).toList();
    }

    @Override
    public QuoteDto updateQuote(NewQuoteDto newQuote, Long id, Long userId) {
        Quote quote = quoteRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Quote", id));
        if (quote.getAuthor().getId().equals(userId)) {
            throw new ForbiddenOperationException("Only author can change the quote");
        }
        quote.setContent(newQuote.getContent());
        quote.setModifiedDate(LocalDateTime.now());
        return QuoteMapper.toQuoteDto(quoteRepository.save(quote));
    }

    @Override
    public void deleteById(Long id, Long userId) {
        Quote quote = quoteRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Quote", id));
        if (quote.getAuthor().getId().equals(userId)) {
            throw new ForbiddenOperationException("Only author can delete the quote");
        }
        quoteRepository.deleteById(id);
    }

    @Override
    public void addVote(Boolean isLike, Long quoteId, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("User", userId));
        Quote quote = quoteRepository.findById(quoteId)
                .orElseThrow(() -> new NotFoundException("Quote", quoteId));
        Vote vote = new Vote();
        vote.setQuote(quote);
        vote.setUser(user);
        vote.setVote(Boolean.TRUE.equals(isLike) ? 1 : -1);
        vote.setCreationDate(LocalDateTime.now());
        voteRepository.save(vote);
    }

    @Override
    public void deleteVote(Long quoteId, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("User", userId));
        Quote quote = quoteRepository.findById(quoteId)
                .orElseThrow(() -> new NotFoundException("Quote", quoteId));
        voteRepository.deleteByUserAndQuote(user, quote);
    }

    @Override
    public List<Datapoint> getGraph(Long id) {

        List<Datapoint> map =  voteRepository.getGraph(id);
        System.out.println(map);
        return map;
    }
}
