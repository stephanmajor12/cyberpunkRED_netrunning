package org.major.Essentials;

import java.util.List;

public class FloorNode {
    private final String type;
    private final int dv;
    private final String password;
    private final String iceType;
    private final int initiative;
    private final List<String> programs;
    private final List<String> controls;
    private final String data;

    // Black ICE stats
    private final int perception;
    private final int speed;
    private final int attack;
    private final int defense;
    private final int rez;

    // NEW fields for Eye-Dee
    private final String worth;
    private final String content;

    public FloorNode(
            String type,
            int dv,
            String password,
            String iceType,
            int initiative,
            List<String> programs,
            List<String> controls,
            String data,
            int perception,
            int speed,
            int attack,
            int defense,
            int rez,
            String worth,
            String content
    ) {
        this.type = type;
        this.dv = dv;
        this.password = password;
        this.iceType = iceType;
        this.initiative = initiative;
        this.programs = programs;
        this.controls = controls;
        this.data = data;
        this.perception = perception;
        this.speed = speed;
        this.attack = attack;
        this.defense = defense;
        this.rez = rez;
        this.worth = worth;
        this.content = content;
    }

    public String getType() { return type; }
    public int getDv() { return dv; }
    public String getPassword() { return password; }
    public String getIceType() { return iceType; }
    public int getInitiative() { return initiative; }
    public List<String> getPrograms() { return programs; }
    public List<String> getControls() { return controls; }
    public String getData() { return data; }

    //BLACK-ICE
    public int getPerception() { return perception; }
    public int getSpeed() { return speed; }
    public int getAttack() { return attack; }
    public int getDefense() { return defense; }
    public int getRez() { return rez; }


    public String getWorth() { return worth; }
    public String getContent() { return content; }

    public String getAllInfo() {
        StringBuilder sb = new StringBuilder();
        sb.append("Type: ").append(type).append("\n");

        if (dv >= 0) sb.append("  DV: ").append(dv).append("\n");
        if (password != null) sb.append("  Password: ").append(password).append("\n");
        if (iceType != null) sb.append("  ICE Type: ").append(iceType).append("\n");
        if (initiative >= 0) sb.append("  Initiative: ").append(initiative).append("\n");

        if (programs != null && !programs.isEmpty()) {
            sb.append("  Programs: ").append(String.join(", ", programs)).append("\n");
        }

        if (controls != null && !controls.isEmpty()) {
            sb.append("  Controls: ").append(String.join(", ", controls)).append("\n");
        }

        if (data != null) {
            sb.append("  Data: ").append(data).append("\n");
        }

        if ("BLACK_ICE".equalsIgnoreCase(type)) {
            sb.append("  Perception: ").append(perception).append("\n");
            sb.append("  Speed: ").append(speed).append("\n");
            sb.append("  Attack: ").append(attack).append("\n");
            sb.append("  Defense: ").append(defense).append("\n");
            sb.append("  Rez: ").append(rez).append("\n");
        }

        return sb.toString().trim();
    }

    public String getInfo() {
        return "Type: " + type;
    }
}
