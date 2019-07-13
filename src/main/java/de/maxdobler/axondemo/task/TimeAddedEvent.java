package de.maxdobler.axondemo.task;

import org.axonframework.modelling.command.TargetAggregateIdentifier;


public class TimeAddedEvent {

    @TargetAggregateIdentifier
    private final String id;
    private final Integer time;

    public TimeAddedEvent(String id, Integer time) {

        this.id = id;
        this.time = time;
    }

    public String getId() {

        return id;
    }


    public Integer getTime() {

        return time;
    }


    @Override
    public String toString() {

        return "TimeAddedEvent{"
            + "id='" + id + '\''
            + ", time=" + time + '}';
    }
}
