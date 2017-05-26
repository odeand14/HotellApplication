package no.westerdals.odeand.hotellapplication;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


public class AllGuestsFragment extends Fragment {

    private RecyclerView recyclerView;
    private List<Guest> guestList;
    private GuestsDataSource dataSource;

    public AllGuestsFragment() {
    }

    public static AllGuestsFragment newInstance() {

        return new AllGuestsFragment();
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_all_guests_list, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.list);
        recyclerView.hasFixedSize();

        dataSource = new GuestsDataSource(getContext());
        dataSource.open();
        guestList = new ArrayList<>();
        guestList = dataSource.getAllGuests();
        dataSource.close();

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(new MyGuestRecyclerViewAdapter(guestList));

        return view;
    }



}
