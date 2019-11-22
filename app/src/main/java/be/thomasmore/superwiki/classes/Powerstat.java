package be.thomasmore.superwiki.classes;

public class Powerstat {
    private long id;
    private Integer intelligence;
    private Integer strength;
    private Integer speed;
    private Integer durability;
    private Integer power;
    private Integer combat;

    public Powerstat() {
    }

    public Powerstat(Integer intelligence, Integer strength, Integer speed, Integer durability, Integer power, Integer combat) {
        this.intelligence = intelligence;
        this.strength = strength;
        this.speed = speed;
        this.durability = durability;
        this.power = power;
        this.combat = combat;
    }

    public Powerstat(long id, Integer intelligence, Integer strength, Integer speed, Integer durability, Integer power, Integer combat) {
        this.id = id;
        this.intelligence = intelligence;
        this.strength = strength;
        this.speed = speed;
        this.durability = durability;
        this.power = power;
        this.combat = combat;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Integer getIntelligence() { return intelligence; }

    public void setIntelligence(Integer intelligence) { this.intelligence = intelligence; }

    public Integer getStrength() { return strength; }

    public void setStrength(Integer strength) { this.strength = strength; }

    public Integer getSpeed() { return speed; }

    public void setSpeed(Integer speed) { this.speed = speed; }

    public Integer getDurability() { return durability; }

    public void setDurability(Integer durability) { this.durability = durability; }

    public Integer getPower() { return power; }

    public void setPower(Integer power) { this.power = power; }

    public Integer getCombat() { return combat; }

    public void setCombat(Integer combat) { this.combat = combat; }

    @Override
    public String toString() {
        return "Powerstat{" +
                "id=" + id +
                ", intelligence=" + intelligence +
                ", strength=" + strength +
                ", speed=" + speed +
                ", durability=" + durability +
                ", power=" + power +
                ", combat=" + combat +
                '}';
    }
}



