package architgarg.in.childtracker.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import architgarg.in.childtracker.DriverList.DriverListActivity;
import architgarg.in.childtracker.R;

public class MainActivity extends AppCompatActivity {

      @Override
      protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

//            TextView parentLoginTextView = (TextView) findViewById(R.id.parent_login_id);
//            parentLoginTextView.setTypeface(EasyFonts.caviarDreams(this));

//            Button button = (Button) findViewById(R.id.start_tracking_button);
//            button.setTypeface(EasyFonts.caviarDreams(this));

      }

      public void startTrackingButtonClicked(View view) {
            Intent intent = DriverListActivity.produceIntent(this);
            startActivity(intent);
      }
}
