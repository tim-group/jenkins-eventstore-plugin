package org.jenkinsci.plugins.eventstore.events;

import org.apache.commons.httpclient.URIException;
import org.apache.commons.httpclient.util.URIUtil;

public final class StreamId {
    public final String category;
    public final String id;

    public StreamId(String category, String id) {
        this.category = category;
        this.id = id;
    }

    public String toSuffix() throws URIException {
        return category + "-" + URIUtil.encodePath(id);
    }
}
