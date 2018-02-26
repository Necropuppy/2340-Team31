package team31.cs2340.gatech.sheltermap;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class WelcomeActivity extends AppCompatActivity {

    private Button login;
    private Button register;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        login = (Button) findViewById(R.id.login);
        register = (Button) findViewById(R.id.register);

        /******************************************************************************************/

        login=(Button)findViewById(R.id.login);

        login.setOnClickListener(new View.OnClickListener() {

            // https://androidsolved.wordpress.com/2015/07/01/how-to-move-from-one-activityscreen-to
            // -another-activityscreen-in-android/

            @Override
            public void onClick(View view) {

                // TODO Auto-generated method stub

                Intent go2Login = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(go2Login);
            }
        });

        register.setOnClickListener(new View.OnClickListener() {

            // https://androidsolved.wordpress.com/2015/07/01/how-to-move-from-one-activityscreen-to-another-activityscreen-in-android/
            @Override
            public void onClick(View view) {

                Intent Reg = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(Reg);
            }
        });
    }
}
