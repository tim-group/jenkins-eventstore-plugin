package org.jenkinsci.plugins.eventstore.publishing;

import net.sf.json.JSONArray;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpConnectionManager;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.httpclient.params.HttpClientParams;
import org.jenkinsci.plugins.eventstore.events.Event;
import org.jenkinsci.plugins.eventstore.events.StreamId;

import java.io.IOException;
import java.util.Arrays;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class EventstorePublisher {
    private final static Logger LOG = Logger.getLogger(EventstorePublisher.class.getName());
    private static final String PATH_PREFIX = "/streams/";

    private final String uriPrefix;
    private final HttpClient httpClient;

    public EventstorePublisher(String eventstoreUrl) {
        this.uriPrefix = eventstoreUrl + PATH_PREFIX;
        LOG.info(String.format("Connecting to eventstore at %s with category %s", eventstoreUrl, "jenkins"));
        HttpClientParams params = new HttpClientParams();
        params.setConnectionManagerTimeout(1000L);
        httpClient = new HttpClient(params);
    }

    public void send(StreamId streamId, Event event) {
        try {
            PostMethod post = new PostMethod(uriPrefix + streamId.toSuffix());
            EventstoreEvent eventstoreEvent = new EventstoreEvent(UUID.randomUUID().toString(), event.getClass().getSimpleName(), event);

            String payload = JSONArray.fromObject(new EventstoreEvent[] {eventstoreEvent}).toString();
            post.setRequestEntity(new StringRequestEntity(payload, "application/vnd.eventstore.events+json", "UTF-8"));
            int responseCode = httpClient.executeMethod(post);

            if (responseCode >= 400) {
                LOG.log(Level.SEVERE, responseCode + " " + Arrays.deepToString(post.getResponseHeaders()));
            }
        } catch (Exception e) {
            LOG.log(Level.SEVERE, "Unable to send to Event Store", e);
        }
    }
}
