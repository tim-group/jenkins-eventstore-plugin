package org.jenkinsci.plugins.eventstore.events.queue;

import static org.jenkinsci.plugins.eventstore.DateUtil.epochToIsoDateString;

public final class EnteredBuildableQueue extends QueueEvent {

    public final String buildableStartTimestamp;

    public EnteredBuildableQueue(long queueId, String jobName, long inQueueSinceMillis, long buildableStartMillis) {
        super(queueId, jobName, inQueueSinceMillis);
        this.buildableStartTimestamp = epochToIsoDateString(buildableStartMillis);
    }

}
