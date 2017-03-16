package org.jenkinsci.plugins.eventstore.events.queue;

import static org.jenkinsci.plugins.eventstore.DateUtil.epochToIsoDateString;

public final class EnteredWaitingQueue extends QueueEvent {

    public final String effectiveTimestamp;

    public EnteredWaitingQueue(long queueId, String jobName, long inQueueSinceMillis, long timeInMillis) {
        super(queueId, jobName, inQueueSinceMillis);
        this.effectiveTimestamp = epochToIsoDateString(timeInMillis);
    }
}
