package no.westerdals.odeand.hotellapplication;

import android.support.v4.app.Fragment;

public class MainActivity extends SingleFragmentActivity {

     @Override
     protected Fragment createFragment() {
         return MainFragment.newInstance();
     }

}
