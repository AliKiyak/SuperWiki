package be.thomasmore.superwiki;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import be.thomasmore.superwiki.classes.Character;
import be.thomasmore.superwiki.classes.Appearance;
import be.thomasmore.superwiki.classes.Biography;
import be.thomasmore.superwiki.classes.Powerstat;
import be.thomasmore.superwiki.classes.Work;
import be.thomasmore.superwiki.helper.DatabaseHelper;
import be.thomasmore.superwiki.helper.HttpReader;
import be.thomasmore.superwiki.helper.JsonHelper;
import be.thomasmore.superwiki.classes.Connection;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    DatabaseHelper db;
    Character character;
    Appearance appearance;
    Biography biography;
    Connection connection;
    Powerstat powerstat;
    Work work;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_fight, R.id.nav_search, R.id.nav_info, R.id.nav_favorite, R.id.nav_logout)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        isLoggedIn();
        SharedPreferences settings = getSharedPreferences("TokenPrefs", MODE_PRIVATE);

        String token = settings.getString("token", null);
        //toon(token);

        db = new DatabaseHelper(getApplicationContext());

        getfavoritesFromUser(token);

    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    private void isLoggedIn(){
        SharedPreferences settings = getSharedPreferences("TokenPrefs", MODE_PRIVATE);

        SharedPreferences.Editor editor = settings.edit();

        /*editor.clear();
        editor.commit();*/

        if(!settings.contains("initialized")){
            Intent intent = new Intent(getBaseContext(), LoginActivity.class);
            startActivity(intent);
        }
    }

    private void getfavoritesFromUser(String token){
        HttpReader httpReader = new HttpReader();
        httpReader.setOnResultReadyListener((new HttpReader.OnResultReadyListener() {
            @Override
            public void resultReady(String result) {
                JsonHelper jsonHelper = new JsonHelper();


                getFavorites(result);

            }
        }));
        httpReader.execute("http://superwiki.dinvanwezemael.space/index.php/getfavorites/?token=" + token);
    }



    public List<Integer> getFavorites(String jsonTekst){
        List<Integer> getFavorites = new ArrayList<Integer>();

        try{
            JSONArray jsonArrayID = new JSONArray(jsonTekst);

            String tekst;
            for (int i = 0; i < jsonArrayID.length(); i++){
                JSONObject jsonObjectfav = jsonArrayID.getJSONObject(i);

                getFavorites.add(jsonObjectfav.getInt("characterID"));


                getCharacterDetails(jsonObjectfav.getLong("characterID"));
            }
        }catch (JSONException e){

        }

        return getFavorites;
    }


    private void getCharacterDetails(Long id) {

        HttpReader httpReader = new HttpReader();
        httpReader.setOnResultReadyListener(new HttpReader.OnResultReadyListener() {
            @Override
            public void resultReady(String result) {
                JsonHelper jsonHelper = new JsonHelper();
                character = jsonHelper.getCharacter(result);
                powerstat = jsonHelper.getStats(result);
                appearance = jsonHelper.getAppearance(result);
                biography = jsonHelper.getBiography(result);
                connection = jsonHelper.getConnection(result);
                work = jsonHelper.getWork(result);
                db.insertCharacter(character, appearance, biography, connection, powerstat, work);

            }
        });

        //insertCharacter();

        httpReader.execute("http://www.superheroapi.com/api.php/145638653494882/" + id.toString());

    }

    private void insertCharacter(){
        db.insertCharacter(character, appearance, biography, connection, powerstat, work);
    }
    private void toon(String tekst)
    {
        Toast.makeText(getApplicationContext(), tekst, Toast.LENGTH_SHORT).show();
    }

/*
        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0); // 0 - for private mode
        SharedPreferences.Editor editor = pref.edit();

        editor.remove("initialized");
        editor.remove("token");

        editor.commit();

*/
}
