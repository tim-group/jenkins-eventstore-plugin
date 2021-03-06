package org.jenkinsci.plugins.eventstore.listeners;

import hudson.Extension;
import hudson.model.AbstractBuild;
import hudson.model.BuildListener;
import hudson.tasks.BuildStep;
import org.jenkinsci.plugins.eventstore.EventstoreConfiguration;
import org.jenkinsci.plugins.eventstore.events.Event;
import org.jenkinsci.plugins.eventstore.events.build.BuildStepFinished;
import org.jenkinsci.plugins.eventstore.events.build.BuildStepStarted;

@Extension
public final class BuildStepListener extends hudson.model.BuildStepListener {

    @Override
    public void started(AbstractBuild abstractBuild, BuildStep buildStep, BuildListener buildListener) {
        emit(new BuildStepStarted(
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
        emit(new BuildStepFinished(
                abstractBuild.getProject().getFullName(),
                abstractBuild.getNumber(),
                abstractBuild.getTimeInMillis(),
                abstractBuild.getStartTimeInMillis(),
                buildStep.getClass().getSimpleName(),
                canContinue
            )
        );
    }

    private void emit(Event event) {
        EventstoreConfiguration.send(event);
    }
    
}
