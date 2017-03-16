package org.jenkinsci.plugins.eventstore.listeners;

import hudson.Extension;
import hudson.model.Job;
import hudson.model.Queue;
import org.jenkinsci.plugins.eventstore.EventstoreConfiguration;
import org.jenkinsci.plugins.eventstore.events.StreamId;
import org.jenkinsci.plugins.eventstore.events.queue.*;

@Extension
public final class QueueListener extends hudson.model.queue.QueueListener {

    @Override
    public void onEnterWaiting(Queue.WaitingItem wi) {
        emit(wi,
             new EnteredWaitingQueue(
                 wi.getId(),
                 wi.task.getName(),
                 wi.getInQueueSince(),
                 wi.timestamp.getTimeInMillis()
             )
        );
    }

    @Override
    public void onLeaveWaiting(Queue.WaitingItem wi) {
        emit(wi,
            new LeftWaitingQueue(
                wi.getId(),
                wi.task.getName(),
                wi.getInQueueSince(),
                wi.timestamp.getTimeInMillis()
        ));
    }

    @Override
    public void onEnterBlocked(Queue.BlockedItem bi) {
        emit(bi,
            new EnteredBlockedQueue(
                bi.getId(),
                bi.task.getName(),
                bi.getInQueueSince(),
                bi.buildableStartMilliseconds
        ));
    }

    @Override
    public void onLeaveBlocked(Queue.BlockedItem bi) {
        emit(bi,
            new LeftBlockedQueue(
                bi.getId(),
                bi.task.getName(),
                bi.getInQueueSince(),
                bi.buildableStartMilliseconds
        ));
    }

    @Override
    public void onEnterBuildable(Queue.BuildableItem bi) {
        emit(bi,
            new EnteredBuildableQueue(
                bi.getId(),
                bi.task.getName(),
                bi.getInQueueSince(),
                bi.buildableStartMilliseconds
        ));
    }

    @Override
    public void onLeaveBuildable(Queue.BuildableItem bi) {
        emit(bi,
            new LeftBuildableQueue(
                bi.getId(),
                bi.task.getName(),
                bi.getInQueueSince(),
                bi.buildableStartMilliseconds
        ));
    }

    @Override
    public void onLeft(Queue.LeftItem li) {
        if (li.task instanceof Job) {
            Job job = (Job) li.task;
            int buildNumber = job.getNextBuildNumber();
            emit(li,
                    new LeftQueue(
                            li.getId(),
                            li.task.getName(),
                            buildNumber,
                            li.getInQueueSince()
                    ));
            emit(job,
                new LeftQueue(
                    li.getId(),
                    li.task.getName(),
                    buildNumber,
                    li.getInQueueSince()
            ));
        } else {
            emit(li,
                    new LeftQueue(
                            li.getId(),
                            li.task.getName(),
                            null,
                            li.getInQueueSince()
                    ));
        }
    }

    private void emit(Queue.Item item, QueueEvent event) {
        EventstoreConfiguration.send(new StreamId("queue", item.task.getName() + "-" + event.queueId), event);
    }

    private void emit(Job job, QueueEvent event) {
        EventstoreConfiguration.send(new StreamId("build", job.getName() + "-" + job.getNextBuildNumber()), event);
    }

}