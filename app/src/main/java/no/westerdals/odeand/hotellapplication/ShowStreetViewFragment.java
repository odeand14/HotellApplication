package no.westerdals.odeand.hotellapplication;

// Created by Andreas Ødegaard on 25.05.2017.

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.maps.OnStreetViewPanoramaReadyCallback;
import com.google.android.gms.maps.StreetViewPanorama;
import com.google.android.gms.maps.StreetViewPanoramaOptions;
import com.google.android.gms.maps.StreetViewPanoramaView;
import com.google.android.gms.maps.model.LatLng;

public class ShowStreetViewFragment extends Fragment implements OnStreetViewPanoramaReadyCallback {

    private StreetViewPanoramaView mStreetViewPanoramaView;

    private static final LatLng HOTEL = new LatLng(59.91365930021811, 10.709168696822513);
    private static final String STREETVIEW_BUNDLE_KEY = "StreetViewBundleKey";

    public ShowStreetViewFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        StreetViewPanoramaOptions options = new StreetViewPanoramaOptions();
        if (savedInstanceState == null) {
            options.position(HOTEL);
        }
        checkPlayServices();
        mStreetViewPanoramaView = new StreetViewPanoramaView(getContext(), options);
        getActivity().addContentView(mStreetViewPanoramaView,
                new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

        Bundle mStreetViewBundle = null;
        if (savedInstanceState != null) {
            mStreetViewBundle = savedInstanceState.getBundle(STREETVIEW_BUNDLE_KEY);
        }
        mStreetViewPanoramaView.onCreate(mStreetViewBundle);

        return inflater.inflate(R.layout.fragment_show_street_view, container, false);

    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        mStreetViewPanoramaView.onResume();
        super.onResume();
        checkPlayServices();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onPause() {
        mStreetViewPanoramaView.onPause();
        super.onPause();
    }

    @Override
    public void onDestroy() {
        mStreetViewPanoramaView.onDestroy();
        super.onDestroy();
    }

    @Override
    public void onLowMemory() {
        mStreetViewPanoramaView.onLowMemory();
        super.onLowMemory();
    }

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

    @Override
    public void onStreetViewPanoramaReady(StreetViewPanorama streetViewPanorama) {

    }



}
