package org.jenkinsci.plugins.eventstore.events.queue;

public final class LeftQueue extends QueueEvent {

    public final Integer buildNumber;

    public LeftQueue(long queueId, String jobName, Integer buildNumber, long inQueueSinceMillis) {
        super(queueId, jobName, inQueueSinceMillis);
        this.buildNumber = buildNumber;
    }

}