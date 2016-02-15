package com.example.shon.boost4;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Shon on 30.01.2016.
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private List<Measurement> measurements;

    public RecyclerViewAdapter(List<Measurement> m) {
        this.measurements = m;
    }

    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_activity, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RecyclerViewAdapter.ViewHolder holder, int position) {
        holder.X.setText("X: " + String.valueOf(measurements.get(position).getX()));
        holder.Y.setText("Y: " + String.valueOf(measurements.get(position).getY()));
        holder.Z.setText("Z: " + String.valueOf(measurements.get(position).getZ()));
    }

    public void add(Measurement m){
        measurements.add(MainActivity.POS, m);
        notifyItemInserted(MainActivity.POS);
    }

    @Override
    public int getItemCount() {
        return measurements.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView X;
        private TextView Y;
        private TextView Z;

        public ViewHolder(View itemView) {
            super(itemView);
            X = (TextView) itemView.findViewById(R.id.x);
            Y = (TextView) itemView.findViewById(R.id.y);
            Z = (TextView) itemView.findViewById(R.id.z);
        }
    }
}