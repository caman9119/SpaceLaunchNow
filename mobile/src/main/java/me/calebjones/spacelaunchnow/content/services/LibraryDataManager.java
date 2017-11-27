package me.calebjones.spacelaunchnow.content.services;

import android.content.Context;

import me.calebjones.spacelaunchnow.content.data.DataClientManager;
import me.calebjones.spacelaunchnow.content.jobs.UpdateJob;
import me.calebjones.spacelaunchnow.utils.Utils;
import timber.log.Timber;

/**
 * This grabs rockets from LaunchLibrary
 */
public class LibraryDataManager extends BaseManager {

    private DataClientManager dataClientManager;

    public LibraryDataManager(Context context) {
        super(context);
        dataClientManager = new DataClientManager(context);
    }

    public void getAllData(){
        if (sharedPref.getBoolean("background", true)) {
            scheduleLaunchUpdates();
        }

        dataClientManager.getUpcomingLaunchesAll();
        dataClientManager.getLaunchesByDate("1950-01-01", Utils.getEndDate(1));
        getAllLibraryData();
    }

    public void getAllLibraryData(){
        listPreference.setLastVehicleUpdate(System.currentTimeMillis());
        dataClientManager.getAllAgencies();
        dataClientManager.getAllMissions();
        dataClientManager.getAllLocations();
        dataClientManager.getAllPads();
        dataClientManager.getVehicles();
        dataClientManager.getRockets();
        dataClientManager.getRocketFamily();
    }

    public void  getAgencies(){
        dataClientManager.getAllAgencies();
    }

    public void getMissions(){
        dataClientManager.getAllMissions();
    }

    public void getPads(){
        dataClientManager.getAllPads();
    }

    public void getVehicleDetails(){
        listPreference.setLastVehicleUpdate(System.currentTimeMillis());
        dataClientManager.getVehicles();
        dataClientManager.getRockets();
        dataClientManager.getRocketFamily();
    }

    public void getVehicles(){
        dataClientManager.getRockets();
        dataClientManager.getRocketFamily();
    }

    public void syncNotifiers() {
        dataClientManager.getDataSaver().syncNotifiers(mRealm);
    }

    public void updateNextLaunchMini() {
        Timber.v("Sending Update Next Launch intent.");
        dataClientManager.getNextUpcomingLaunchesMini();
    }

    public void updateLaunchById(int id){
        if (id > 0) {
            Timber.v("Updating detailLaunch id: %s", id);
            dataClientManager.getLaunchById(id);
        } else {
            Timber.v("Unable to update launch with id: %s", id);
        }
    }

    public void getUpcomingLaunches(){
        dataClientManager.getUpcomingLaunches();
    }

    public void getAllUpcomingLaunches(){
        if (sharedPref.getBoolean("background", true)) {
            scheduleLaunchUpdates();
        }

        dataClientManager.getUpcomingLaunchesAll();
    }

    public void getPreviousLaunches(String startDate, String endDate){
        if (startDate != null && endDate != null) {
            dataClientManager.getLaunchesByDate(startDate, endDate, 0);
        } else {
            getPreviousLaunches();
        }
    }

    public void getPreviousLaunches(){
        dataClientManager.getLaunchesByDate("1950-01-01", Utils.getEndDate(1), 0);
    }

    public void scheduleLaunchUpdates() {
        Timber.d("scheduleLaunchUpdates");
        UpdateJob.scheduleJob(context);
    }
}
