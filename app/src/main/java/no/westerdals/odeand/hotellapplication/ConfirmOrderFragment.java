package no.westerdals.odeand.hotellapplication;

// Created by Andreas Ødegaard on 23.05.2017.


import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Objects;

public class ConfirmOrderFragment extends Fragment {

    private TextView txtGuestInfo;
    private EditText edtNumberPassengers;
    private TimePicker timePicker;
    private Button btnOrder, btnCancel;
    private Guest guest;
    private String numberOfPassengers;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_confirm_order, container, false);

        txtGuestInfo = (TextView) view.findViewById(R.id.guest_info);
        edtNumberPassengers = (EditText) view.findViewById(R.id.edit_confirm_number_passengers);
        btnCancel = (Button) view.findViewById(R.id.button_confirm_cancel);
        btnOrder = (Button) view.findViewById(R.id.button_confirm_order);
        timePicker = (TimePicker) view.findViewById(R.id.timePicker);

        if (!Objects.equals(edtNumberPassengers.getText().toString(), "")) {
            numberOfPassengers = edtNumberPassengers.getText().toString();
        } else {
            numberOfPassengers = "1";
        }


        try {
            Bundle bundle = getActivity().getIntent().getExtras();
            guest = (Guest) bundle.getSerializable("guest");
            txtGuestInfo.setText(guest != null ? "Confirm order for " + guest.getName() + " in room " + guest.getRoomNumber() : "");
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
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                sendConfirmationEmail();
            }
        });

        return view;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void sendConfirmationEmail() {
        MailSender mailSender = new MailSender(getContext(), guest.getEmail(), "Taxiorder Confirmation", buildMessage());
        mailSender.execute();
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private String buildMessage() {
        return "Hello " + guest.getName() + "!\nA Taxi for " + numberOfPassengers +
                " people has been ordered for you.\nIt will arrive " + timePicker.getHour() + ":" + timePicker.getMinute() +
                ".\nPlease contact the reception if you have questions.";
    }

}
