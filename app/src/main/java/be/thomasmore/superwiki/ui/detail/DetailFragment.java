package be.thomasmore.superwiki.ui.detail;

import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import be.thomasmore.superwiki.R;
import be.thomasmore.superwiki.classes.Appearance;
import be.thomasmore.superwiki.classes.Biography;
import be.thomasmore.superwiki.classes.Connection;
import be.thomasmore.superwiki.classes.Powerstat;
import be.thomasmore.superwiki.classes.Work;
import be.thomasmore.superwiki.helper.DatabaseHelper;
import be.thomasmore.superwiki.helper.HttpReader;
import be.thomasmore.superwiki.helper.JsonHelper;

import be.thomasmore.superwiki.classes.Character;

public class DetailFragment extends Fragment {

    public static DetailFragment newInstance() {
        return new DetailFragment();
    }

    DatabaseHelper db;
    Character character;
    Appearance appearance;
    Biography biography;
    Connection connection;
    Powerstat powerstat;
    Work work;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.detail_fragment, container, false);

        Bundle bundle = getArguments();


        db = new DatabaseHelper(getContext());

        if (bundle.getBoolean("db") == true) {
            getCharacterDetailsDb(bundle, view);
            setViews(view);
        } else {
            getCharacterDetails(bundle.getLong("characterId"), view);
        }
        Button save = (Button) view.findViewById(R.id.character_save);
        Button delete = (Button) view.findViewById(R.id.character_delete);
        save.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                saveCharacter(view);
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteCharacter(view);
            }
        });

        return view;
    }

    private void deleteCharacter(final View view) {
        SharedPreferences settings = getActivity().getSharedPreferences("TokenPrefs", Context.MODE_PRIVATE);
        String token = settings.getString("token", null);

        HttpReader httpReader = new HttpReader();
        httpReader.setOnResultReadyListener(new HttpReader.OnResultReadyListener() {
            @Override
            public void resultReady(String result) {
                db.deleteCharacter(character.getCharacterId());
                checkIfExists(view);
            }
        });
        toon("Favorite is deleted!");

        httpReader.execute("http://superwiki.dinvanwezemael.space/index.php/deletefavorite/?token=" + token.toString() + "&characterID=" + character.getCharacterId());
    }
    private void saveCharacter(final View view) {
        Integer characterId = Integer.parseInt(character.getCharacterId() + "");
        SharedPreferences settings = getActivity().getSharedPreferences("TokenPrefs", Context.MODE_PRIVATE);

        String token = settings.getString("token", null);
        db.insertCharacter(character, appearance, biography, connection, powerstat, work);
        HttpReader httpReader = new HttpReader();
        httpReader.setOnResultReadyListener(new HttpReader.OnResultReadyListener() {
            @Override
            public void resultReady(String result) {
                checkIfExists(view);
            }
        });
        toon("Favorite is added!");

        httpReader.execute("http://superwiki.dinvanwezemael.space/index.php/addfavorite/?token=" + token.toString() + "&characterID=" + characterId.toString());
    }

    private void getCharacterDetailsDb(Bundle bundle, View view) {
        character = db.getCharacter(bundle.getLong("id"));
        appearance = db.getAppearance(bundle.getLong("appearanceId"));
        biography = db.getBiography(bundle.getLong("biographyId"));
        connection = db.getConnection(bundle.getLong("connectionId"));
        powerstat = db.getPowerStat(bundle.getLong("powerstatId"));
        work = db.getWork(bundle.getLong("workId"));
        checkIfExists(view);

    }
    private void getCharacterDetails(Long id, final View view) {

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
                setViews(view);
                checkIfExists(view);

            }
        });

        httpReader.execute("http://www.superheroapi.com/api.php/145638653494882/" + id.toString());

    }

    private void setViews(View view) {
        setCharacterViews(view);
        setStatsViews(view);
        setBiographyViews(view);
        setAppearanceViews(view);
        setWorkViews(view);
        setConnectionViews(view);
    }

    private void setConnectionViews(View view) {
        TextView groupAffilation = (TextView) view.findViewById(R.id.connections_group_affiliation);
        TextView relatives = (TextView) view.findViewById(R.id.connections_relatives);

        groupAffilation.setText("Group affilation: " + connection.getGroupAffiliation());
        relatives.setText("Relatives: " + connection.getRelatives());
    }

    private void setWorkViews(View view) {
        TextView occupation = (TextView) view.findViewById(R.id.work_occupation);
        TextView base = (TextView) view.findViewById(R.id.work_base);

        occupation.setText("Occupation: " + work.getOccupation());
        base.setText("Base: " + work.getBase());
    }

    private void setAppearanceViews(View view) {
        TextView gender = (TextView) view.findViewById(R.id.appearance_gender);
        TextView race = (TextView) view.findViewById(R.id.appearance_race);
        TextView eyeColor = (TextView) view.findViewById(R.id.appearance_eye_color);
        TextView hairColor = (TextView) view.findViewById(R.id.appearance_hair_color);
        TextView height = (TextView) view.findViewById(R.id.appearance_height);
        TextView weight = (TextView) view.findViewById(R.id.appearance_weight);

        gender.setText("Gender: " + appearance.getGender());
        race.setText("Race: " + appearance.getRace());
        eyeColor.setText("Eye color: " + appearance.getEyeColor());
        hairColor.setText("Hair color: " + appearance.getHairColor());
        height.setText("Height: " + appearance.getHairColor());
        weight.setText("Weight: " + appearance.getWeight());
    }

    private void setBiographyViews(View view) {
        TextView fullName = (TextView) view.findViewById(R.id.biography_full_name);
        TextView placeOfBirth = (TextView) view.findViewById(R.id.biography_place_of_birth);
        TextView alterEgos = (TextView) view.findViewById(R.id.biography_alter_egos);
        TextView firstAppearance = (TextView) view.findViewById(R.id.biography_first_appearance);
        TextView publisher = (TextView) view.findViewById(R.id.biography_publisher);
        TextView alignment = (TextView) view.findViewById(R.id.biography_alignment);

        fullName.setText("Full name: " + biography.getFullName());
        placeOfBirth.setText("Place of birth: " + biography.getPlaceOfBirth());
        alterEgos.setText("Alter egos: " + biography.getAlterEgo());
        firstAppearance.setText("First appearance: " + biography.getFirstAppearance());
        publisher.setText("Publisher: " + biography.getPublisher());
        alignment.setText("Alignment: " + biography.getAlignment());
    }

    private void setStatsViews(View view) {
        TextView intelligence = (TextView) view.findViewById(R.id.stats_intelligence);
        TextView strength = (TextView) view.findViewById(R.id.stats_strength);
        TextView speed = (TextView) view.findViewById(R.id.stats_speed);
        TextView durability = (TextView) view.findViewById(R.id.stats_durability);
        TextView power = (TextView) view.findViewById(R.id.stats_power);
        TextView combat = (TextView) view.findViewById(R.id.stats_combat);

        intelligence.setText("Intelligence: " + powerstat.getIntelligence());
        strength.setText("Strength: " + powerstat.getStrength());
        speed.setText("Speed: " + powerstat.getSpeed().toString());
        durability.setText("Durability: " + powerstat.getDurability());
        power.setText("Power: " + powerstat.getPower());
        combat.setText("Combat: " + powerstat.getCombat());
    }

    private void setCharacterViews(View view) {
        TextView characterName = (TextView) view.findViewById(R.id.character_name);
        ImageView characterImg = (ImageView) view.findViewById(R.id.character_image);

        characterName.setText(character.getNaam());
        Picasso.get().load(character.getImageUrl()).into(characterImg);
    }

    private void toon(String tekst)
    {
        Toast.makeText(getContext(), tekst, Toast.LENGTH_LONG).show();
    }

    private void checkIfExists(View view) {

        Button saveButton = (Button) view.findViewById(R.id.character_save);
        Button deleteButton = (Button) view.findViewById(R.id.character_delete);
        if(db.getCharacterByCharacterId(character.getCharacterId()).getCharacterId()==0) {
            saveButton.setVisibility(View.VISIBLE);
            deleteButton.setVisibility(View.INVISIBLE);
        } else {
            saveButton.setVisibility(View.INVISIBLE);
            deleteButton.setVisibility(View.VISIBLE);
        }
    }

}
