package be.thomasmore.superwiki.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import be.thomasmore.superwiki.classes.Appearance;
import be.thomasmore.superwiki.classes.Biography;
import be.thomasmore.superwiki.classes.Character;
import be.thomasmore.superwiki.classes.Connection;
import be.thomasmore.superwiki.classes.Powerstat;
import be.thomasmore.superwiki.classes.Work;

public class DatabaseHelper extends SQLiteOpenHelper {

    //Versie
    private static final int DATABASE_VERSION = 6;

    //Naam van de database
    private static final String DATABASE_NAME = "superwiki";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE_BIOGRAPHY  = "CREATE TABLE biography (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "fullName TEXT," +
                "alterEgo TEXT," +
                "alias TEXT," +
                "firstAppearance TEXT," +
                "publisher TEXT," +
                "alignment TEXT," +
                "placeOfBirth TEXT)";
        db.execSQL(CREATE_TABLE_BIOGRAPHY);

        String CREATE_TABLE_APPEARANCE = "CREATE TABLE appearance (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "gender TEXT," +
                "race TEXT," +
                "height TEXT," +
                "weight TEXT," +
                "eyeColor TEXT," +
                "hairColor TEXT)";
        db.execSQL(CREATE_TABLE_APPEARANCE);

        String CREATE_TABLE_WORK = "CREATE TABLE work (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "base TEXT," +
                "occupation TEXT)";
        db.execSQL(CREATE_TABLE_WORK);

        String CREATE_TABLE_CONNECTION = "CREATE TABLE connection (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "groupAffiliation TEXT," +
                "relatives TEXT)";
        db.execSQL(CREATE_TABLE_CONNECTION);

        String CREATE_TABLE_POWERSTAT = "CREATE TABLE powerstat (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "intelligence INTEGER," +
                "strength INTEGER," +
                "speed INTEGER," +
                "durability INTEGER," +
                "power INTEGER," +
                "combat INTEGER)";
        db.execSQL(CREATE_TABLE_POWERSTAT);

        String CREATE_TABLE_CHARACTER = "CREATE TABLE character (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
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

    }

    private void initializeDatabase(SQLiteDatabase db) {
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

    private Boolean checkIfCharacterExist(Long characterId) {
        String selectQuery = "SELECT * FROM character WHERE characterId = " + characterId;

        Log.e("id", characterId.toString());
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        int aantal = cursor.getCount();

        cursor.close();
        db.close();

        if (aantal == 0) {
            return false;
        } else {
            return true;
        }

    }

    public void insertCharacter(Character character, Appearance appearance, Biography biography, Connection connection, Powerstat powerstat, Work work) {
        if (checkIfCharacterExist(character.getCharacterId()) == false) {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues valuesAppearance = new ContentValues();
            ContentValues valuesBiography = new ContentValues();
            ContentValues valuesConnection = new ContentValues();
            ContentValues valuesPowerstat = new ContentValues();
            ContentValues valuesWork = new ContentValues();
            ContentValues valuesCharacter = new ContentValues();

            valuesAppearance.put("gender", appearance.getGender());
            valuesAppearance.put("race", appearance.getRace());
            valuesAppearance.put("height", appearance.getHeight());
            valuesAppearance.put("weight", appearance.getWeight());
            valuesAppearance.put("eyeColor", appearance.getEyeColor());
            valuesAppearance.put("hairColor", appearance.getHairColor());

            long appearanceId = db.insert("appearance", null, valuesAppearance);

            valuesWork.put("occupation", work.getOccupation());
            valuesWork.put("base", work.getBase());

            long workId = db.insert("work", null, valuesWork);


            valuesBiography.put("fullName", biography.getFullName());
            valuesBiography.put("alterEgo", biography.getAlterEgo());
            valuesBiography.put("firstAppearance", biography.getFirstAppearance());
            valuesBiography.put("publisher", biography.getPublisher());
            valuesBiography.put("alignment", biography.getAlignment());
            valuesBiography.put("placeOfBirth", biography.getPlaceOfBirth());

            long biographyId = db.insert("biography", null, valuesBiography);


            valuesConnection.put("groupAffiliation", connection.getGroupAffiliation());
            valuesConnection.put("relatives", connection.getRelatives());

            long connectionId = db.insert("connection", null, valuesConnection);

            valuesPowerstat.put("intelligence", powerstat.getIntelligence());
            valuesPowerstat.put("strength", powerstat.getStrength());
            valuesPowerstat.put("speed", powerstat.getSpeed());
            valuesPowerstat.put("durability", powerstat.getDurability());
            valuesPowerstat.put("power", powerstat.getPower());
            valuesPowerstat.put("combat", powerstat.getCombat());

            long powerstatId = db.insert("powerstat", null, valuesPowerstat);


            valuesCharacter.put("name", character.getNaam());
            valuesCharacter.put("imageUrl", character.getImageUrl());
            valuesCharacter.put("appearanceId", appearanceId);
            valuesCharacter.put("workId", workId);
            valuesCharacter.put("biographyId", biographyId);
            valuesCharacter.put("connectionId", connectionId);
            valuesCharacter.put("powerstatId", powerstatId);
            valuesCharacter.put("characterId", character.getCharacterId());

            long id = db.insert("character", null, valuesCharacter);
            db.close();

        }


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
