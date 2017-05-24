package no.westerdals.odeand.hotellapplication;

import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.FragmentManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import no.westerdals.odeand.hotellapplication.dummy.DummyContent;

public class MainActivity extends AppCompatActivity implements
        MainFragment.OnFragmentInteractionListener,
        HotellInfoFragment.OnFragmentInteractionListener,
        AllGuestsFragment.OnListFragmentInteractionListener,
        NewGuestFragment.OnFragmentInteractionListener {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, new MainFragment())
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
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
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
            transaction.replace(R.id.fragment_container, AllGuestsFragment.newInstance(100));
            transaction.addToBackStack(null);

            transaction.commit();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void onListFragmentInteraction(DummyContent.DummyItem item) {

    }
}
