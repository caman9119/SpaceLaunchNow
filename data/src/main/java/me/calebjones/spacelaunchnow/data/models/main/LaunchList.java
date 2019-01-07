package me.calebjones.spacelaunchnow.data.models.main;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class LaunchList extends RealmObject {

    @PrimaryKey
    @SerializedName("id")
    @Expose
    public Integer id;
    @SerializedName("url")
    @Expose
    public String url;
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("launchStatus")
    @Expose
    public LaunchStatus launchStatus;
    @SerializedName("net")
    @Expose
    public Date net;
    @SerializedName("window_end")
    @Expose
    public Date windowEnd;
    @SerializedName("window_start")
    @Expose
    public Date windowStart;
    @SerializedName("mission")
    @Expose
    public String mission;
    @SerializedName("mission_type")
    @Expose
    public String missionType;
    @SerializedName("pad")
    @Expose
    public String pad;
    @SerializedName("location")
    @Expose
    public String location;
    @SerializedName("landing")
    @Expose
    public String landing;
    @SerializedName("launcher")
    @Expose
    public String launcher;
    @SerializedName("orbit")
    @Expose
    public String orbit;
    @SerializedName("landing_success")
    @Expose
    public Integer landingSuccess;

    public String getOrbit() {
        return orbit;
    }

    public void setOrbit(String orbit) {
        this.orbit = orbit;
    }

    public Integer getLandingSuccess() {
        return landingSuccess;
    }

    public void setLandingSuccess(Integer landingSuccess) {
        this.landingSuccess = landingSuccess;
    }

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LaunchStatus getLaunchStatus() {
        return launchStatus;
    }

    public void setLaunchStatus(LaunchStatus launchStatus) {
        this.launchStatus = launchStatus;
    }

    public Date getNet() {
        return net;
    }

    public void setNet(Date net) {
        this.net = net;
    }

    public Date getWindowEnd() {
        return windowEnd;
    }

    public void setWindowEnd(Date windowEnd) {
        this.windowEnd = windowEnd;
    }

    public Date getWindowStart() {
        return windowStart;
    }

    public void setWindowStart(Date windowStart) {
        this.windowStart = windowStart;
    }

    public String getMission() {
        return mission;
    }

    public void setMission(String mission) {
        this.mission = mission;
    }

    public String getMissionType() {
        return missionType;
    }

    public void setMissionType(String missionType) {
        this.missionType = missionType;
    }

    public String getPad() {
        return pad;
    }

    public void setPad(String pad) {
        this.pad = pad;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLanding() {
        return landing;
    }

    public void setLanding(String landing) {
        this.landing = landing;
    }

    public String getLauncher() {
        return launcher;
    }

    public void setLauncher(String launcher) {
        this.launcher = launcher;
    }
}
