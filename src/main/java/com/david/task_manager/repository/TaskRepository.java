package com.david.task_manager.repository;

import com.david.task_manager.domain.ENUMS.StageEnum;
import com.david.task_manager.domain.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findByTitle(String title);

    @Query("SELECT t FROM Task t WHERE t.stage <> :stage AND t.endDate IS NOT NULL AND t.priority IS NOT NULL AND t.endDate > t.initDate")
    List<Task> findValidTasks(@Param("stage") StageEnum stage);

    @Query(value = "SELECT * FROM task t WHERE t.stage <> 'DONE' AND t.end_date IS NOT NULL AND t.priority IS NOT NULL AND t.end_date > t.init_date AND t.end_date BETWEEN CURRENT_DATE AND CURRENT_DATE + INTERVAL 3 DAY", nativeQuery = true)
    List<Task> findTasksEndingInThreeDays();
}
