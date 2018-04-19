package team31.cs2340.gatech.sheltermap;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

/**
 * Class for SearchDetailedView (screen logic when searching for shelter)
 */
public class SearchDetailedView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_detailed_view);


        int position = getIntent().getExtras().getInt("Position");
        Shelter shelter = SearchActivity.getFiltered().get(position);
        Log.d(AccountActivity.TAG, "Successful extra");
        TextView name = (TextView) findViewById(R.id.shelter_name);
        String name1 = "Shelter Name: " + shelter.getName();
        name.setText(name1);
        Log.d(AccountActivity.TAG, "Successful name");
        TextView key = (TextView) findViewById(R.id.shelter_key);
        String name2 = "Shelter ID: " + shelter.getKey();
        key.setText(name2);
        TextView capacity = (TextView) findViewById(R.id.shelter_cap);
        String name3 = "Capacity: " + shelter.getPop() + "/" + shelter.getCap();
        capacity.setText(name3);
        TextView restriction = (TextView) findViewById(R.id.shelter_restriction);
        String name4 = "" + shelter.getRestriction();
        restriction.setText(name4);
        TextView longitude = (TextView) findViewById(R.id.shelter_longitude);
        String name5 = "Longitude: " + shelter.getLongitude();
        longitude.setText(name5);
        TextView latitude = (TextView) findViewById(R.id.shelter_latitude);
        String name6 = "Latitude: " + shelter.getLatitude();
        latitude.setText(name6);
        TextView address = (TextView) findViewById(R.id.shelter_address);
        String name7 = "Address: " + shelter.getAddress();
        address.setText(name7);
    }

}
