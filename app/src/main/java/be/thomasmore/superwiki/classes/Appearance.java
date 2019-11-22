package be.thomasmore.superwiki.classes;

import java.util.ArrayList;
import java.util.List;

public class Appearance {

    private long id;
    private String gender;
    private String race;
    private ArrayList<String> height;
    private List<String>  weight;
    private String eyeColor;
    private String hairColor;

    public Appearance() {
    }

    public Appearance(long id, String gender, String race, ArrayList<String> height, ArrayList<String> weight, String eyeColor, String hairColor) {
        this.id = id;
        this.gender = gender;
        this.race = race;
        this.height = height;
        this.weight = weight;
        this.eyeColor = eyeColor;
        this.hairColor = hairColor;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public ArrayList<String> getHeight() {
        return height;
    }

    public void setHeight(ArrayList<String> height) {
        this.height = height;
    }

    public List<String> getWeight() {
        return weight;
    }

    public void setWeight(List<String> weight) {
        this.weight = weight;
    }

    public String getEyeColor() {
        return eyeColor;
    }

    public void setEyeColor(String eyeColor) {
        this.eyeColor = eyeColor;
    }

    public String getHairColor() {
        return hairColor;
    }

    public void setHairColor(String hairColor) {
        this.hairColor = hairColor;
    }
}
