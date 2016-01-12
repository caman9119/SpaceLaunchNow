package me.calebjones.spacelaunchnow.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import me.calebjones.spacelaunchnow.R;
import me.calebjones.spacelaunchnow.content.database.SharedPreference;
import me.calebjones.spacelaunchnow.content.models.Launch;
import me.calebjones.spacelaunchnow.content.models.Mission;
import me.calebjones.spacelaunchnow.ui.activity.LaunchDetailActivity;

public class PayloadDetailFragment extends Fragment {

    private SharedPreferences sharedPref;
    private static SharedPreference sharedPreference;
    private Context context;
    public static Launch detailLaunch;
    private TextView payload_description,payload_status,payload_infoButton,payload_wikiButton;

    @Nullable @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view;
        this.sharedPref = PreferenceManager.getDefaultSharedPreferences(getContext());
        this.context = getContext();

        sharedPreference = SharedPreference.getInstance(this.context);

        if (sharedPreference.getNightMode()) {
            view = inflater.inflate(R.layout.dark_launch_payload, container, false);
        } else {
            view = inflater.inflate(R.layout.light_launch_payload, container, false);
        }

        detailLaunch = ((LaunchDetailActivity)getActivity()).getLaunch();

        payload_description = (TextView) view.findViewById(R.id.payload_description);
        payload_status = (TextView) view.findViewById(R.id.payload_status);
        payload_infoButton = (TextView) view.findViewById(R.id.payload_infoButton);
        payload_wikiButton = (TextView) view.findViewById(R.id.payload_wikiButton);

        if (detailLaunch.getMissions().size() > 0){
            final Mission mission = sharedPreference.getMissionByID(detailLaunch.getMissions().get(0).getId());
            payload_status.setText(mission.getName());
            payload_description.setText(mission.getDescription());
            if (mission.getInfoURL().length() > 0){
                payload_infoButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent info = new Intent(Intent.ACTION_VIEW);
                        info.setData(Uri.parse(mission.getInfoURL()));
                        startActivity(info);
                    }
                });
            } else {
                payload_infoButton.setVisibility(View.GONE);
            }
            if (mission.getWikiURL().length() > 0){
                payload_wikiButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent wiki = new Intent(Intent.ACTION_VIEW);
                        wiki.setData(Uri.parse(mission.getWikiURL()));
                        startActivity(wiki);
                    }
                });
            } else {
                payload_wikiButton.setVisibility(View.GONE);
            }
        } else {
            payload_status.setText("Unknown Mission and Payload");
            payload_infoButton.setVisibility(View.GONE);
            payload_wikiButton.setVisibility(View.GONE);
        }

        return view;
    }

    @Override public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    public static Fragment newInstance() {
        return new PayloadDetailFragment();
    }

}