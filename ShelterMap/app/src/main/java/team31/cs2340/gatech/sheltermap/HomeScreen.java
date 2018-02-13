package team31.cs2340.gatech.sheltermap;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomeScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        Button logoutBtn = (Button) findViewById(R.id.logoutButton);


        logoutBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent myIntent = new Intent(HomeScreen.this, LoginActivity.class);
                startActivity(myIntent);

            }
        });

    }
}
