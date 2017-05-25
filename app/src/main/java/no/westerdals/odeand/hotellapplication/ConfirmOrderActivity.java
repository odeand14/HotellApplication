package no.westerdals.odeand.hotellapplication;

// Created by Andreas Ødegaard on 23.05.2017.


import android.support.v4.app.Fragment;

public class ConfirmOrderActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new ConfirmOrderFragment();
    }

}
