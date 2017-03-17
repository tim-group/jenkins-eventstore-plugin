package org.jenkinsci.plugins.eventstore.events.buildstep;

import org.jenkinsci.plugins.eventstore.events.Event;

public abstract class BuildEvent implements Event {
    public final String jobName;
    public final int buildNumber;

    public BuildEvent(String jobName, int buildNumber) {
        this.jobName = jobName;
        this.buildNumber = buildNumber;
    }
}
