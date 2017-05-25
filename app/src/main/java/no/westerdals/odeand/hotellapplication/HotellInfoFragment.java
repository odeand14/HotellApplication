package no.westerdals.odeand.hotellapplication;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;


public class HotellInfoFragment extends Fragment {

    public HotellInfoFragment() {
    }

    private MapView mapView;
    private Button mBtnShowAsStreetView;

    public static HotellInfoFragment newInstance() {

        return new HotellInfoFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_hotell_info, container, false);

        mBtnShowAsStreetView = (Button) view.findViewById(R.id.button_show_street_view);
        mapView = (MapView) view.findViewById(R.id.map_view);

        mapView.onCreate(savedInstanceState);
        mapView.onResume();

        try {
            MapsInitializer.initialize(getActivity().getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }

        mBtnShowAsStreetView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ShowStreetViewActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }


    @Override
    public void onStop() {
        mapView.onStop();
        super.onStop();
    }

    @Override
    public void onResume() {
        checkPlayServices();
        mapView.onResume();
        super.onResume();
    }

    @Override
    public void onDestroy() {
        mapView.onDestroy();
        super.onDestroy();
    }

    @Override
    public void onLowMemory() {
        mapView.onLowMemory();
        super.onLowMemory();
    }


    /**
     * Method to verify google play services on the device
     * */
    private final static int PLAY_SERVICES_RESOLUTION_REQUEST = 1000;

    private boolean checkPlayServices() {
        GoogleApiAvailability googleAPI = GoogleApiAvailability.getInstance();
        int result = googleAPI.isGooglePlayServicesAvailable(getContext());
        if (result != ConnectionResult.SUCCESS) {
            if (googleAPI.isUserResolvableError(result)) {
                googleAPI.getErrorDialog(getActivity(), result,
                        PLAY_SERVICES_RESOLUTION_REQUEST).show();
            }

            return false;
        }

        return true;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

}
