package org.jenkinsci.plugins.eventstore.listeners;

import hudson.Extension;
import hudson.model.Queue;
import org.jenkinsci.plugins.eventstore.EventstoreConfiguration;
import org.jenkinsci.plugins.eventstore.events.Event;
import org.jenkinsci.plugins.eventstore.events.QueueEventHappened;

@Extension
public final class QueueListener extends hudson.model.queue.QueueListener {

    @Override
    public void onEnterWaiting(Queue.WaitingItem wi) {
        emit(new QueueEventHappened("QueueListener.onEnterWaiting"));
    }

    @Override
    public void onLeaveWaiting(Queue.WaitingItem wi) {
        emit(new QueueEventHappened("QueueListener.onLeaveWaiting"));
    }

    @Override
    public void onEnterBlocked(Queue.BlockedItem bi) {
        emit(new QueueEventHappened("QueueListener.onEnterBlocked"));
    }

    @Override
    public void onLeaveBlocked(Queue.BlockedItem bi) {
        emit(new QueueEventHappened("QueueListener.onLeaveBlocked"));
    }

    @Override
    public void onEnterBuildable(Queue.BuildableItem bi) {
        emit(new QueueEventHappened("QueueListener.onEnterBuildable"));
    }

    @Override
    public void onLeaveBuildable(Queue.BuildableItem bi) {
        emit(new QueueEventHappened("QueueListener.onLeaveBuildable"));
    }

    @Override
    public void onLeft(Queue.LeftItem li) {
        emit(new QueueEventHappened("QueueListener.onLeft"));
    }

    private void emit(Event event) {
        EventstoreConfiguration.getPublisher().send(event);
    }

}
