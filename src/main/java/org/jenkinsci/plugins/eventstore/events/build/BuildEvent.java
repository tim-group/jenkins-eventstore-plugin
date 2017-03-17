package org.jenkinsci.plugins.eventstore.events.build;

import org.jenkinsci.plugins.eventstore.HashUtil;
import org.jenkinsci.plugins.eventstore.events.Event;
import org.jenkinsci.plugins.eventstore.events.StreamId;

public abstract class BuildEvent implements Event {
    public final String jobName;
    public final int buildNumber;

    public BuildEvent(String jobName, int buildNumber) {
        this.jobName = jobName;
        this.buildNumber = buildNumber;
    }

    @Override
    public final StreamId streamId() {
        return new StreamId("build", HashUtil.hash(jobName + "-" + buildNumber));
    }
}
