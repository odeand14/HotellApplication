package no.westerdals.odeand.hotellapplication;

// Created by Andreas Ødegaard on 25.05.2017.


import android.support.v4.app.Fragment;

public class ShowStreetViewActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new ShowStreetViewFragment();
    }
}
