package org.jenkinsci.plugins.eventstore.events.buildstep;

import org.jenkinsci.plugins.eventstore.events.Event;

import static org.jenkinsci.plugins.eventstore.DateUtil.epochToIsoDateString;

public final class BuildStepFinished implements Event {

    public final String jobName;
    public final int buildNumber;
    public final String effectiveTimestamp;
    public final String startTimestamp;
    public final String stepType;
    public final boolean canContinue;

    public BuildStepFinished(String jobName, int buildNumber, long timeInMillis, long startTimeInMillis, String stepType, boolean canContinue) {
        this.jobName = jobName;
        this.buildNumber = buildNumber;
        this.effectiveTimestamp = epochToIsoDateString(timeInMillis);
        this.startTimestamp = epochToIsoDateString(startTimeInMillis);
        this.canContinue = canContinue;
        this.stepType = stepType;
    }
}
