package be.thomasmore.superwiki.ui.fight;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.media.Image;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.os.Vibrator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import be.thomasmore.superwiki.R;
import be.thomasmore.superwiki.classes.Character;
import be.thomasmore.superwiki.classes.Powerstat;
import be.thomasmore.superwiki.helper.DatabaseHelper;

public class SecondFightFragment extends Fragment {

    DatabaseHelper db;
    Character character1;
    Character character2;
    Powerstat powerstat1;
    Powerstat powerstat2;
    long winnerId;
    public static SecondFightFragment newInstance() {
        return new SecondFightFragment();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putLong("character1", character1.getId());
        outState.putLong("character2", character2.getId());
        super.onSaveInstanceState(outState);
    }
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        setRetainInstance(true);
        db = new DatabaseHelper(getContext());
        getActivity();
        final View view = inflater.inflate(R.layout.second_fight_fragment, container, false);
        Bundle bundle = getArguments();
        getCharacters(bundle.getLong("firstCharacterId"), bundle.getLong("secondCharacterId"));
        initializePictures(view);
        winnerId = calculateWinner();
        Vibrator v = (Vibrator) getContext().getSystemService(Context.VIBRATOR_SERVICE);
        v.vibrate(5000);
        Handler handler = new Handler();
        handler.postDelayed(
                new Runnable() {
                    public void run() {
                        showWinner();                    }
                }, 5000);

        return view;
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    public void getCharacters(long firstCharacterId, long secondCharacterId) {
        character1 = db.getCharacter(firstCharacterId);
        character2 = db.getCharacter(secondCharacterId);
    }
    public void initializePictures(View v) {
        ImageView firstCharacterPicture = (ImageView) v.findViewById(R.id.first_character_image);
        ImageView secondCharacterPicture = (ImageView) v.findViewById(R.id.second_character_image);

        Picasso.get().load(character1.getImageUrl()).into(firstCharacterPicture);
        Picasso.get().load(character2.getImageUrl()).into(secondCharacterPicture);
    }
    public long calculateWinner() {
        powerstat1 = db.getPowerStat(character1.getPowerstatId());
        powerstat2 = db.getPowerStat(character2.getPowerstatId());

        if (calculateTotalPowerstat(powerstat1) > calculateTotalPowerstat(powerstat2)) {
            return character1.getId();
        } else {
            return character2.getId();
        }
    }
    public int calculateTotalPowerstat(Powerstat powerstat) {
        return powerstat.getStrength() + powerstat.getSpeed() + powerstat.getPower() +
                powerstat.getIntelligence() + powerstat.getCombat() + powerstat.getDurability();
    }
    public void showWinner() {
        ThirdFightFragment thirdFightFragment = new ThirdFightFragment();
        Bundle bundle = new Bundle();
        bundle.putLong("winnerId", winnerId);
        thirdFightFragment.setArguments(bundle);
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.nav_host_fragment, thirdFightFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

}
