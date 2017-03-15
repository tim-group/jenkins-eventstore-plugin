package org.jenkinsci.plugins.eventstore.publishing;

import java.util.logging.Logger;

public final class EventstorePublisher {
    private final static Logger LOG = Logger.getLogger(EventstorePublisher.class.getName());

    private final String eventstoreHost;
    private final int eventstorePort;

    public EventstorePublisher(String eventstoreHost, int eventstorePort) {
        this.eventstoreHost = eventstoreHost;
        this.eventstorePort = eventstorePort;
        LOG.info(String.format("Connecting to eventstore at %s:%s", eventstoreHost, eventstorePort));
    }

    public void send(String event) {
        System.out.println(eventstoreHost + ":" + eventstorePort + " => " + event);
    }
}
