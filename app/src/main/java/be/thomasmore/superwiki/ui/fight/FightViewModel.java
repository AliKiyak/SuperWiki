package be.thomasmore.superwiki.ui.fight;

import androidx.lifecycle.ViewModel;

import java.util.List;

import be.thomasmore.superwiki.classes.Character;
import be.thomasmore.superwiki.helper.DatabaseHelper;

public class FightViewModel extends ViewModel {

    public FightViewModel() { }

    public List<Character> getCharacters(DatabaseHelper db) {
        return db.getCharacters();
    }
}
