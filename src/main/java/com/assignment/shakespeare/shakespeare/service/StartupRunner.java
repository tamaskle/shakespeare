package com.assignment.shakespeare.shakespeare.service;

import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class StartupRunner implements CommandLineRunner {

    private final ProcessorService processorService;

    @Override
    public void run(String... args) throws Exception {
        processorService.process();
    }
}
