package de.maxdobler.axondemo.task;

import org.axonframework.commandhandling.CommandHandler;

import org.axonframework.eventsourcing.EventSourcingHandler;

import org.axonframework.modelling.command.AggregateIdentifier;

import org.axonframework.spring.stereotype.Aggregate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;

import java.util.ArrayList;
import java.util.List;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

import static java.util.Objects.requireNonNull;


@Aggregate
public class TaskAggregate {

    private static final Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @AggregateIdentifier
    private String id;

    private List<TimeLog> timelogs;

    @CommandHandler
    TaskAggregate(CreateTaskCommand cmd) {

        logCommand(cmd);

        apply(new TaskCreatedEvent(cmd.getTaskId()));
    }


    public TaskAggregate() {
    }

    @CommandHandler
    void handle(AddTimeCommand command) {

        logCommand(command);

        requireNonNull(command.getTime());

        if (command.getTime() <= 0)
            throw new IllegalArgumentException(command.getClass().getSimpleName() + ": Time must be > 0");

        apply(new TimeAddedEvent(command.getId(), command.getTime()));
    }


    @EventSourcingHandler
    void on(TaskCreatedEvent event) {

        logEvent(event);

        id = event.getId();
        timelogs = new ArrayList<>();
    }


    @EventSourcingHandler
    void on(TimeAddedEvent event) {

        logEvent(event);

        timelogs.add(new TimeLog(event.getTime()));
    }


    private void logCommand(Object cmd) {

        LOG.info("[Command] {}: {}", cmd.getClass().getSimpleName(), cmd);
    }


    private void logEvent(Object event) {

        LOG.info("[Event] {}: {}", event.getClass().getSimpleName(), event);
    }


    public String getId() {

        return id;
    }
}
