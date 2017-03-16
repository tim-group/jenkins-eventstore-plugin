package org.jenkinsci.plugins.eventstore;

import hudson.Extension;
import jenkins.model.GlobalConfiguration;
import net.sf.json.JSONObject;
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
        this.publisher = new EventstorePublisher(eventstoreUrl);
    }

    public static EventstorePublisher getPublisher() {
        return get().publisher;
    }

}
