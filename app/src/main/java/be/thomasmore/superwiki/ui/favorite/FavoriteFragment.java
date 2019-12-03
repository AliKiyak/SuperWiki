package be.thomasmore.superwiki.ui.favorite;

import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

import be.thomasmore.superwiki.R;
import be.thomasmore.superwiki.adapters.CharacterAdapter;
import be.thomasmore.superwiki.classes.Character;
import be.thomasmore.superwiki.helper.DatabaseHelper;
import be.thomasmore.superwiki.helper.HttpReader;
import be.thomasmore.superwiki.helper.JsonHelper;
import be.thomasmore.superwiki.ui.detail.DetailFragment;

public class FavoriteFragment extends Fragment {

    DatabaseHelper db;
    List<Character> characters;

    public static FavoriteFragment newInstance() {
        return new FavoriteFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view  = inflater.inflate(R.layout.favorite_fragment, container, false);
        db = new DatabaseHelper(getContext());
        getCharacters();
        useCharacterAdapter(view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    private void getCharacters() {
        characters = db.getCharacters();
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
                toonDetailCharacter(characters.get(position));
            }
        });

    }

    public void toonDetailCharacter(Character character) {
        DetailFragment detailFragment= new DetailFragment();

        Bundle bundle = new Bundle();
        bundle.putBoolean("db", true);
        bundle.putLong("id", character.getId());
        bundle.putLong("appearanceId", character.getAppearanceId());
        bundle.putLong("workId", character.getWorkId());
        bundle.putLong("biographyId", character.getBiographyId());
        bundle.putLong("connectionId", character.getConnectionId());
        bundle.putLong("powerstatId", character.getPowerstatId());

        detailFragment.setArguments(bundle);

        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.nav_host_fragment, detailFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

    }

}
