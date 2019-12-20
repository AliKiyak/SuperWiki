package be.thomasmore.superwiki.ui.fight;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;

import be.thomasmore.superwiki.R;
import be.thomasmore.superwiki.adapters.CharacterSpinnerAdapter;
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

        ((AppCompatActivity) getActivity()).getSupportActionBar().show();
        db = new DatabaseHelper(getContext());
        final View view = inflater.inflate(R.layout.fight_fragment, container, false);
        getCharacters();
        fillSpinners(view);

        Button fightButton = (Button) view.findViewById(R.id.fightbutton);

        fightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fightCharacters(view);
            }
        });

        return view;
    }

    public void getCharacters() {
        this.characters = db.getCharacters();
    }

    public void fillSpinners(View view) {
        //ArrayAdapter<Character> characterAdapter =
          //      new ArrayAdapter<Character>(getContext(), R.layout.support_simple_spinner_dropdown_item, characters);
        CharacterSpinnerAdapter characterAdapter =
                new CharacterSpinnerAdapter(getContext(), characters);
        final Spinner firstCharacterSpinner =
                (Spinner) view.findViewById(R.id.characterspinner1);
        final Spinner seoondCharacterSpinner =
                (Spinner) view.findViewById(R.id.characterspinner2);
        firstCharacterSpinner.setAdapter(characterAdapter);
        seoondCharacterSpinner.setAdapter(characterAdapter);
    }

    public void fightCharacters(View v) {
        Spinner spinner1 = (Spinner) v.findViewById(R.id.characterspinner1);
        Spinner spinner2 = (Spinner) v.findViewById(R.id.characterspinner2);

        Character character1 = (Character) spinner1.getSelectedItem();
        Character character2 = (Character) spinner2.getSelectedItem();

        //toon(character1.getNaam() + " V.S " + character2.getNaam());

        SecondFightFragment secondFightFragment = new SecondFightFragment();

        Bundle bundle = new Bundle();
        bundle.putLong("firstCharacterId", character1.getId());
        bundle.putLong("secondCharacterId", character2.getId());

        secondFightFragment.setArguments(bundle);

        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.nav_host_fragment, secondFightFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    private void toon(String tekst)
    {
        Toast.makeText(getContext(), tekst, Toast.LENGTH_SHORT).show();
    }

}
