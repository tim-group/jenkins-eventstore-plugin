package org.jenkinsci.plugins.eventstore;

import hudson.Extension;
import jenkins.model.GlobalConfiguration;
import net.sf.json.JSONObject;
import org.jenkinsci.plugins.eventstore.events.Event;
import org.jenkinsci.plugins.eventstore.events.StreamId;
import org.jenkinsci.plugins.eventstore.publishing.EventstorePublisher;
import org.kohsuke.stapler.StaplerRequest;

@Extension
public final class EventstoreConfiguration extends GlobalConfiguration {

    private String eventstoreUrl;

    private EventstorePublisher publisher;

    public EventstoreConfiguration() {
        load();
        createPublisher();
    }

    @Override
    public boolean configure(StaplerRequest req, JSONObject json) throws FormException {
        eventstoreUrl = json.getString("eventstoreUrl");
        saveAndCreatePublisher();
        return true;
    }

    public String getEventstoreUrl() {
        return eventstoreUrl;
    }

    public void setEventstoreUrl(String eventstoreUrl) {
        this.eventstoreUrl = eventstoreUrl;
        saveAndCreatePublisher();
    }

    private static EventstoreConfiguration get() {
        return (EventstoreConfiguration)GlobalConfiguration.all().get(EventstoreConfiguration.class);
    }

    private void saveAndCreatePublisher() {
        save();
        createPublisher();
    }

    private void createPublisher() {
        if (isValid(eventstoreUrl)) {
            this.publisher = new EventstorePublisher(eventstoreUrl);
        } else {
            this.publisher = null;
        }
    }

    private static boolean isValid(String url) {
        return url != null;
    }

    public static void send(Event event) {
        EventstorePublisher publisher = get().publisher;
        if (publisher != null) {
            publisher.send(event.streamId(), event);
        }
    }
}
