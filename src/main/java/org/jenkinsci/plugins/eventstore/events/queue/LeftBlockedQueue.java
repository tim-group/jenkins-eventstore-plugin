package org.jenkinsci.plugins.eventstore.events.queue;

import static org.jenkinsci.plugins.eventstore.DateUtil.epochToIsoDateString;

public final class LeftBlockedQueue extends QueueEvent {

    public final String buildableStartTimestamp;

    public LeftBlockedQueue(long queueId, String jobName, long inQueueSinceMillis, long buildableStartMillis) {
        super(queueId, jobName, inQueueSinceMillis);
        this.buildableStartTimestamp = epochToIsoDateString(buildableStartMillis);
    }

}
