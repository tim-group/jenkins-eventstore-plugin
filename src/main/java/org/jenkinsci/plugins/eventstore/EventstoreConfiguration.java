package org.jenkinsci.plugins.eventstore;

import hudson.Extension;
import jenkins.model.GlobalConfiguration;
import net.sf.json.JSONObject;
import org.jenkinsci.plugins.eventstore.publishing.EventstorePublisher;
import org.kohsuke.stapler.StaplerRequest;

@Extension
public final class EventstoreConfiguration extends GlobalConfiguration {

    private String eventstoreHost;
    private int eventstorePort;

    private EventstorePublisher publisher;

    public EventstoreConfiguration() {
        load();
        createPublisher();
    }

    @Override
    public boolean configure(StaplerRequest req, JSONObject json) throws FormException {
        eventstoreHost = json.getString("eventstoreHost");
        eventstorePort = json.getInt("eventstorePort");
        saveAndCreatePublisher();
        return true;
    }

    public String getEventstoreHost() {
        return eventstoreHost;
    }

    public void setEventstoreHost(String eventstoreHost) {
        this.eventstoreHost = eventstoreHost;
        saveAndCreatePublisher();
    }

    public int getEventstorePort() {
        return eventstorePort;
    }

    public void setEventstorePort(int eventstorePort) {
        this.eventstorePort = eventstorePort;
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
        this.publisher = new EventstorePublisher("http://" + eventstoreHost + ":" + eventstorePort);
    }

    public static EventstorePublisher getPublisher() {
        return get().publisher;
    }

}
