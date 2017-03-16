package org.jenkinsci.plugins.eventstore.events;

public final class StreamId {
    public final String category;
    public final String id;

    public StreamId(String category, String id) {
        this.category = category;
        this.id = id;
    }

    public String toSuffix() {
        return category + "-" + id;
    }
}
