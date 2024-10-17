package com.david.task_manager.service;

import com.david.task_manager.domain.ENUMS.PriorityEnum;
import com.david.task_manager.domain.ENUMS.StageEnum;
import com.david.task_manager.domain.Task;
import com.david.task_manager.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ScoreService {

    private final TaskService taskService;

    public void setTaskRepository() {
        List<Task> tasks = taskService.findValidTasks(StageEnum.DONE);

        for (Task task : tasks) {
            long score = calculateScore(task.getPriority(), task.getEndDate());
            task.setScore(score);
        }
        taskService.saveAll(tasks);
    }


    public static int calculateScore(PriorityEnum priorityEnum, LocalDate endDate) {
        long daysUntilDelivery = LocalDate.now().until(endDate, ChronoUnit.DAYS);
        return getFinalScore(priorityEnum, daysUntilDelivery);
    }

    private static int getFinalScore(PriorityEnum priorityEnum, long daysUntilDelivery) {
        int priorityScore = switch (priorityEnum) {
            case LOW -> 0;
            case MEDIUM -> 50;
            case HIGH -> 100;
        };

        int dateScore = (int) Math.min(100, (daysUntilDelivery * 100 / 30));
        return Math.min(100, priorityScore + dateScore);
    }
}
