package org.major.Entities;

public class BlackICE {
    private final String iceType;
    private final int perception;
    private final int speed;
    private final int attack;
    private final int defense;
    private final int maxRez;

    private int currentRez;
    private boolean active = true;

    public BlackICE(String iceType, int perception, int speed, int attack, int defense, int rez) {
        this.iceType = iceType;
        this.perception = perception;
        this.speed = speed;
        this.attack = attack;
        this.defense = defense;
        this.maxRez = rez;
        this.currentRez = rez;
    }

    // Getters
    public String getIceType() { return iceType; }
    public int getPerception() { return perception; }
    public int getSpeed() { return speed; }
    public int getAttack() { return attack; }
    public int getDefense() { return defense; }
    public int getCurrentRez() { return currentRez; }
    public int getMaxRez() { return maxRez; }
    public boolean isActive() { return active; }

    // Combat logic
    public void takeDamage(int damage) {
        int finalDamage = Math.max(0, damage - defense);
        currentRez -= finalDamage;

        if (currentRez <= 0) {
            active = false;
            currentRez = 0;
        }
    }

    public String getStatus() {
        return active
                ? "🧠 " + iceType + " — REZ: " + currentRez + "/" + maxRez
                : "☠️ " + iceType + " — DEACTIVATED";
    }
}
