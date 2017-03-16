package org.jenkinsci.plugins.eventstore.events.queue;

import static org.jenkinsci.plugins.eventstore.DateUtil.epochToIsoDateString;

public final class LeftWaitingQueue extends QueueEvent {

    public final String effectiveTimestamp;

    public LeftWaitingQueue(long queueId, String jobName, long inQueueSinceMillis, long timeInMillis) {
        super(queueId, jobName, inQueueSinceMillis);
        this.effectiveTimestamp = epochToIsoDateString(timeInMillis);
    }

}
