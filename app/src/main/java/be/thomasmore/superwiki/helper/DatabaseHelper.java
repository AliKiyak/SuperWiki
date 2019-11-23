package be.thomasmore.superwiki.helper;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import be.thomasmore.superwiki.classes.Character;
import be.thomasmore.superwiki.classes.Powerstat;

public class DatabaseHelper extends SQLiteOpenHelper {

    //Versie
    private static final int DATABASE_VERSION = 2;

    //Naam van de database
    private static final String DATABASE_NAME = "superwiki";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE_BIOGRAPHY  = "CREATE TABLE biography (" +
                "id INTEGER PRIMARY KEY," +
                "fullName TEXT," +
                "alterEgo TEXT," +
                "alias TEXT," +
                "firstAppearance TEXT," +
                "publisher TEXT," +
                "alignment TEXT," +
                "placeOfBirth TEXT)";
        db.execSQL(CREATE_TABLE_BIOGRAPHY);

        String CREATE_TABLE_APPEARANCE = "CREATE TABLE appearance (" +
                "id INTEGER PRIMARY KEY," +
                "gender TEXT," +
                "race TEXT," +
                "height TEXT," +
                "weight TEXT," +
                "eyeColor TEXT," +
                "hairColor TEXT)";
        db.execSQL(CREATE_TABLE_APPEARANCE);

        String CREATE_TABLE_WORK = "CREATE TABLE work (" +
                "id INTEGER PRIMARY KEY," +
                "base TEXT," +
                "occupation TEXT)";
        db.execSQL(CREATE_TABLE_WORK);

        String CREATE_TABLE_CONNECTION = "CREATE TABLE connection (" +
                "id INTEGER PRIMARY KEY," +
                "groupAffiliation TEXT," +
                "relatives TEXT)";
        db.execSQL(CREATE_TABLE_CONNECTION);

        String CREATE_TABLE_POWERSTAT = "CREATE TABLE powerstat (" +
                "id INTEGER PRIMARY KEY," +
                "intelligence INTEGER," +
                "strength INTEGER," +
                "speed INTEGER," +
                "durability INTEGER," +
                "power INTEGER," +
                "combat INTEGER)";
        db.execSQL(CREATE_TABLE_POWERSTAT);

        String CREATE_TABLE_CHARACTER = "CREATE TABLE character (" +
                "id INTEGER PRIMARY KEY," +
                "characterId INTEGER," +
                "name TEXT," +
                "biographyId INTEGER," +
                "appearanceId INTEGER," +
                "workId INTEGER," +
                "connectionId INTEGER," +
                "powerstatId INTEGER," +
                "imageUrl TEXT," +
                "FOREIGN KEY (biographyId) REFERENCES biography(id)," +
                "FOREIGN KEY (appearanceId) REFERENCES appearance(id)," +
                "FOREIGN KEY (workId) REFERENCES work(id)," +
                "FOREIGN KEY (connectionId) REFERENCES connection(id)," +
                "FOREIGN KEY (powerstatId) REFERENCES powerstat(id))";

        db.execSQL(CREATE_TABLE_CHARACTER);

