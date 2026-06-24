package com.smartcampus.reporting.util;

import com.smartcampus.reporting.model.ProgrammeReport;
import com.smartcampus.reporting.repository.ProgrammeReportRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {
    private final ProgrammeReportRepository repository;

    public DataInitializer(ProgrammeReportRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(String... args) throws Exception {
        repository.save(new ProgrammeReport("Computer Science", 150));
        repository.save(new ProgrammeReport("Mechanical Engineering", 95));
    }
}