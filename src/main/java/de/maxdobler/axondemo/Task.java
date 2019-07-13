package de.maxdobler.axondemo;

import org.axonframework.commandhandling.CommandHandler;

import org.axonframework.eventsourcing.EventSourcingHandler;

import org.axonframework.modelling.command.AggregateIdentifier;

import org.axonframework.spring.stereotype.Aggregate;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;


@Aggregate
public class Task {

    @AggregateIdentifier
    private String id;

    @CommandHandler
    Task(CreateTaskCommand cmd) {

        apply(new TaskCreatedEvent(cmd.getTaskId()));
    }


    public Task() {
    }

    @EventSourcingHandler
    void on(TaskCreatedEvent event) {

        id = event.getId();
    }
}
