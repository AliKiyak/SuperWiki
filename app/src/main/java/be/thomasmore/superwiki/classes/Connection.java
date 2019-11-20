package be.thomasmore.superwiki.classes;

public class Connection {
    private long id;
    private String groupAffiliation;
    private String relatives;

    public Connection() {}

    public Connection(String groupAffiliation, String relatives) {
        this.groupAffiliation = groupAffiliation;
        this.relatives = relatives;
    }

    public Connection(long id, String groupAffiliation, String relatives) {
        this.id = id;
        this.groupAffiliation = groupAffiliation;
        this.relatives = relatives;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getGroupAffiliation() {
        return groupAffiliation;
    }

    public void setGroupAffiliation(String groupAffiliation) {
        this.groupAffiliation = groupAffiliation;
    }

    public String getRelatives() {
        return relatives;
    }

    public void setRelatives(String relatives) {
        this.relatives = relatives;
    }
}
