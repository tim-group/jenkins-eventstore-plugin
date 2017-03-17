package org.jenkinsci.plugins.eventstore.listeners;

import hudson.Extension;
import hudson.model.AbstractBuild;
import hudson.model.BuildListener;
import hudson.tasks.BuildStep;
import org.jenkinsci.plugins.eventstore.EventstoreConfiguration;
import org.jenkinsci.plugins.eventstore.events.Event;
import org.jenkinsci.plugins.eventstore.events.StreamId;
import org.jenkinsci.plugins.eventstore.events.build.BuildStepFinished;
import org.jenkinsci.plugins.eventstore.events.build.BuildStepStarted;

@Extension
public final class BuildStepListener extends hudson.model.BuildStepListener {

    @Override
    public void started(AbstractBuild abstractBuild, BuildStep buildStep, BuildListener buildListener) {
        emit(abstractBuild,
            new BuildStepStarted(
                abstractBuild.getProject().getFullName(),
                abstractBuild.getNumber(),
                abstractBuild.getTimeInMillis(),
                abstractBuild.getStartTimeInMillis(),
                buildStep.getClass().getSimpleName()
            )
        );
    }

    @Override
    public void finished(AbstractBuild abstractBuild, BuildStep buildStep, BuildListener buildListener, boolean canContinue) {
        emit(abstractBuild,
            new BuildStepFinished(
                abstractBuild.getProject().getFullName(),
                abstractBuild.getNumber(),
                abstractBuild.getTimeInMillis(),
                abstractBuild.getStartTimeInMillis(),
                buildStep.getClass().getSimpleName(),
                canContinue
            )
        );
    }

    private void emit(AbstractBuild build, Event event) {
        EventstoreConfiguration.send(new StreamId(event.category(), build.getProject().getFullName() + "-" + build.getNumber()), event);
    }
    
}
