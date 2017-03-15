package org.jenkinsci.plugins.eventstore.listeners;

import hudson.Extension;
import hudson.model.AbstractBuild;
import hudson.model.BuildListener;
import hudson.tasks.BuildStep;
import org.jenkinsci.plugins.eventstore.EventstoreConfiguration;

@Extension
public final class BuildStepListener extends hudson.model.BuildStepListener {

    @Override
    public void started(AbstractBuild abstractBuild, BuildStep buildStep, BuildListener buildListener) {
        emit("BuildStepListener.started");
    }

    @Override
    public void finished(AbstractBuild abstractBuild, BuildStep buildStep, BuildListener buildListener, boolean b) {
        emit("BuildStepListener.finished");
    }

    private void emit(String event) {
        EventstoreConfiguration.getPublisher().send(event);
    }
    
}
