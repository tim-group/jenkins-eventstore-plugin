package org.jenkinsci.plugins.eventstore.listeners;

import hudson.Extension;
import hudson.model.Job;
import hudson.model.Queue;
import org.jenkinsci.plugins.eventstore.EventstoreConfiguration;
import org.jenkinsci.plugins.eventstore.events.Event;
import org.jenkinsci.plugins.eventstore.events.build.BuildLeftQueue;
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
             )
        );
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
        if (!li.isCancelled() && li.task instanceof Job) {
            Job job = (Job) li.task;
            int buildNumber = job.getNextBuildNumber();

            LeftQueue leftQueue = new LeftQueue(
                    li.getId(),
                    li.task.getName(),
                    buildNumber,
                    li.getInQueueSince(),
                    li.isCancelled()
            );
            emit(leftQueue);
            emit(new BuildLeftQueue(
                        li.task.getName(),
                        buildNumber,
                        leftQueue.queueId,
                        leftQueue.enteredQueueTimestamp
                ));
        } else {
            emit(new LeftQueue(
                        li.getId(),
                        li.task.getName(),
                        null,
                        li.getInQueueSince(),
                        li.isCancelled()
                ));
        }
    }

    private void emit(Event event) {
        EventstoreConfiguration.send(event);
    }

}