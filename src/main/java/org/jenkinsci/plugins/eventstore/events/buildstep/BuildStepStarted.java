package org.jenkinsci.plugins.eventstore.events.buildstep;

import org.jenkinsci.plugins.eventstore.events.Event;

import static org.jenkinsci.plugins.eventstore.DateUtil.epochToIsoDateString;

public final class BuildStepStarted implements Event {

    public final String jobName;
    public final int buildNumber;
    public final String effectiveTimestamp;
    public final String startTimestamp;
    public final String stepType;

    public BuildStepStarted(String jobName, int buildNumber, long timeInMillis, long startTimeInMillis, String stepType) {
        this.jobName = jobName;
        this.buildNumber = buildNumber;
        this.effectiveTimestamp = epochToIsoDateString(timeInMillis);
        this.startTimestamp = epochToIsoDateString(startTimeInMillis);
        this.stepType = stepType;
    }
}
