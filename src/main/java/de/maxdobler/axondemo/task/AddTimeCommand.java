package de.maxdobler.axondemo.task;

import org.axonframework.modelling.command.TargetAggregateIdentifier;


public class AddTimeCommand {

    @TargetAggregateIdentifier
    private final String id;
    private final Integer time;

    public AddTimeCommand(String id, Integer time) {

        this.id = id;
        this.time = time;
    }

    public String getId() {

        return id;
    }


    public Integer getTime() {

        return time;
    }
}
