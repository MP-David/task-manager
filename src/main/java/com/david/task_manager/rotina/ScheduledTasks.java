package com.david.task_manager.rotina;

import com.david.task_manager.service.ScoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ScheduledTasks {

    private final ScoreService scoreService;

    @Scheduled(cron = "0 0 2 * * ?")
    public void defineScore() {
        // código a ser executado diariamente às 2:00 AM
        scoreService.setTaskRepository();
    }
}
