package architgarg.in.childtracker.Activities;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import architgarg.in.childtracker.DriverList.Driver;
import architgarg.in.childtracker.R;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    public static final String EXTRA_NAME = "driver";
    public static final String EXTRA_CONTACT = "conatct";
    public static final String EXTRA_ROUTE_NO = "routeno";
    public static final String EXTRA_BUS_NO = "busno";
    public static final String EXTRA_ID = "id";
    public static final String EXTRA_LAT = "lat";
    public static final String EXTRA_LNG = "lng";
    private static Driver mDriver;
    private DatabaseReference mDatabase;
    private GoogleMap mMap;
    private Marker currentMarker = null;


    private String lat;
    private String lng;


    public static Intent produceIntent(Context context, String name, String contact, String routeNo, String busNo, String id, String lat, String lng, Driver driver) {
        Intent intent = new Intent(context, MapsActivity.class);
        intent.putExtra(EXTRA_NAME, name);
        intent.putExtra(EXTRA_CONTACT, contact);
        intent.putExtra(EXTRA_ROUTE_NO, routeNo);
        intent.putExtra(EXTRA_BUS_NO, busNo);
        intent.putExtra(EXTRA_ID, id);
        intent.putExtra(EXTRA_LAT, lat);
        intent.putExtra(EXTRA_LNG, lng);
        mDriver = driver;
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        mDatabase = FirebaseDatabase.getInstance().getReference().child("driver");

        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    Driver driver = ds.getValue(Driver.class);
                    if (mDriver.getId() == driver.getId() && driver.getStatus().equals("1")) {
                        lat = driver.getLat();
                        lng = driver.getLong();
                    }
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        TextView nameTextView = (TextView) findViewById(R.id.dname);
        TextView contactTextView = (TextView) findViewById(R.id.dcontact);
        TextView routeTextView = (TextView) findViewById(R.id.droute);
        TextView busTextView = (TextView) findViewById(R.id.dbusno);
        TextView idTextView = (TextView) findViewById(R.id.did);

//            nameTextView.setTypeface(EasyFonts.caviarDreams(this));
//            contactTextView.setTypeface(EasyFonts.caviarDreams(this));
//            routeTextView.setTypeface(EasyFonts.caviarDreams(this));
//            busTextView.setTypeface(EasyFonts.caviarDreams(this));
//            idTextView.setTypeface(EasyFonts.caviarDreams(this));

        Intent intent = getIntent();
        String name = intent.getStringExtra(EXTRA_NAME);
        String conatct = intent.getStringExtra(EXTRA_CONTACT);
        String routeNo = intent.getStringExtra(EXTRA_ROUTE_NO);
        String busNo = intent.getStringExtra(EXTRA_BUS_NO);
        String id = intent.getStringExtra(EXTRA_ID);
        lat = intent.getStringExtra(EXTRA_LAT);
        lng = intent.getStringExtra(EXTRA_LNG);

        nameTextView.setText(name);
        contactTextView.setText(conatct);
        busTextView.setText(busNo);
        routeTextView.setText(routeNo);
        idTextView.setText(id);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        updateLocation(googleMap);
    }


    public void updateLocation(final GoogleMap mMap) {
        this.mMap = mMap;
        LatLng child = new LatLng(Double.parseDouble(lat), Double.parseDouble(lng));
        if (currentMarker != null) {
            currentMarker.remove();
            currentMarker = null;
        }
        if (currentMarker == null) {
            currentMarker = mMap.addMarker(new MarkerOptions().position(child).title("Child Is Here"));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(child));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(child, 17));
        }

    }

    public void refreshMap(View view) {
        updateLocation(mMap);
    }
}