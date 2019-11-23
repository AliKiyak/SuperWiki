package be.thomasmore.superwiki.ui.search;

import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
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
import android.widget.AdapterView;
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
import be.thomasmore.superwiki.ui.detail.DetailFragment;

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
                        }, 3000);
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

    public void toonDetailCharacter(long id) {
        DetailFragment detailFragment= new DetailFragment();

        Bundle bundle = new Bundle();
        bundle.putLong("characterId", id);

        detailFragment.setArguments(bundle);

        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.nav_host_fragment, detailFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

    }

    private void useCharacterAdapter(View view) {
        CharacterAdapter characterAdapter =
                new CharacterAdapter(getContext(), characters);

        final ListView listViewCharacters =
                (ListView) view.findViewById(R.id.listViewCharacters);
        listViewCharacters.setAdapter(characterAdapter);

        listViewCharacters.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parentView, View childView, int position, long id) {
                toonDetailCharacter(characters.get(position).getCharacterId());
            }
        });

    }
    private void toon(String tekst)
    {
        Toast.makeText(getContext(), tekst, Toast.LENGTH_SHORT).show();
    }


}
