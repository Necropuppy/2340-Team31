package team31.cs2340.gatech.sheltermap;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * Class for MapActivity (map logic)
 */
public class MapActivity extends AppCompatActivity implements OnMapReadyCallback{


    private Spinner filter;
    private static final String[] options = {"No Filter", "Male", "Female", "Children",
            "Young Adults", "Families"};
    private static String current = "No Filter";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        Button update;
        update = findViewById(R.id.update);
        filter = findViewById(R.id.filter);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, options);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        filter.setAdapter(adapter);
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent go2Map = new Intent(getApplicationContext(), MapActivity.class);
                current = (String) filter.getSelectedItem();
                startActivity(go2Map);
            }
        });
        SupportMapFragment mapFragment = (SupportMapFragment)
                getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        LatLng shelter = null;
        for (int i = 0; i < Shelter.shelters.size(); i++) {
            if (!(current.equals("Female")
                    && Shelter.shelters.get(i).getRestriction().equals("Men"))
                    && !(current.equals("Male")
                    && Shelter.shelters.get(i).getRestriction().equals("Women/Children"))) {
                if (!current.equals("Children") ||
                        (Shelter.shelters.get(i).getRestriction().equals("Women/Children") ||
                                Shelter.shelters.get(i).getRestriction().equals("Families " +
                                        "w/ Children under 5") ||
                                Shelter.shelters.get(i).getRestriction().equals("Childrens/Young " +
                                        "adults "))) {
                    if (!current.equals("Young Adults") ||
                            (Shelter.shelters.get(i).getRestriction().equals("Childrens/Young " +
                                    "adults ") ||
                                    Shelter.shelters.get(i).getRestriction().equals("Young " +
                                            "adults "))) {
                        if (!current.equals("Families") ||
                                (Shelter.shelters.get(i).getRestriction().equals("Families ") ||
                                Shelter.shelters.get(i).getRestriction().equals("Families " +
                                        "w/ newborns ") ||
                                Shelter.shelters.get(i).getRestriction().equals("Families " +
                                        "w/ Children under 5"))){
                            shelter = new LatLng(Shelter.shelters.get(i).getLatitude(),
                                    Shelter.shelters.get(i).getLongitude());
                            googleMap.addMarker(new MarkerOptions().position(shelter).title(Shelter
                                    .shelters.get(i).getName()).snippet(Shelter.shelters.get(i)
                                    .getAddress()));
                        }
                    }
                }
            }
        }
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(shelter));
    }
}
