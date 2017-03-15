package org.jenkinsci.plugins.eventstore.events;

public class QueueEventHappened implements Event {

    private final String message;

    public QueueEventHappened(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "QueueEventHappened{" +
                "message='" + message + '\'' +
                '}';
    }
}
