package de.maxdobler.axondemo;

public class TaskCreatedEvent {

    private final String id;

    public TaskCreatedEvent(String id) {

        this.id = id;
    }

    public String getId() {

        return id;
    }
}
