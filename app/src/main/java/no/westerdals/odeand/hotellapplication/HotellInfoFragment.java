package no.westerdals.odeand.hotellapplication;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.lang.reflect.Field;


public class HotellInfoFragment extends Fragment implements OnMapReadyCallback {

    private final static int PLAY_SERVICES_RESOLUTION_REQUEST = 1000;
    private static final LatLng HOTEL = new LatLng(59.91365930021811, 10.709168696822513);

    private Button mBtnShowAsStreetView;
    private SupportMapFragment fragment;
    private GoogleMap mMap;
    private View view;

    public HotellInfoFragment() {
    }

    public static HotellInfoFragment newInstance() {

        return new HotellInfoFragment();
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        ((SingleFragmentActivity) getActivity()).setActonBarTitle("Hotel Info");
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        FragmentManager fm = getChildFragmentManager();
        fragment = (SupportMapFragment) fm.findFragmentById(R.id.map_view);
        if (fragment == null) {
            fragment = SupportMapFragment.newInstance();
            fm.beginTransaction().replace(R.id.map_view, fragment).commit();
        }
        fragment.getMapAsync(this);

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        mMap.addMarker(new MarkerOptions().position(HOTEL).title("Andys hotel"));
        mMap.moveCamera(CameraUpdateFactory.newCameraPosition(new CameraPosition(HOTEL, 1, 0, 0)));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(HOTEL, 15), 3000, null);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        if (view == null) {
            view = inflater.inflate(R.layout.fragment_hotell_info, container, false);
        }

        try {
            MapsInitializer.initialize(getActivity().getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }

        mBtnShowAsStreetView = (Button) view.findViewById(R.id.button_show_street_view);
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
        fragment.onStop();
        super.onStop();
    }

    @Override
    public void onResume() {
        ((SingleFragmentActivity) getActivity()).setActonBarTitle("Hotel Info");
        checkPlayServices();
        fragment.onResume();
        super.onResume();

    }

    @Override
    public void onDetach() {
        fragment.onDetach();
        super.onDetach();
        try {
            Field childFragmentManager = Fragment.class
                    .getDeclaredField("mChildFragmentManager");
            childFragmentManager.setAccessible(true);
            childFragmentManager.set(this, null);

        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onDestroy() {
        if (mMap != null) {
            if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(),
                    Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

                mMap.setMyLocationEnabled(false);
                mMap.clear();
            }
        }
        if (fragment != null) {
            getFragmentManager().beginTransaction().remove(fragment).commit();
        }
        super.onDestroy();
    }


    @Override
    public void onLowMemory() {
        fragment.onLowMemory();
        super.onLowMemory();
    }


    /**
     * Method to verify google play services on the device
     * */
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


}
