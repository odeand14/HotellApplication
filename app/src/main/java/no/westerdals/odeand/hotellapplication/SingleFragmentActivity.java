package no.westerdals.odeand.hotellapplication;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;


// Created by Andreas Ødegaard on 22.05.2017.


public abstract class SingleFragmentActivity extends AppCompatActivity {

    protected abstract Fragment createFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);

        android.support.v4.app.FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.fragment_container);

        if (fragment == null) {
            fragment = createFragment();
            fm.beginTransaction()
                    .add(R.id.fragment_container, fragment)
                    .commit();
        }
    }

    @Override
    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() > 0) {
            getFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();


        if (id == R.id.action_new_guest) {

            transaction.replace(R.id.fragment_container, NewGuestFragment.newInstance());
            transaction.addToBackStack(null);

            transaction.commit();

        } else if (id == R.id.action_hotel_info) {

            transaction.replace(R.id.fragment_container, HotellInfoFragment.newInstance());
            transaction.addToBackStack(null);

            transaction.commit();

        } else if (id == R.id.action_all_guests) {

            transaction.replace(R.id.fragment_container, AllGuestsFragment.newInstance());
            transaction.addToBackStack(null);

            transaction.commit();
        }

        return super.onOptionsItemSelected(item);
    }

}
