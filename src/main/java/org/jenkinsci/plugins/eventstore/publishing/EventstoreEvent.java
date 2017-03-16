package org.jenkinsci.plugins.eventstore.publishing;

import org.jenkinsci.plugins.eventstore.events.Event;

public final class EventstoreEvent {

    public String eventId;
    public String eventType;
    public Event data;

    public EventstoreEvent(String eventId, String eventType, Event data) {
        this.eventId = eventId;
        this.eventType = eventType;
        this.data = data;
    }
}
