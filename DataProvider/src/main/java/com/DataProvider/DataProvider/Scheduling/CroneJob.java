package com.DataProvider.DataProvider.Scheduling;

import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDate;

public class CroneJob {
    @Scheduled(initialDelay = 1000, fixedRate = 10000)
    public void runEvey10Seconds() {
        System.out.println("Current time is :: " + LocalDate.now());
    }


}

