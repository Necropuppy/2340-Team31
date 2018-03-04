package team31.cs2340.gatech.sheltermap;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AccountActivity extends AppCompatActivity {

    private Button logout;
    private Button shelters;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        /******************************************************************************************/

        logout=(Button)findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                // TODO Auto-generated method stub
                Intent go2Login = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(go2Login);
            }
        });

        shelters=(Button)findViewById(R.id.shelters);
        shelters.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                // TODO Auto-generated method stub
                Intent go2Login = new Intent(getApplicationContext(), ShelterActivity.class);
                startActivity(go2Login);
            }
        });

        /******************************************************************************************/

        String name = getIntent().getStringExtra("Name");
        TextView userName = (TextView) findViewById(R.id.insertUserName);
        userName.setText(name);
    }
}
