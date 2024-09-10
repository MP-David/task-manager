package com.david.task_manager.service;

import com.david.task_manager.domain.ENUMS.Priority;
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

    private final TaskRepository taskRepository;

    public void setTaskRepository() {
        List<Task> tasks = taskRepository.findValidTasks(StageEnum.DONE);

        for (Task task : tasks) {
            long score = calculateScore(task.getPriority(), task.getEndDate());
            task.setScore(score);
        }
        taskRepository.saveAll(tasks);
    }


    public static int calculateScore(Priority priority, LocalDate endDate) {
        long daysUntilDelivery = LocalDate.now().until(endDate, ChronoUnit.DAYS);
        int finalScore = getFinalScore(priority, daysUntilDelivery);

        return finalScore;
    }

    private static int getFinalScore(Priority priority, long daysUntilDelivery) {
        int priorityScore;
        switch (priority) {
            case LOW:
                priorityScore = 0;
                break;
            case MEDIUM:
                priorityScore = 50;
                break;
            case HIGH:
                priorityScore = 100;
                break;
            default:
                throw new IllegalArgumentException("Prioridade desconhecida");
        }

        int dateScore = (int) Math.min(100, (daysUntilDelivery * 100 / 30));
        int finalScore = Math.min(100, priorityScore + dateScore);
        return finalScore;
    }
}
