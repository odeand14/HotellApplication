package no.westerdals.odeand.hotellapplication;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;


public class MyGuestRecyclerViewAdapter extends RecyclerView.Adapter<MyGuestRecyclerViewAdapter.ViewHolder> {

    private final List<Guest> mValues;

    public MyGuestRecyclerViewAdapter(List<Guest> guests) {
        mValues = guests;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_all_guests, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        String roomNumber = "Room: " + String.valueOf(mValues.get(position).getRoomNumber());
        holder.mItem = mValues.get(position);
        holder.mIdView.setText(roomNumber);
        holder.mContentView.setText(mValues.get(position).toString());

    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mIdView;
        public final TextView mContentView;
        public Guest mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mIdView = (TextView) view.findViewById(R.id.id);
            mContentView = (TextView) view.findViewById(R.id.content);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}
