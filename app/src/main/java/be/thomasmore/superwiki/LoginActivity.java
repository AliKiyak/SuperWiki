package be.thomasmore.superwiki;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import be.thomasmore.superwiki.helper.HttpReader;
import be.thomasmore.superwiki.helper.JsonHelper;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        isLoggedIn();

        setContentView(R.layout.activity_login);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);





    }

    private void isLoggedIn(){
        SharedPreferences settings = getSharedPreferences("TokenPrefs", MODE_PRIVATE);

        if(settings.contains("initialized")){
            Intent intent = new Intent(getBaseContext(), MainActivity.class);
            startActivity(intent);
        }
    }

    private void setLocalStorage(String token){
        SharedPreferences settings = getSharedPreferences("TokenPrefs", MODE_PRIVATE);
        SharedPreferences.Editor prefEditor = settings.edit();


        prefEditor.remove("token");
        prefEditor.remove("initialized");

        prefEditor.commit();

        prefEditor.putString("token", token);
        prefEditor.putBoolean("initialized", true);
        prefEditor.commit();


    }

    public void login(View v){
        EditText username = (EditText)findViewById(R.id.username);
        String usernameResult = username.getText().toString();

        EditText password = (EditText)findViewById(R.id.password);
        String passwordResult = password.getText().toString();

        HttpReader httpReader = new HttpReader();
        httpReader.setOnResultReadyListener((new HttpReader.OnResultReadyListener() {
            @Override
            public void resultReady(String result) {
                JsonHelper jsonHelper = new JsonHelper();
                String token = result.toString();

                String newToken = token.substring(1, token.length()-2);



                if(!token.contains("\"false\"")){

                    setLocalStorage(newToken);

                    Intent intent = new Intent(getBaseContext(), MainActivity.class);
                    startActivity(intent);

                }else{
                    toon("Foute login");
                }



            }
        }));
        httpReader.execute("http://superwiki.dinvanwezemael.space/index.php/login/?naam=" + usernameResult + "&wachtwoord=" + passwordResult);
    }

    public void goToRegister(View v){
        Intent intent = new Intent(getBaseContext(), RegisterActivity.class);
        startActivity(intent);
    }

    private void toon(String tekst)
    {
        Toast.makeText(getApplicationContext(), tekst, Toast.LENGTH_SHORT).show();
    }


}
