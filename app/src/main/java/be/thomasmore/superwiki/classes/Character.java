package be.thomasmore.superwiki.classes;

import androidx.annotation.NonNull;

public class Character {
    private long id;
    private long characterId;
    private String naam;
    private long appearanceId;
    private long workId;
    private long connectionId;
    private long powerstatId;
    private String imageUrl;

    public Character() {}

    public Character(long id, long characterId, String naam) {
        this.id = id;
        this.characterId = characterId;
        this.naam = naam;
    }
    public Character(long characterId, String naam, long appearanceId, long workId, long connectionId, long powerstatId, String imageUrl) {
        this.characterId = characterId;
        this.naam = naam;
        this.appearanceId = appearanceId;
        this.workId = workId;
        this.connectionId = connectionId;
        this.powerstatId = powerstatId;
        this.imageUrl = imageUrl;
    }

    public Character(long id, long characterId, String naam, long appearanceId, long workId, long connectionId, long powerstatId, String imageUrl) {
        this.id = id;
        this.characterId = characterId;
        this.naam = naam;
        this.appearanceId = appearanceId;
        this.workId = workId;
        this.connectionId = connectionId;
        this.powerstatId = powerstatId;
        this.imageUrl = imageUrl;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getCharacterId() {
        return characterId;
    }

    public void setCharacterId(long characterId) {
        this.characterId = characterId;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public long getAppearanceId() { return appearanceId; }

    public void setAppearanceId(long appearanceId) { this.appearanceId = appearanceId; }

    public long getWorkId() { return workId; }

    public void setWorkId(long workId) { this.workId = workId; }

    public long getConnectionId() { return connectionId; }

    public void setConnectionId(long connectionId) {this.connectionId = connectionId;}

    public long getPowerstatId() { return powerstatId; }

    public void setPowerstatId(long powerstatId) { this.powerstatId = powerstatId; }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @NonNull
    @Override
    public String toString() {
        return naam;
    }
}
