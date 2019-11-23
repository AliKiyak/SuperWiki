package be.thomasmore.superwiki.ui.fight;

import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import be.thomasmore.superwiki.R;
import be.thomasmore.superwiki.classes.Character;
import be.thomasmore.superwiki.helper.DatabaseHelper;

public class ThirdFightFragment extends Fragment {


    Character winner;
    DatabaseHelper db;
    public static ThirdFightFragment newInstance() {
        return new ThirdFightFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        db = new DatabaseHelper(getContext());
        View view = inflater.inflate(R.layout.third_fight_fragment, container, false);
        Bundle bundle = getArguments();
        getWinner(bundle.getLong("winnerId"));
        setWinnerImage(view);

        Handler handler = new Handler();
        handler.postDelayed(
                new Runnable() {
                    public void run() {
                        goToMainScreen();                   }
                }, 5000);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // TODO: Use the ViewModel
    }

    public void getWinner(long id) {
        winner = db.getCharacter(id);
    }

    public void setWinnerImage(View v) {
        ImageView winnerImage = (ImageView)  v.findViewById(R.id.winner_image);

        Picasso.get().load(winner.getImageUrl()).into(winnerImage);
    }

    public void goToMainScreen() {
        FightFragment fightFragment = new FightFragment();
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.nav_host_fragment, fightFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

}
