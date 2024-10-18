package com.david.task_manager.service;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ScheduledPriorityService {

    private final ScoreService scoreService;

    @Scheduled(cron = "0 0 2 * * ?")
    public void defineScore() {
        scoreService.setTaskRepository();
    }
}