        insertCharacter(db);
    }

    private void insertCharacter(SQLiteDatabase db) {
        db.execSQL("INSERT INTO biography (id, fullName, alterEgo, alias, firstAppearance, publisher, alignment, placeOfBirth) VALUES ('1', 'Jack Napier', 'No alter egos found.', 'Red Hood I', 'Batman #1 (Spring 1940)', 'DC Comics', 'bad', '-' )");
        db.execSQL("INSERT INTO appearance (id, gender, race, height, weight, eyeColor, hairColor) VALUES ('1', 'Male', 'Human', '196 cm', '86kg', 'Green', 'Green')");
        db.execSQL("INSERT INTO work (id, occupation, base) VALUES ('1', '-', 'Arkham Asylum, Gotham City; Ha-Hacienda')");
        db.execSQL("INSERT INTO connection (id, groupAffiliation, relatives) VALUES ('1', 'Black Glove, Injustice Gang, Injustice League, Joker League of Anarchy', 'Jeannie (wife, deceased); Unborn son (deceased); Melvin Reipan (cousin, deceased)')");
        db.execSQL("INSERT INTO powerstat (id, intelligence, strength, speed, durability, power, combat) VALUES ('1', '100', '10', '12', '60', '43', '70')");
        db.execSQL("INSERT INTO character (id, characterId, name, biographyId, appearanceId, workId, connectionId,powerstatId, imageUrl) VALUES ('1', '370', 'Joker', '1', '1', '1', '1', '1', 'https://www.superherodb.com/pictures2/portraits/10/100/719.jpg')");

        db.execSQL("INSERT INTO biography (id, fullName, alterEgo, alias, firstAppearance, publisher, alignment, placeOfBirth) VALUES ('2', 'Clark Kent', 'Superman Prime One-Million', 'The Man of Steel', 'ACTION COMICS #1', 'Superman Prime One-Million', 'good', 'Krypton' )");
        db.execSQL("INSERT INTO appearance (id, gender, race, height, weight, eyeColor, hairColor) VALUES ('2', 'Male', 'Kyptonian', '191 cm', '101 kg', 'Blue', 'Black')");
        db.execSQL("INSERT INTO work (id, occupation, base) VALUES ('2', 'Reporter for the Daily Planet and novelist', 'Metropolis')");
        db.execSQL("INSERT INTO connection (id, groupAffiliation, relatives) VALUES ('2', 'Justice League of America, The Legion of Super-Heroes (pre-Crisis as Superboy); Justice Society of America (pre-Crisis Earth-2 version); All-Star Squadron (pre-Crisis Earth-2 version)', 'Lois Lane (wife), Jor-El (father, deceased), Lara (mother, deceased), Jonathan Kent (adoptive father), Martha Kent (adoptive mother), Seyg-El (paternal grandfather, deceased), Zor-El (uncle, deceased), Alura (aunt, deceased), Supergirl (Kara Zor-El, cousin), Superboy (Kon-El/Conner Kent, partial clone)')");
        db.execSQL("INSERT INTO powerstat (id, intelligence, strength, speed, durability, power, combat) VALUES ('2', '94', '100', '100', '100', '100', '85')");
        db.execSQL("INSERT INTO character (id, characterId, name, biographyId, appearanceId, workId, connectionId,powerstatId, imageUrl) VALUES ('2', '64', 'Superman', '2', '2', '2', '2', '2', 'https://www.superherodb.com/pictures2/portraits/10/100/791.jpg')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS character");
        db.execSQL("DROP TABLE IF EXISTS work");
        db.execSQL("DROP TABLE IF EXISTS connection");
        db.execSQL("DROP TABLE IF EXISTS powerstat");
        db.execSQL("DROP TABLE IF EXISTS appearance");
        db.execSQL("DROP TABLE IF EXISTS biography");
    }

    public List<Character> getCharacters() {
        List<Character> characters = new ArrayList<>();

        String selectQuery = "SELECT * FROM character ORDER BY name";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Character character = new Character(cursor.getLong(0), cursor.getLong(1), cursor.getString(2));
                characters.add(character);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return characters;
    }

    public Character getCharacter(long id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(
          "character",
          new String[] {"id", "name", "powerstatId", "imageUrl"},
          "id = ?",
          new String[] { String.valueOf(id) },
          null,
          null,
          null,
          null
        );

        Character character = new Character();

        if(cursor.moveToFirst()) {
            character = new Character(cursor.getLong(0),
                    cursor.getString(1),
                    cursor.getLong(2),
                    cursor.getString(3));
        }

        cursor.close();
        db.close();
        return character;
    }

    public Powerstat getPowerStat(long id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(
                "powerstat",
                new String[] {"id", "intelligence", "strength", "speed", "durability", "power", "combat" },
                "id = ?",
                new String[] { String.valueOf(id) },
                null,
                null,
                null,
                null
        );

        Powerstat powerstat = new Powerstat();

        if (cursor.moveToFirst()) {
            powerstat = new Powerstat(cursor.getLong(0),
                    cursor.getInt(1),
                    cursor.getInt(2),
                    cursor.getInt(3),
                    cursor.getInt(4),
                    cursor.getInt(5),
                    cursor.getInt(6));
        }
        cursor.close();
        db.close();
        return powerstat;
    }
}
