package architgarg.in.childtracker.DriverList;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import architgarg.in.childtracker.R;

public class DriverListActivity extends AppCompatActivity {
      private DatabaseReference mDatabase;
      private List<Driver> driverList = new ArrayList<>();
      private RecyclerView mRecyclerView;
      private DriverAdapter adapter;
      private LinearLayoutManager mLinearLayoutManager;
      private ProgressDialog mProgressDialog;

      public static Intent produceIntent(Context context) {
            return new Intent(context, DriverListActivity.class);
      }

      @Override
      protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_driver_list);
            mDatabase = FirebaseDatabase.getInstance().getReference().child("driver");
            mProgressDialog = new ProgressDialog(this);
            mProgressDialog.setMessage("Loading......");
            mProgressDialog.setCanceledOnTouchOutside(false);
            mProgressDialog.show();

            TextView driversOnDutyTextView = (TextView) findViewById(R.id.driver_on_duty_textview);
//            driversOnDutyTextView.setTypeface(EasyFonts.caviarDreams(this));

            mRecyclerView = (RecyclerView) findViewById(R.id.recyler_view_id);
            mLinearLayoutManager = new LinearLayoutManager(this);
            mLinearLayoutManager.setReverseLayout(true);
            mLinearLayoutManager.setStackFromEnd(true);
            mRecyclerView.setLayoutManager(mLinearLayoutManager);

            mDatabase.addValueEventListener(new ValueEventListener() {
                  @Override
                  public void onDataChange(DataSnapshot dataSnapshot) {
                        driverList.removeAll(driverList);
                        mProgressDialog.dismiss();
                        for (DataSnapshot ds : dataSnapshot.getChildren()) {
                              Driver driver = ds.getValue(Driver.class);
                              if (!(driver.getStatus().equals("0")))
                                    driverList.add(driver);
                        }
                        adapter.notifyDataSetChanged();

                  }

                  @Override
                  public void onCancelled(DatabaseError databaseError) {

                  }
            });
            adapter = new DriverAdapter(this, driverList);
            mRecyclerView.setAdapter(adapter);
      }
}
