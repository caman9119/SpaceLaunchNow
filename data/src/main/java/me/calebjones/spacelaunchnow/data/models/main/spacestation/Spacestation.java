package me.calebjones.spacelaunchnow.data.models.main.spacestation;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import me.calebjones.spacelaunchnow.data.models.main.Agency;
import me.calebjones.spacelaunchnow.data.models.main.spacecraft.SpacecraftStage;

public class Spacestation extends RealmObject {

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
    @SerializedName("status")
    @Expose
    public SpacestationStatus status;
    @SerializedName("founded")
    @Expose
    public String founded;
    @SerializedName("description")
    @Expose
    public String description;
    @SerializedName("orbit")
    @Expose
    public String orbit;
    @SerializedName("onboard_crew")
    @Expose
    public Integer onboardCrew;
    @SerializedName("owners")
    @Expose
    public RealmList<Agency> owners = null;
    @SerializedName("docked_vehicles")
    @Expose
    public RealmList<SpacecraftStage> dockedVehicles = null;
    @SerializedName("active_expeditions")
    @Expose
    public RealmList<Expedition> activeExpeditions = null;

    private Date lastUpdate;

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

    public SpacestationStatus getStatus() {
        return status;
    }

    public void setStatus(SpacestationStatus status) {
        this.status = status;
    }

    public String getFounded() {
        return founded;
    }

    public void setFounded(String founded) {
        this.founded = founded;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOrbit() {
        return orbit;
    }

    public void setOrbit(String orbit) {
        this.orbit = orbit;
    }

    public Integer getOnboardCrew() {
        return onboardCrew;
    }

    public void setOnboardCrew(Integer onboardCrew) {
        this.onboardCrew = onboardCrew;
    }

    public RealmList<Agency> getOwners() {
        return owners;
    }

    public void setOwners(RealmList<Agency> owners) {
        this.owners = owners;
    }

    public RealmList<SpacecraftStage> getDockedVehicles() {
        return dockedVehicles;
    }

    public void setDockedVehicles(RealmList<SpacecraftStage> dockedVehicles) {
        this.dockedVehicles = dockedVehicles;
    }

    public RealmList<Expedition> getActiveExpeditions() {
        return activeExpeditions;
    }

    public void setActiveExpeditions(RealmList<Expedition> activeExpeditions) {
        this.activeExpeditions = activeExpeditions;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }
}
