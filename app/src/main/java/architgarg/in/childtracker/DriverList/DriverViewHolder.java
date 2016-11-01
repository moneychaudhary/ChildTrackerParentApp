package architgarg.in.childtracker.DriverList;

// Created by Archit Garg on 02 Oct, 2016.

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;


import architgarg.in.childtracker.Activities.MapsActivity;
import architgarg.in.childtracker.R;

class DriverViewHolder extends RecyclerView.ViewHolder {

      private Context context;
      private TextView driverNameTextView;
      private TextView driverRouteNoTextView;

      private String name;
      private String contact;
      private String busNo;
      private String routeNo;
      private String id;
      private String lat;
      private String lng;
      public Driver driver;

      DriverViewHolder(View itemView, final Context context) {
            super(itemView);

            this.context = context;
            driverNameTextView = (TextView) itemView.findViewById(R.id.driver_name_id);
            driverRouteNoTextView = (TextView) itemView.findViewById(R.id.driver_route_no_id);

//            driverNameTextView.setTypeface(EasyFonts.caviarDreams(context));

            itemView.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {
                        Intent intent = MapsActivity.produceIntent(DriverViewHolder.this.context,name,contact,busNo,routeNo,id,lat,lng,driver);
                        context.startActivity(intent);
                  }
            });
      }

      public void bindDriver(Driver driver) {

            this.name = driver.getName();
            this.contact = driver.getContact();
            this.routeNo = driver.getRouteno();
            this.busNo = driver.getBusno();
            this.id = driver.getId();
            this.lat= driver.getLat();
            this.lng = driver.getLong();

            driverNameTextView.setText(name);
            driverRouteNoTextView.setText("Route No : " + routeNo);
      }
}
