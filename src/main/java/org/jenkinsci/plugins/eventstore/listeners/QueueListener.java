package org.jenkinsci.plugins.eventstore.listeners;

import hudson.Extension;
import hudson.model.Queue;
import org.jenkinsci.plugins.eventstore.EventstoreConfiguration;

@Extension
public final class QueueListener extends hudson.model.queue.QueueListener {

    @Override
    public void onEnterWaiting(Queue.WaitingItem wi) {
        emit("QueueListener.onEnterWaiting");
    }

    @Override
    public void onLeaveWaiting(Queue.WaitingItem wi) {
        emit("QueueListener.onLeaveWaiting");
    }

    @Override
    public void onEnterBlocked(Queue.BlockedItem bi) {
        emit("QueueListener.onEnterBlocked");
    }

    @Override
    public void onLeaveBlocked(Queue.BlockedItem bi) {
        emit("QueueListener.onLeaveBlocked");
    }

    @Override
    public void onEnterBuildable(Queue.BuildableItem bi) {
        emit("QueueListener.onEnterBuildable");
    }

    @Override
    public void onLeaveBuildable(Queue.BuildableItem bi) {
        emit("QueueListener.onLeaveBuildable");
    }

    @Override
    public void onLeft(Queue.LeftItem li) {
        emit("QueueListener.onLeft");
    }

    private void emit(String event) {
        EventstoreConfiguration.getPublisher().send(event);
    }

}
