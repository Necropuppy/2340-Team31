package team31.cs2340.gatech.sheltermap;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class SearchDetailedView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_detailed_view);


        int position = getIntent().getExtras().getInt("Position");
        Shelter shelter = SearchActivity.getFiltered().get(position);
        Log.d(AccountActivity.TAG, "Successful extra");
        TextView name = (TextView) findViewById(R.id.shelter_name);
        name.setText(shelter.getName());
        Log.d(AccountActivity.TAG, "Successful name");
        TextView key = (TextView) findViewById(R.id.shelter_key);
        key.setText("" + shelter.getKey());
        TextView capacity = (TextView) findViewById(R.id.shelter_cap);
        capacity.setText("" + shelter.getCap());
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
