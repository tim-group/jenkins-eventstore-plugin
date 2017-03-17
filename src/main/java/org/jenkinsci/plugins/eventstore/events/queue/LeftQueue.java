package org.jenkinsci.plugins.eventstore.events.queue;

public final class LeftQueue extends QueueEvent {

    public final Integer buildNumber;
    public final boolean cancelled;

    public LeftQueue(long queueId, String jobName, Integer buildNumber, long inQueueSinceMillis, boolean cancelled) {
        super(queueId, jobName, inQueueSinceMillis);
        this.buildNumber = buildNumber;
        this.cancelled = cancelled;
    }

}