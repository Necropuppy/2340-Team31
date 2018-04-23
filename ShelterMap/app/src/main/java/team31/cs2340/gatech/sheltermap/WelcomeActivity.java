package team31.cs2340.gatech.sheltermap;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Class for WelcomeActivity (the screen logic for entry screen)
 */
public class WelcomeActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        User.loadUsers(this);
        Button login;
        Button register;
        Button skip;

        login = (Button) findViewById(R.id.login);
        register = (Button) findViewById(R.id.register);
        skip = (Button) findViewById(R.id.skip);

        login.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {

                Intent go2Login = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(go2Login);
            }
        });

        register.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {

                Intent Reg = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(Reg);
            }
        });

        skip.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {
                User.annonymous = true;
                Intent go2Login = new Intent(getApplicationContext(), AccountActivity.class);
                startActivity(go2Login);
            }
        });
    }
}
