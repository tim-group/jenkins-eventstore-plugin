package org.jenkinsci.plugins.eventstore.events.queue;

import org.jenkinsci.plugins.eventstore.events.Event;

import static org.jenkinsci.plugins.eventstore.DateUtil.epochToIsoDateString;

public final class EnteredBuildableQueue implements Event {

    public final long id;
    public final String name;
    public final String enteredQueueTimestamp;
    public final String buildableStartTimestamp;

    public EnteredBuildableQueue(long id, String name, long inQueueSinceMillis, long buildableStartMillis) {
        this.id = id;
        this.name = name;
        this.enteredQueueTimestamp = epochToIsoDateString(inQueueSinceMillis);
        this.buildableStartTimestamp = epochToIsoDateString(buildableStartMillis);
    }

}
