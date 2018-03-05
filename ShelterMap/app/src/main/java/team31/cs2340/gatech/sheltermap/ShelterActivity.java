package team31.cs2340.gatech.sheltermap;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ShelterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shelter);

        String key = getIntent().getStringExtra("Key");
        TextView insertKey = (TextView) findViewById(R.id.insertKey);
        insertKey.setText(key);

        String name = getIntent().getStringExtra("Name");
        TextView insertName = (TextView) findViewById(R.id.insertName);
        insertName.setText(name);

        String capacity = getIntent().getStringExtra("Capacity");
        TextView insertCapacity = (TextView) findViewById(R.id.insertCapacity);
        insertCapacity.setText(capacity);

        String restrictions = getIntent().getStringExtra("Restrictions");
        TextView insertRestrictions = (TextView) findViewById(R.id.insertRestrictions);
        insertRestrictions.setText(restrictions);

        String longitude = getIntent().getStringExtra("Longitude");
        TextView insertLongitude = (TextView) findViewById(R.id.insertLongitude);
        insertLongitude.setText(longitude);

        String latitude = getIntent().getStringExtra("Latitude");
        TextView insertLatitude = (TextView) findViewById(R.id.insertLatitude);
        insertLatitude.setText(latitude);

        String address = getIntent().getStringExtra("Address");
        TextView insertAddress = (TextView) findViewById(R.id.insertAddress);
        insertAddress.setText(address);

        String notes = getIntent().getStringExtra("Notes");
        TextView insertNotes = (TextView) findViewById(R.id.insertNotes);
        insertNotes.setText(notes);

        String phone = getIntent().getStringExtra("Phone");
        TextView insertPhone = (TextView) findViewById(R.id.insertPhone);
        insertPhone.setText(phone);
    }
}
