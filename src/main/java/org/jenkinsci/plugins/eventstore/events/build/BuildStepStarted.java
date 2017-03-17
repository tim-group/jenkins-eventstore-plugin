package org.jenkinsci.plugins.eventstore.events.build;

import static org.jenkinsci.plugins.eventstore.DateUtil.epochToIsoDateString;

public final class BuildStepStarted extends BuildEvent {

    public final String effectiveTimestamp;
    public final String startTimestamp;
    public final String stepType;

    public BuildStepStarted(String jobName, int buildNumber, long timeInMillis, long startTimeInMillis, String stepType) {
        super(jobName, buildNumber);
        this.effectiveTimestamp = epochToIsoDateString(timeInMillis);
        this.startTimestamp = epochToIsoDateString(startTimeInMillis);
        this.stepType = stepType;
    }
}
