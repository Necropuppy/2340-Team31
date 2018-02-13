package team31.cs2340.gatech.sheltermap;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    private String username = "user";
    private String password = "pass";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final EditText userBox = (EditText) findViewById(R.id.usernameBox);
        final EditText passBox = (EditText) findViewById(R.id.passwordBox);
        final TextView errorText = (TextView) findViewById(R.id.passwordBox);
        errorText.setTextColor(Color.RED);

        Button loginBtn = (Button) findViewById(R.id.loginButton);


        loginBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (userBox.getText().toString().equals(username) && passBox.getText().toString().equals(password)){
                    Intent myIntent = new Intent(LoginActivity.this, HomeScreen.class);
                    startActivity(myIntent);
                }else{
                    errorText.setVisibility(View.VISIBLE);
                }
            }
        });

    }
}
