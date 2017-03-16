package org.jenkinsci.plugins.eventstore.events.queue;

import org.jenkinsci.plugins.eventstore.events.Event;

import static org.jenkinsci.plugins.eventstore.DateUtil.epochToIsoDateString;

public final class LeftQueue implements Event {

    public final long queueId;
    public final String jobName;
    public final Integer buildNumber;
    public final String enteredQueueTimestamp;

    public LeftQueue(long queueId, String jobName, Integer buildNumber, long inQueueSinceMillis) {
        this.queueId = queueId;
        this.jobName = jobName;
        this.buildNumber = buildNumber;
        this.enteredQueueTimestamp = epochToIsoDateString(inQueueSinceMillis);
    }

}