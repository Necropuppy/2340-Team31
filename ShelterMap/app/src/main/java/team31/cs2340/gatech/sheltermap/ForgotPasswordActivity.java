package team31.cs2340.gatech.sheltermap;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
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
        Button cancel;

        email = (EditText) findViewById(R.id.email);
        cancel = (Button) findViewById(R.id.cancel);
        cancel.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {
                //old way of returning (created new activity)
                    //Intent go2Welcome = new Intent(getApplicationContext(), WelcomeActivity.class);
                    //startActivity(go2Welcome);
                //use onBackPressed to remove multiple instances
                ForgotPasswordActivity.super.onBackPressed();
            }
        });
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
                        //invalidEmail.setVisibility(View.INVISIBLE);
                        //validEmail.setVisibility(View.VISIBLE);
                        Context context = getApplicationContext();
                        CharSequence text = "Password Sent To Email!";
                        int duration = Toast.LENGTH_SHORT;

                        Toast toast = Toast.makeText(context, text, duration);
                        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 1100);
                        toast.show();
                        ForgotPasswordActivity.super.onBackPressed();
                    }
                }
                if(fail) {
                    //login fail
                    //validEmail.setVisibility(View.INVISIBLE);
                    //invalidEmail.setVisibility(View.VISIBLE);
                    Context context = getApplicationContext();
                    CharSequence text = "Invalid Email!";
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.setGravity(Gravity.CENTER_VERTICAL, 0, 1100);
                    toast.show();
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
