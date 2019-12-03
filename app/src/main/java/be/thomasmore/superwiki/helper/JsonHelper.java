package be.thomasmore.superwiki.helper;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import be.thomasmore.superwiki.classes.Appearance;
import be.thomasmore.superwiki.classes.Biography;
import be.thomasmore.superwiki.classes.Character;
import be.thomasmore.superwiki.classes.Connection;
import be.thomasmore.superwiki.classes.Powerstat;
import be.thomasmore.superwiki.classes.Work;

public class JsonHelper {
    public List<Character> getCharacters(String jsonTekst) {
        List<Character> lijst = new ArrayList<>();

        try {
            JSONObject jsonResultaat = new JSONObject(jsonTekst);
            JSONArray jsonCharacters = jsonResultaat.getJSONArray("results");

            for (int i = 0; i < jsonCharacters.length(); i++) {
                JSONObject jsonCharacter = jsonCharacters.getJSONObject(i);

                Character character = new Character();

                character.setCharacterId(jsonCharacter.getLong("id"));
                character.setNaam(jsonCharacter.getString("name"));
                character.setImageUrl(jsonCharacter.getJSONObject("image").getString("url"));

                lijst.add(character);
            }

        }  catch (JSONException e) {
            Log.e("JSON Parser", "Error parsing data " + e.toString());
        }
        return lijst;
    }

    public Character getCharacter(String jsonTekst) {
        Character character = new Character();

        try {
            JSONObject jsonObjectCharacter = new JSONObject(jsonTekst);
            JSONObject jsonObjectImage = new JSONObject(jsonObjectCharacter.getString("image"));

            character.setNaam(jsonObjectCharacter.getString("name"));
            character.setCharacterId(jsonObjectCharacter.getLong("id"));
            character.setImageUrl(jsonObjectImage.getString("url"));

        }  catch (JSONException e) {
            Log.e("JSON Parser", "Error parsing data " + e.toString());
        }

        return character;
    }

    public Powerstat getStats(String jsonTekst) {
        Powerstat powerstat = new Powerstat();

        try {
            JSONObject jsonObjectCharacter = new JSONObject(jsonTekst);
            JSONObject jsonObjectStats = new JSONObject(jsonObjectCharacter.getString("powerstats"));

            powerstat.setCombat(Integer.parseInt(jsonObjectStats.getString("combat")));
            powerstat.setIntelligence(Integer.parseInt(jsonObjectStats.getString("intelligence")));
            powerstat.setStrength(Integer.parseInt(jsonObjectStats.getString("strength")));
            powerstat.setSpeed(Integer.parseInt(jsonObjectStats.getString("speed")));
            powerstat.setDurability(Integer.parseInt(jsonObjectStats.getString("durability")));
            powerstat.setPower(Integer.parseInt(jsonObjectStats.getString("power")));

        }  catch (JSONException e) {
            Log.e("JSON Parser", "Error parsing data " + e.toString());
        }
        return powerstat;
    }

    public Biography getBiography(String jsonTekst) {
        Biography biography = new Biography();

        try {
            JSONObject jsonObjectCharacter = new JSONObject(jsonTekst);
            JSONObject jsonObjectBiography = new JSONObject(jsonObjectCharacter.getString("biography"));

            biography.setFullName(jsonObjectBiography.getString("full-name"));
            biography.setAlterEgo(jsonObjectBiography.getString("alter-egos"));
            biography.setPlaceOfBirth(jsonObjectBiography.getString("place-of-birth"));
            biography.setFirstAppearance(jsonObjectBiography.getString("first-appearance"));
            biography.setPublisher(jsonObjectBiography.getString("publisher"));
            biography.setAlignment(jsonObjectBiography.getString("alignment"));


        }  catch (JSONException e) {
            Log.e("JSON Parser", "Error parsing data " + e.toString());
        }

        return biography;
    }

    public Appearance getAppearance(String jsonTekst) {
        Appearance appearance = new Appearance();

        try {
            JSONObject jsonObjectCharacter = new JSONObject(jsonTekst);
            JSONObject jsonObjectAppearance = new JSONObject(jsonObjectCharacter.getString("appearance"));

            appearance.setGender(jsonObjectAppearance.getString("gender"));
            appearance.setRace(jsonObjectAppearance.getString("race"));
            appearance.setEyeColor(jsonObjectAppearance.getString("eye-color"));
            appearance.setHairColor(jsonObjectAppearance.getString("hair-color"));


        }  catch (JSONException e) {
            Log.e("JSON Parser", "Error parsing data " + e.toString());
        }

        return appearance;
    }

    public Work getWork(String jsonTekst) {
        Work work = new Work();

        try {
            JSONObject jsonObjectCharacter = new JSONObject(jsonTekst);
            JSONObject jsonObjectWork = new JSONObject(jsonObjectCharacter.getString("work"));

            work.setOccupation(jsonObjectWork.getString("occupation"));
            work.setBase(jsonObjectWork.getString("base"));

        }  catch (JSONException e) {
            Log.e("JSON Parser", "Error parsing data " + e.toString());
        }

        return work;
    }

    public Connection getConnection(String jsonTekst) {
        Connection connection = new Connection();

        try {
            JSONObject jsonObjectCharacter = new JSONObject(jsonTekst);
            JSONObject jsonObjectConnections = new JSONObject(jsonObjectCharacter.getString("connections"));

            connection.setGroupAffiliation(jsonObjectConnections.getString("group-affiliation"));
            connection.setRelatives(jsonObjectConnections.getString("relatives"));

        }  catch (JSONException e) {
            Log.e("JSON Parser", "Error parsing data " + e.toString());
        }

        return connection;
    }
}
