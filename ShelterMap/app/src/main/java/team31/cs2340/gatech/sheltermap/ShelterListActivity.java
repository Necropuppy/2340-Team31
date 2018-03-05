package team31.cs2340.gatech.sheltermap;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class ShelterListActivity extends AppCompatActivity {

    ListView listview;

    private List<Shelter> shelters = new ArrayList<>();

    ArrayAdapter<Shelter> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shelterlist);

        readShelterData();

        listview = (ListView) findViewById(R.id.shelterList);
        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, shelters);
        listview.setAdapter(adapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent go2Shelter = new Intent(ShelterListActivity.this, ShelterActivity.class);
                go2Shelter.putExtra("Key", "" + shelters.get(position).getUniqueKey());
                go2Shelter.putExtra("Name", shelters.get(position).getName());
                go2Shelter.putExtra("Capacity", shelters.get(position).getCapacity());
                go2Shelter.putExtra("Restrictions", shelters.get(position).getRestrictions());
                go2Shelter.putExtra("Longitude", "" + shelters.get(position).getLongitude());
                go2Shelter.putExtra("Latitude", "" + shelters.get(position).getLatitude());
                go2Shelter.putExtra("Address", shelters.get(position).getAddress());
                go2Shelter.putExtra("Notes", shelters.get(position).getNotes());
                go2Shelter.putExtra("Phone", shelters.get(position).getPhone());
                startActivity(go2Shelter);
            }
        });
    }

    /**********************************************************************************************/

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
            }
        } catch (IOException e) {
            Log.wtf("MyActivity", "Error reading data file on line " + line, e);
            e.printStackTrace();
        }
    }
}
