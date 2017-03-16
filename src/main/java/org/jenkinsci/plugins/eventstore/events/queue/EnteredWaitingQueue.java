package org.jenkinsci.plugins.eventstore.events.queue;

import org.jenkinsci.plugins.eventstore.events.Event;

import static org.jenkinsci.plugins.eventstore.DateUtil.epochToIsoDateString;

public final class EnteredWaitingQueue implements Event {

    public final long queueId;
    public final String name;
    public final String enteredQueueTimestamp;
    public final String effectiveTimestamp;

    public EnteredWaitingQueue(long queueId, String name, long inQueueSinceMillis, long timeInMillis) {
        this.queueId = queueId;
        this.name = name;
        this.enteredQueueTimestamp = epochToIsoDateString(inQueueSinceMillis);
        this.effectiveTimestamp = epochToIsoDateString(timeInMillis);
    }
}
