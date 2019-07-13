package de.maxdobler.axondemo.task;

public class TaskCreatedEvent {

    private final String id;

    public TaskCreatedEvent(String id) {

        this.id = id;
    }

    public String getId() {

        return id;
    }


    @Override
    public String toString() {

        return "TaskCreatedEvent{"
            + "id='" + id + '\'' + '}';
    }
}
