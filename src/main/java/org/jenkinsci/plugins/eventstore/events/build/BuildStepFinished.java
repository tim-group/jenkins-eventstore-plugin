package org.jenkinsci.plugins.eventstore.events.build;

import static org.jenkinsci.plugins.eventstore.DateUtil.epochToIsoDateString;

public final class BuildStepFinished extends BuildEvent {

    public final String effectiveTimestamp;
    public final String startTimestamp;
    public final String stepType;
    public final boolean canContinue;

    public BuildStepFinished(String jobName, int buildNumber, long timeInMillis, long startTimeInMillis, String stepType, boolean canContinue) {
        super(jobName, buildNumber);
        this.effectiveTimestamp = epochToIsoDateString(timeInMillis);
        this.startTimestamp = epochToIsoDateString(startTimeInMillis);
        this.canContinue = canContinue;
        this.stepType = stepType;
    }
}
