package team31.cs2340.gatech.sheltermap;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

/**
 * Class for SearchActivity (the search screen logic after clicking search from main screen)
 */
public class SearchActivity extends AppCompatActivity {
    private Spinner gender;
    private Spinner age;
    private EditText shelterName;

    private static final String[] genders = {"All", "Male", "Female"};
    private static final String[] ages = {"All", "Children", "Young Adults", "Families"};
    private static List<Shelter> filtered;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        Button search;
        gender = findViewById(R.id.GenderSpinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, genders);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        gender.setAdapter(adapter);
        age = findViewById(R.id.AgeSpinner);
        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, ages);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        age.setAdapter(adapter);
        shelterName = findViewById(R.id.ShelterName);
        search = findViewById(R.id.Search);
        search.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent go2Results = new Intent(getApplicationContext(), ResultActivity.class);
                filtered = (ArrayList<Shelter>) Shelter.shelters.clone();
                if(gender.getSelectedItem() == "Female") {
                    for (int i = 0; i < filtered.size(); i++) {
                        if (filtered.get(i).getRestriction().equals("Men")) {
                            filtered.remove(i);
                            i--;
                        }
                    }
                }
                if(gender.getSelectedItem() == "Male") {
                    for (int i = 0; i < filtered.size(); i++) {
                        if (filtered.get(i).getRestriction().equals("Women/Children")) {
                            filtered.remove(i);
                            i--;
                        }
                    }
                }
                if(age.getSelectedItem() == "Children") {
                    for (int i = 0; i < filtered.size(); i++) {
                        if (!(filtered.get(i).getRestriction().equals("Women/Children")
                                || filtered.get(i).getRestriction().equals("Families " +
                                "w/ Children under 5")
                                || filtered.get(i).getRestriction().equals("Childrens/Young " +
                                "adults "))) {
                            filtered.remove(i);
                            i--;
                        }
                    }
                }
                if(age.getSelectedItem() == "Young Adults") {
                    for (int i = 0; i < filtered.size(); i++) {
                        if (!(filtered.get(i).getRestriction().equals("Childrens/Young adults ")
                                || filtered.get(i).getRestriction().equals("Young adults "))) {
                            filtered.remove(i);
                            i--;
                        }
                    }
                }
                if(age.getSelectedItem() == "Families") {
                    for (int i = 0; i < filtered.size(); i++) {
                        if (!(filtered.get(i).getRestriction().equals("Families ")
                                || filtered.get(i).getRestriction().equals("Families w/ newborns ")
                                || filtered.get(i).getRestriction().equals("Families " +
                                "w/ Children under 5"))) {
                            filtered.remove(i);
                            i--;
                        }
                    }
                }
                if(!shelterName.getText().toString().equals("Shelter Name")) {
                    for (int i = 0; i < filtered.size(); i++) {
                        if (!(filtered.get(i).getName().trim().equals(shelterName.getText()
                                .toString()))) {
                            filtered.remove(i);
                            i--;
                        }
                    }
                }
                startActivity(go2Results);
            }
        });
    }

    /**
     * Filters the shelter array list
     * @return array list of shelters filtered
     */
    public static List<Shelter> getFiltered() {
        return filtered;
    }

}
