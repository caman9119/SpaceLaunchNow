package me.calebjones.spacelaunchnow.data.models.main.spacecraft;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

import io.realm.RealmList;
import io.realm.RealmObject;
import me.calebjones.spacelaunchnow.data.models.main.Launch;
import me.calebjones.spacelaunchnow.data.models.main.astronaut.Astronaut;
import me.calebjones.spacelaunchnow.data.models.main.spacestation.DockingEvent;

public class SpacecraftStage extends RealmObject {

    @SerializedName("id")
    @Expose
    public Integer id;
    @SerializedName("url")
    @Expose
    public String url;
    @SerializedName("splashdown")
    @Expose
    public Date splashdown;
    @SerializedName("destination")
    @Expose
    public String destination;
    @SerializedName("launch_crew")
    @Expose
    public RealmList<Astronaut> launchCrew = null;
    @SerializedName("onboard_crew")
    @Expose
    public RealmList<Astronaut> onboardCrew = null;
    @SerializedName("landing_crew")
    @Expose
    public RealmList<Astronaut> landingCrew = null;
    @SerializedName("spacecraft")
    @Expose
    public Spacecraft spacecraft;
    @SerializedName("launch")
    @Expose
    public Launch launch;
    @SerializedName("docking_events")
    @Expose
    public RealmList<DockingEvent> dockingEvents = null;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Date getSplashdown() {
        return splashdown;
    }

    public void setSplashdown(Date splashdown) {
        this.splashdown = splashdown;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public RealmList<Astronaut> getLaunchCrew() {
        return launchCrew;
    }

    public void setLaunchCrew(RealmList<Astronaut> launchCrew) {
        this.launchCrew = launchCrew;
    }

    public RealmList<Astronaut> getOnboardCrew() {
        return onboardCrew;
    }

    public void setOnboardCrew(RealmList<Astronaut> onboardCrew) {
        this.onboardCrew = onboardCrew;
    }

    public RealmList<Astronaut> getLandingCrew() {
        return landingCrew;
    }

    public void setLandingCrew(RealmList<Astronaut> landingCrew) {
        this.landingCrew = landingCrew;
    }

    public Spacecraft getSpacecraft() {
        return spacecraft;
    }

    public void setSpacecraft(Spacecraft spacecraft) {
        this.spacecraft = spacecraft;
    }

    public Launch getLaunch() {
        return launch;
    }

    public void setLaunch(Launch launch) {
        this.launch = launch;
    }

    public RealmList<DockingEvent> getDockingEvents() {
        return dockingEvents;
    }

    public void setDockingEvents(RealmList<DockingEvent> dockingEvents) {
        this.dockingEvents = dockingEvents;
    }
}
