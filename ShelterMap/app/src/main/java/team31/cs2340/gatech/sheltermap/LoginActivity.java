package team31.cs2340.gatech.sheltermap;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    private EditText username;
    private EditText password;
    private Button cancel;
    private Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);

        final TextView errorText = (TextView) findViewById(R.id.password);
        errorText.setTextColor(Color.RED);

        cancel = (Button) findViewById(R.id.cancel);
        submit = (Button) findViewById(R.id.submit);

        /******************************************************************************************/

        cancel = (Button) findViewById(R.id.cancel);
        cancel.setOnClickListener(new View.OnClickListener() {

            // https://androidsolved.wordpress.com/2015/07/01/how-to-move-from-one-activityscreen-to-another-activityscreen-in-android/
            @Override
            public void onClick(View view) {
                // TODO Auto-generated method stub
                Intent go2Welcome = new Intent(getApplicationContext(), WelcomeActivity.class);
                startActivity(go2Welcome);
            }
        });


//        submit = (Button) findViewById(R.id.submit);
//        submit.setOnClickListener(new View.OnClickListener() {
//
//            // https://androidsolved.wordpress.com/2015/07/01/how-to-move-from-one-activityscreen-to-another-activityscreen-in-android/
//            @Override
//            public void onClick(View view) {
//                // TODO Auto-generated method stub
//                Intent go2Account = new Intent(getApplicationContext(), AccountActivity.class);
//                startActivity(go2Account);
//            }
//        });


        submit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (username.getText().toString().equals("user") && password.getText().toString().equals("pass")){
                    Intent go2Account = new Intent(LoginActivity.this, AccountActivity.class);
                    startActivity(go2Account);
                } else {
                    errorText.setVisibility(View.VISIBLE);
                }
            }
        });

    }
}
