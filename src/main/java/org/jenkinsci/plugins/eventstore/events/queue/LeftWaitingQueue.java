package org.jenkinsci.plugins.eventstore.events.queue;

import org.jenkinsci.plugins.eventstore.events.Event;

import static org.jenkinsci.plugins.eventstore.DateUtil.epochToIsoDateString;

public final class LeftWaitingQueue implements Event {

    public final long queueId;
    public final String jobName;
    public final String enteredQueueTimestamp;
    public final String effectiveTimestamp;

    public LeftWaitingQueue(long queueId, String jobName, long inQueueSinceMillis, long timeInMillis) {
        this.queueId = queueId;
        this.jobName = jobName;
        this.enteredQueueTimestamp = epochToIsoDateString(inQueueSinceMillis);
        this.effectiveTimestamp = epochToIsoDateString(timeInMillis);
    }

}
