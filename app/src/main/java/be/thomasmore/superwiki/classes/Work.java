package be.thomasmore.superwiki.classes;

public class Work {

    private long id;
    private String base;
    private String occupation;

    public Work() {
    }

    public Work(long id, String base, String occupation) {
        this.id = id;
        this.base = base;
        this.occupation = occupation;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    @Override
    public String toString() {
        return "Work{" +
                "id=" + id +
                ", base='" + base + '\'' +
                ", occupation='" + occupation + '\'' +
                '}';
    }
}
