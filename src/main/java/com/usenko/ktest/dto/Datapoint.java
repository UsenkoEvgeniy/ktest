package com.usenko.ktest.dto;

import java.time.LocalDate;

public interface Datapoint {
    LocalDate getLocaldate();
    Long getVotes();
}