package no.westerdals.odeand.hotellapplication;

// Created by Andreas Ødegaard on 23.05.2017.


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ConfirmOrderFragment extends Fragment {

    private TextView txtGuestInfo;
    private EditText edtNumberPassengers, edtDeparture;
    private Button btnOrder, btnCancel;
    private GuestsDataSource dataSource;
    private Guest guest;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_confirm_order, container, false);

        txtGuestInfo = (TextView) view.findViewById(R.id.guest_info);
        edtNumberPassengers = (EditText) view.findViewById(R.id.edit_confirm_number_passengers);
        edtDeparture = (EditText) view.findViewById(R.id.edit_confirm_time);
        btnCancel = (Button) view.findViewById(R.id.button_confirm_cancel);
        btnOrder = (Button) view.findViewById(R.id.button_confirm_order);


        try {
            Bundle bundle = getActivity().getIntent().getExtras();
            guest = (Guest) bundle.getSerializable("guest");
            txtGuestInfo.setText(guest != null ? guest.toString() : "");

        } catch (Exception e) {
            e.printStackTrace();
        }

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
            }
        });

        btnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendConfirmationEmail();
            }
        });

        return view;
    }

    private void sendConfirmationEmail() {
        MailSender mailSender = new MailSender(getContext(), guest.getEmail(), "Taxiorder Confirmation", guest.toString());
        mailSender.execute();
    }

}
