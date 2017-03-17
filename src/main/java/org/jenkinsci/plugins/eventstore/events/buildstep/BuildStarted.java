package org.jenkinsci.plugins.eventstore.events.buildstep;

import org.jenkinsci.plugins.eventstore.events.Event;

public final class BuildStarted implements Event {

    public final String jobName;
    public final int buildNumber;
    public final String queueId;
    public final String enteredQueueTimestamp;

    public BuildStarted(String jobName, int buildNumber, String queueId, String enteredQueueTimestamp) {
        this.jobName = jobName;
        this.buildNumber = buildNumber;
        this.queueId = queueId;
        this.enteredQueueTimestamp = enteredQueueTimestamp;
    }
}
