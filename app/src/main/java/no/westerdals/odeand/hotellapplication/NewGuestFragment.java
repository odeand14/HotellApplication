package no.westerdals.odeand.hotellapplication;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class NewGuestFragment extends Fragment {

    private EditText txtPhone, txtRoom, txtName, txtEmail;
    private Button btnSubmit, btnCancel;
    private GuestsDataSource guestsDataSource;

    public NewGuestFragment() {}

    public static NewGuestFragment newInstance() {

        return new NewGuestFragment();
    }

    @Override
    public void onResume() {
        ((SingleFragmentActivity) getActivity()).setActonBarTitle("New Guest");
        super.onResume();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        ((SingleFragmentActivity) getActivity()).setActonBarTitle("New Guest");
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_new_guest, container, false);

        txtName = (EditText) view.findViewById(R.id.edit_new_guest_name);
        txtRoom = (EditText) view.findViewById(R.id.edit_new_room_number);
        txtEmail = (EditText) view.findViewById(R.id.edit_new_guest_email);
        txtPhone = (EditText) view.findViewById(R.id.edit_new_phone_number);

        btnSubmit = (Button) view.findViewById(R.id.button_new_guest_submit);
        btnCancel = (Button) view.findViewById(R.id.button_new_guest_cancel);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Guest guest = new Guest(
                        txtName.getText().toString(),
                        txtEmail.getText().toString(),
                        1L,
                        Long.parseLong(txtPhone.getText().toString()),
                        Integer.parseInt(txtRoom.getText().toString()));

                txtName.getText().clear();
                txtEmail.getText().clear();
                txtPhone.getText().clear();
                txtRoom.getText().clear();

                guestsDataSource = new GuestsDataSource(getContext());
                guestsDataSource.open();

                try {
                    guestsDataSource.createGuest(guest);
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(getContext(), "Creation failed!", Toast.LENGTH_LONG).show();
                }

                Toast.makeText(getContext(), "Registered new Guest!", Toast.LENGTH_LONG).show();
                guestsDataSource.close();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getFragmentManager();
                FragmentTransaction transaction = fm.beginTransaction();

                transaction.replace(R.id.fragment_container, MainFragment.newInstance());
                transaction.addToBackStack(null);

                transaction.commit();
            }
        });

        return view;
    }


}
