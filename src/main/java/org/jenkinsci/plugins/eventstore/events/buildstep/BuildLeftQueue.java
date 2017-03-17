package org.jenkinsci.plugins.eventstore.events.buildstep;

public final class BuildLeftQueue extends BuildEvent {

    public final String queueId;
    public final String enteredQueueTimestamp;

    public BuildLeftQueue(String jobName, int buildNumber, String queueId, String enteredQueueTimestamp) {
        super(jobName, buildNumber);
        this.queueId = queueId;
        this.enteredQueueTimestamp = enteredQueueTimestamp;
    }
}
