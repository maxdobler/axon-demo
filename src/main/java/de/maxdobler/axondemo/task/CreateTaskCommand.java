package de.maxdobler.axondemo.task;

import org.axonframework.modelling.command.TargetAggregateIdentifier;


public class CreateTaskCommand {

    @TargetAggregateIdentifier
    private String taskId;

    public CreateTaskCommand(String taskId) {

        this.taskId = taskId;
    }

    public String getTaskId() {

        return taskId;
    }
}
