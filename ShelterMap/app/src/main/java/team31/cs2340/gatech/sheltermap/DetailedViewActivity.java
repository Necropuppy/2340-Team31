package team31.cs2340.gatech.sheltermap;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

/**
 * Created by Elmer on 3/3/2018.
 */

public class DetailedViewActivity extends AppCompatActivity{

    protected void onCreate(Bundle savedInstanceState) {
        Log.d(AccountActivity.TAG, "Successful on create");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed);

        int position = getIntent().getExtras().getInt("Position");
        Shelter shelter = Shelter.shelters.get(position);
        Log.d(AccountActivity.TAG, "Successful extra");
        TextView name = (TextView) findViewById(R.id.shelter_name);
        name.setText(shelter.getName());
        Log.d(AccountActivity.TAG, "Successful name");
        TextView key = (TextView) findViewById(R.id.shelter_key);
        key.setText("" + shelter.getKey());
        TextView capacity = (TextView) findViewById(R.id.shelter_cap);
        capacity.setText(shelter.getCap());
        TextView restriction = (TextView) findViewById(R.id.shelter_restriction);
        restriction.setText("" + shelter.getRestriction());
        TextView longitude = (TextView) findViewById(R.id.shelter_longitude);
        longitude.setText("" + shelter.getLongitude());
        TextView latitude = (TextView) findViewById(R.id.shelter_latitude);
        latitude.setText("" + shelter.getLatitude());
        TextView address = (TextView) findViewById(R.id.shelter_address);
        address.setText(shelter.getAddress());
    }
}
