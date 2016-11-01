package architgarg.in.childtracker.DriverList;

// Created by Archit Garg on 02 Oct, 2016.

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import architgarg.in.childtracker.R;

class DriverAdapter extends RecyclerView.Adapter<DriverViewHolder> {

      private Context context;
      private List<Driver> drivers;

      DriverAdapter(Context context, List<Driver> drivers) {
            this.context = context;
            this.drivers=drivers;
      }

      @Override
      public DriverViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflator = LayoutInflater.from(context);
            View view = inflator.inflate(R.layout.one_line, parent, false);
            return new DriverViewHolder(view,context);
      }

      @Override
      public void onBindViewHolder(DriverViewHolder holder, int position) {
            holder.bindDriver(drivers.get(position));
            holder.driver=drivers.get(position);
      }

      @Override
      public int getItemCount() {
            return drivers.size();
      }
}
