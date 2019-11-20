package be.thomasmore.superwiki.helper;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import be.thomasmore.superwiki.classes.Character;

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
}
