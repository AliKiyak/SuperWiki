package be.thomasmore.superwiki.ui.search;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import be.thomasmore.superwiki.R;
import be.thomasmore.superwiki.adapters.CharacterAdapter;
import be.thomasmore.superwiki.classes.Character;
import be.thomasmore.superwiki.helper.DatabaseHelper;
import be.thomasmore.superwiki.helper.HttpReader;
import be.thomasmore.superwiki.helper.JsonHelper;

public class SearchFragment extends Fragment {

    DatabaseHelper db;
    List<Character> characters;

    public static SearchFragment newInstance() {
        return new SearchFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.search_fragment, container, false);

        final EditText zoekTekst = (EditText) view.findViewById(R.id.zoektekst);

        zoekTekst.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                getCharacters(zoekTekst.getText().toString());
                Handler handler = new Handler();
                handler.postDelayed(
                        new Runnable() {
                            public void run() {
                                useCharacterAdapter(view);
                            }
                        }, 2000);
                toon(zoekTekst.getText().toString());
            }
            @Override
            public void afterTextChanged(Editable s) { }
        });

        return view;
    }

    private void getCharacters(String zoektekst) {
        HttpReader httpReader = new HttpReader();
        httpReader.setOnResultReadyListener(new HttpReader.OnResultReadyListener() {
            @Override
            public void resultReady(String result) {
                JsonHelper jsonHelper = new JsonHelper();
                characters = jsonHelper.getCharacters(result);
            }
        });

        httpReader.execute("http://www.superheroapi.com/api.php/145638653494882/search/" + zoektekst);


    }

    private void useCharacterAdapter(View view) {
        CharacterAdapter characterAdapter =
                new CharacterAdapter(getContext(), characters);

        final ListView listViewCharacters =
                (ListView) view.findViewById(R.id.listViewCharacters);
        listViewCharacters.setAdapter(characterAdapter);

    }
    private void toon(String tekst)
    {
        Toast.makeText(getContext(), tekst, Toast.LENGTH_SHORT).show();
    }


}
