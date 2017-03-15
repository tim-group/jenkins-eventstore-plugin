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

    public EventstoreConfiguration() {
        this.load();
    }

    public boolean configure(StaplerRequest req, JSONObject json) throws FormException {
        eventstoreHost = json.getString("eventstoreHost");
        eventstorePort = json.getInt("eventstorePort");
        save();
        return true;
    }

    public String getEventstoreHost() {
        return eventstoreHost;
    }

    public void setEventstoreHost(String eventstoreHost) {
        this.eventstoreHost = eventstoreHost;
        this.save();
    }

    public int getEventstorePort() {
        return eventstorePort;
    }

    public void setEventstorePort(int eventstorePort) {
        this.eventstorePort = eventstorePort;
        this.save();
    }

    public static EventstoreConfiguration get() {
        return (EventstoreConfiguration)GlobalConfiguration.all().get(EventstoreConfiguration.class);
    }

    public static EventstorePublisher getPublisher() {
        EventstoreConfiguration eventstoreConfiguration = get();
        return new EventstorePublisher(eventstoreConfiguration.eventstoreHost, eventstoreConfiguration.eventstorePort);
    }

}
