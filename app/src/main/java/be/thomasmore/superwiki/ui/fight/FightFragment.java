package be.thomasmore.superwiki.ui.fight;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.List;

import be.thomasmore.superwiki.R;
import be.thomasmore.superwiki.classes.Character;
import be.thomasmore.superwiki.helper.DatabaseHelper;

public class FightFragment extends Fragment {

    DatabaseHelper db;
    List<Character> characters;

    public static FightFragment newInstance() {
        return new FightFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        db = new DatabaseHelper(getContext());
        View view = inflater.inflate(R.layout.fight_fragment, container, false);
        this.characters = getCharacters();
        ArrayAdapter<Character> characterAdapter =
                new ArrayAdapter<Character>(getContext(), R.layout.support_simple_spinner_dropdown_item, characters);
        final Spinner firstCharacterSpinner =
                (Spinner) view.findViewById(R.id.characterspinner1);
        final Spinner seoondCharacterSpinner =
                (Spinner) view.findViewById(R.id.characterspinner2);
        firstCharacterSpinner.setAdapter(characterAdapter);
        seoondCharacterSpinner.setAdapter(characterAdapter);

        return view;
    }

    public List<Character> getCharacters() {
        return db.getCharacters();
    }


}
