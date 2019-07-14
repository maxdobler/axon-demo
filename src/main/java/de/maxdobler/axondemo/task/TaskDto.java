package de.maxdobler.axondemo.task;

import java.util.List;


public class TaskDto {

    public final String id;
    public final List<TimeLog> timeLogs;

    public TaskDto(String id, List<TimeLog> timeLogs) {

        this.id = id;
        this.timeLogs = timeLogs;
    }
}
