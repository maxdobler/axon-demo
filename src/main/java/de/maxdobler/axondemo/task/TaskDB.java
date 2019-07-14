package de.maxdobler.axondemo.task;

import org.axonframework.eventhandling.EventHandler;

import org.axonframework.queryhandling.QueryHandler;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static java.util.Collections.emptyList;


@Component
public class TaskDB {

    private final Map<String, TaskDto> tasks = new ConcurrentHashMap<>();

    @EventHandler
    void on(TaskCreatedEvent event) {

        var taskId = event.getId();
        tasks.put(taskId, new TaskDto(taskId, emptyList()));
    }


    @EventHandler
    void on(TimeAddedEvent event) {

        var taskId = event.getId();
        var task = tasks.get(event.getId());

        var timeLogs = new ArrayList<>(task.timeLogs);
        timeLogs.add(new TimeLog(event.getTime()));
        tasks.put(taskId, new TaskDto(taskId, timeLogs));
    }


    @QueryHandler
    TaskDto getTask(GetTaskQuery query) {

        return tasks.get(query.taskId);
    }
}
