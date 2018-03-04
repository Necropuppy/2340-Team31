package team31.cs2340.gatech.sheltermap;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class ShelterActivity extends AppCompatActivity {

    ListView list;

    private List<Shelter> shelters = new ArrayList<>();

    ArrayAdapter<Shelter> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shelter);

        readShelterData();

        list = (ListView) findViewById(R.id.shelterList);
        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, shelters);
        list.setAdapter(adapter);
    }

    private void readShelterData() {
        InputStream is = getResources().openRawResource(R.raw.homeless_shelter_database);
        BufferedReader reader = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));

        String line = "";

        try {
            line = reader.readLine();

            while ((line = reader.readLine()) != null) {
                // Split by ','
                String[] tokens = line.split(",");

                // read data
                Shelter s = new Shelter();

                s.setUniqueKey(Integer.parseInt(tokens[0]));
                s.setName(tokens[1]);
                s.setCapacity(tokens[2]);
                s.setRestrictions(tokens[3]);
                s.setLongitude(Double.parseDouble(tokens[4]));
                s.setLatitude(Double.parseDouble(tokens[5]));
                s.setAddress(tokens[6]);
                s.setNotes(tokens[7]);
                s.setPhone(tokens[8]);

                shelters.add(s);

                Log.d("MyActivity", "Just created: " + s);
            }
        } catch (IOException e) {
            Log.wtf("MyActivity", "Error reading data file on line " + line, e);
            e.printStackTrace();
        }
    }
}
