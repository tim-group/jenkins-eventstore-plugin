package org.jenkinsci.plugins.eventstore.listeners;

import hudson.Extension;
import hudson.model.Queue;
import org.jenkinsci.plugins.eventstore.DateUtil;
import org.jenkinsci.plugins.eventstore.EventstoreConfiguration;
import org.jenkinsci.plugins.eventstore.events.Event;
import org.jenkinsci.plugins.eventstore.events.queue.*;

@Extension
public final class QueueListener extends hudson.model.queue.QueueListener {

    @Override
    public void onEnterWaiting(Queue.WaitingItem wi) {
        emit(new EnteredWaitingQueue(
                wi.getId(),
                wi.task.getName(),
                wi.getInQueueSince(),
                wi.timestamp.getTimeInMillis()
        ));
    }

    @Override
    public void onLeaveWaiting(Queue.WaitingItem wi) {
        emit(new LeftWaitingQueue(
                wi.getId(),
                wi.task.getName(),
                wi.getInQueueSince(),
                wi.timestamp.getTimeInMillis()
        ));
    }

    @Override
    public void onEnterBlocked(Queue.BlockedItem bi) {
        emit(new EnteredBlockedQueue(
                bi.getId(),
                bi.task.getName(),
                bi.getInQueueSince(),
                bi.buildableStartMilliseconds
        ));
    }

    @Override
    public void onLeaveBlocked(Queue.BlockedItem bi) {
        emit(new LeftBlockedQueue(
                bi.getId(),
                bi.task.getName(),
                bi.getInQueueSince(),
                bi.buildableStartMilliseconds
        ));
    }

    @Override
    public void onEnterBuildable(Queue.BuildableItem bi) {
        emit(new EnteredBuildableQueue(
                bi.getId(),
                bi.task.getName(),
                bi.getInQueueSince(),
                bi.buildableStartMilliseconds
        ));
    }

    @Override
    public void onLeaveBuildable(Queue.BuildableItem bi) {
        emit(new LeftBuildableQueue(
                bi.getId(),
                bi.task.getName(),
                bi.getInQueueSince(),
                bi.buildableStartMilliseconds
        ));
    }

    @Override
    public void onLeft(Queue.LeftItem li) {
        emit(new LeftQueue(
                li.getId(),
                li.task.getName(),
                li.getInQueueSince()
        ));
    }

    private void emit(Event event) {
        EventstoreConfiguration.getPublisher().send(event);
    }

}