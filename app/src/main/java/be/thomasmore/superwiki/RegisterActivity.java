package be.thomasmore.superwiki;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import be.thomasmore.superwiki.helper.HttpReader;
import be.thomasmore.superwiki.helper.JsonHelper;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



    }
    public void register(View v){
        EditText username = (EditText)findViewById(R.id.usernameRegister);
        String usernameResult = username.getText().toString();

        EditText password = (EditText)findViewById(R.id.passwordRegister);
        String passwordResult = password.getText().toString();

        HttpReader httpReader = new HttpReader();
        httpReader.setOnResultReadyListener((new HttpReader.OnResultReadyListener() {
            @Override
            public void resultReady(String result) {
                JsonHelper jsonHelper = new JsonHelper();


                    /*SharedPreferences settings = getSharedPreferences("TokenPrefs", MODE_PRIVATE);
                    SharedPreferences.Editor prefEditor = settings.edit();
                    prefEditor.putString("token", newToken);
                    prefEditor.commit();*/



                    Intent intent = new Intent(getBaseContext(), LoginActivity.class);
                    startActivity(intent);

                    toon("Je account is succesvol aangemaakt!");




            }
        }));
        httpReader.execute("http://superwiki.dinvanwezemael.space/index.php/registreer/?naam="+ usernameResult +"&wachtwoord=" + passwordResult);
    }

    public void goToLogin(View v){
        Intent intent = new Intent(getBaseContext(), LoginActivity.class);
        startActivity(intent);
    }

    private void toon(String tekst)
    {
        Toast.makeText(getApplicationContext(), tekst, Toast.LENGTH_SHORT).show();
    }


}
