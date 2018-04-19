package team31.cs2340.gatech.sheltermap;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ForgotPasswordActivity extends AppCompatActivity {

    private EditText email;
    private Button send;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        email = (EditText) findViewById(R.id.email);
        send = (Button) findViewById(R.id.send);

        final TextView invalidEmail = (TextView) findViewById(R.id.invalidEmail);
        final TextView validEmail = (TextView) findViewById(R.id.validEmail);

        /******************************************************************************************/

        send.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                boolean fail = true;
                for(User u: User.users){
                    if(u.getEmail().equals(email.getText().toString())){

                        new SendMail().execute(u.getEmail(), u.getPassword());

                        fail = false;
                        invalidEmail.setVisibility(View.INVISIBLE);
                        validEmail.setVisibility(View.VISIBLE);
                    }
                }
                if(fail) {
                    //login fail
                    validEmail.setVisibility(View.INVISIBLE);
                    invalidEmail.setVisibility(View.VISIBLE);
                }
            }
        });
    }

}

class SendMail extends AsyncTask<String, Integer, Void> {

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
    }

    @Override
    protected Void doInBackground(String... params) {
        Mail m = new Mail("CS2340Team31@gmail.com", "Hello123!");

        String[] toArr = {params[0], "CS2340Team31@gmail.com"};
        m.setTo(toArr);
        m.setFrom("CS2340Team31@gmail.com");
        m.setSubject("Shelter Mapp Password Recovery");
        m.setBody("Your password is: " + params[1]);

        try {
            if(m.send()) {
                Log.e("MailApp", "sent email");
            } else {
                Log.e("MailApp", "email not sent");
            }
        } catch(Exception e) {
            Log.e("MailApp", "Could not send email", e);
        }
        return null;
    }
}
