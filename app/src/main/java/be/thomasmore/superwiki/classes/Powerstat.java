package be.thomasmore.superwiki.classes;

public class Powerstat {
    private long id;
    private long intelligence;
    private long strength;
    private long speed;
    private long durability;
    private long power;
    private long combat;

    public Powerstat() {}

    public Powerstat(long intelligence, long strength, long speed, long durability, long power, long combat) {
        this.intelligence = intelligence;
        this.strength = strength;
        this.speed = speed;
        this.durability = durability;
        this.power = power;
        this.combat = combat;
    }

    public Powerstat(long id, long intelligence, long strength, long speed, long durability, long power, long combat) {
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

    public long getIntelligence() {
        return intelligence;
    }

    public void setIntelligence(long intelligence) {
        this.intelligence = intelligence;
    }

    public long getStrength() {
        return strength;
    }

    public void setStrength(long strength) {
        this.strength = strength;
    }

    public long getSpeed() {
        return speed;
    }

    public void setSpeed(long speed) {
        this.speed = speed;
    }

    public long getDurability() {
        return durability;
    }

    public void setDurability(long durability) {
        this.durability = durability;
    }

    public long getPower() {
        return power;
    }

    public void setPower(long power) {
        this.power = power;
    }

    public long getCombat() {
        return combat;
    }

    public void setCombat(long combat) {
        this.combat = combat;
    }
}
