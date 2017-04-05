package tr.com.mis49m.sharedpreferences;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final String SHARED_PREF_LOGIN = "loginPref";
    private static final String KEY_USERNAME = "usernameKey";
    private static final String KEY_REMEMBER = "rememberKey";

    EditText txtUserName;
    Switch switchRemember;
    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //-- read references from ui
        txtUserName = (EditText) findViewById(R.id.txt_username);
        switchRemember = (Switch) findViewById(R.id.switch_remember);
        btnLogin = (Button) findViewById(R.id.btn_login);

        //-- get shared preferences for login
        SharedPreferences sharedPreferences = getSharedPreferences(
                SHARED_PREF_LOGIN, MODE_PRIVATE);
        //-- read remember parameter
        boolean isRemember = sharedPreferences.getBoolean(KEY_REMEMBER, false);
        //-- if isRemember selected, write user name
        if (isRemember)
            txtUserName.setText(sharedPreferences.getString(KEY_USERNAME, ""));
        //-- update switch value
        switchRemember.setChecked(isRemember);


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //-- get shared preferences for login
                SharedPreferences sharedPreferences = getSharedPreferences(
                        SHARED_PREF_LOGIN, MODE_PRIVATE);

                //-- open editor
                SharedPreferences.Editor editor = sharedPreferences.edit();

                //-- set values
                editor.putBoolean(KEY_REMEMBER, switchRemember.isChecked());
                if (switchRemember.isChecked())
                    editor.putString(KEY_USERNAME, txtUserName.getText().toString());
                else
                    editor.putString(KEY_USERNAME, "");

                //-- update shared preferences for login
                editor.commit();

                Toast.makeText(getApplicationContext(), "Hello", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
