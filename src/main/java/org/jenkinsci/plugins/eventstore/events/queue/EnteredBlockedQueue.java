package org.jenkinsci.plugins.eventstore.events.queue;

import org.jenkinsci.plugins.eventstore.events.Event;

import static org.jenkinsci.plugins.eventstore.DateUtil.epochToIsoDateString;

public final class EnteredBlockedQueue implements Event {

    public final long queueId;
    public final String jobName;
    public final String enteredQueueTimestamp;
    public final String buildableStartTimestamp;

    public EnteredBlockedQueue(long queueId, String jobName, long inQueueSinceMillis, long buildableStartMillis) {
        this.queueId = queueId;
        this.jobName = jobName;
        this.enteredQueueTimestamp = epochToIsoDateString(inQueueSinceMillis);
        this.buildableStartTimestamp = epochToIsoDateString(buildableStartMillis);
    }
}
