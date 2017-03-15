package org.jenkinsci.plugins.eventstore.events;

public class BuildEventHappened implements Event {

    private final String message;

    public BuildEventHappened(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "BuildEventHappened{" +
                "message='" + message + '\'' +
                '}';
    }
}
