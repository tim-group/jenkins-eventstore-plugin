package org.jenkinsci.plugins.eventstore.events.queue;

import org.jenkinsci.plugins.eventstore.events.Event;

import static org.jenkinsci.plugins.eventstore.DateUtil.epochToIsoDateString;

public abstract class QueueEvent implements Event {
    public final String queueId;
    public final String jobName;
    public final String enteredQueueTimestamp;

    public QueueEvent(long queueId, String jobName, long inQueueSinceMillis) {
        this.queueId = inQueueSinceMillis + "-" + queueId;
        this.jobName = jobName;
        this.enteredQueueTimestamp = epochToIsoDateString(inQueueSinceMillis);
    }

    @Override
    public final String category() {
        return "queue";
    }
}
