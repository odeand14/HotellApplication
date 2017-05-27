package no.westerdals.odeand.hotellapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class MainFragment extends Fragment {

    private Button btnSubmit;
    private TextView txtRoomNr;
    private String guestRoomNumber;
    private GuestsDataSource dataSource;
    private Guest guest;
    private ImageView imageViewTop, imageViewBottom;

    public MainFragment() {
    }

    public static MainFragment newInstance() {

        return new MainFragment();
    }

    @Override
    public void onResume() {
        ((SingleFragmentActivity) getActivity()).setActonBarTitle("Taxi booker");
        super.onResume();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        ((SingleFragmentActivity) getActivity()).setActonBarTitle("Taxi booker");
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        btnSubmit = (Button) view.findViewById(R.id.btnSubmitRoomNumber);
        txtRoomNr = (TextView) view.findViewById(R.id.txtRoomNumber);
        imageViewTop = (ImageView) view.findViewById(R.id.taxi_image_top);
        imageViewBottom = (ImageView) view.findViewById(R.id.taxi_image_bottom);
        imageViewTop.setImageResource(R.mipmap.taxi_squares);
        imageViewBottom.setImageResource(R.mipmap.taxi_squares);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!txtRoomNr.getText().toString().equals("")) {
                    guestRoomNumber = txtRoomNr.getText().toString();
                } else {
                    Toast.makeText(getContext(), "You need to enter room number!", Toast.LENGTH_SHORT).show();
                    return;
                }

                dataSource = new GuestsDataSource(getContext());
                dataSource.open();
                guest = dataSource.getGuest(Integer.parseInt(guestRoomNumber));
                dataSource.close();

                if (guest != null) {

                    Intent intent = new Intent(getActivity(), ConfirmOrderActivity.class);
                    intent.putExtra("guest", guest);
                    startActivity(intent);

                } else {
                    Toast.makeText(getContext(), "No guest on given room number!", Toast.LENGTH_SHORT).show();
                }

            }
        });

        return view;
    }

}
