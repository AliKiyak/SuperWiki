package be.thomasmore.superwiki.classes;

import java.util.ArrayList;
import java.util.List;

public class Biography {

    private long id;
    private String fullName;
    private String alterEgo;
    private ArrayList<String> aliases;
    private String firstAppearance;
    private String publisher;
    private String alignment;
    private String placeOfBirth;

    public Biography() {
    }

    public Biography(long id, String fullName, String alterEgo, ArrayList<String> aliases, String firstAppearance, String publisher, String alignment, String placeOfBirth) {
        this.id = id;
        this.fullName = fullName;
        this.alterEgo = alterEgo;
        this.aliases = aliases;
        this.firstAppearance = firstAppearance;
        this.publisher = publisher;
        this.alignment = alignment;
        this.placeOfBirth = placeOfBirth;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAlterEgo() {
        return alterEgo;
    }

    public void setAlterEgo(String alterEgo) {
        this.alterEgo = alterEgo;
    }

    public ArrayList<String> getAliases() {
        return aliases;
    }

    public void setAliases(ArrayList<String>  alias) {
        this.aliases = aliases;
    }

    public String getFirstAppearance() {
        return firstAppearance;
    }

    public void setFirstAppearance(String firstAppearance) {
        this.firstAppearance = firstAppearance;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getAlignment() {
        return alignment;
    }

    public void setAlignment(String alignment) {
        this.alignment = alignment;
    }

    public String getPlaceOfBirth() {
        return placeOfBirth;
    }

    public void setPlaceOfBirth(String placeOfBirth) {
        this.placeOfBirth = placeOfBirth;
    }
}
