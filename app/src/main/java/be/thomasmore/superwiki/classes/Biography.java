package be.thomasmore.superwiki.classes;

public class Biography {

    private long id;
    private String fullName;
    private String alterEgo;
    private String alias;
    private String firstAppearance;
    private String publisher;
    private String alignment;
    private String placeOfBirth;

    public Biography() {
    }

    public Biography(long id, String fullName, String alterEgo, String alias, String firstAppearance, String publisher, String alignment, String placeOfBirth) {
        this.id = id;
        this.fullName = fullName;
        this.alterEgo = alterEgo;
        this.alias = alias;
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

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
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
