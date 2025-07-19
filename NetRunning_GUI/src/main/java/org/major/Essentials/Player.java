package org.major.Essentials;

public class Player {
    private final String name;
    private final int interfaceStat;

    public Player(String name, int interfaceStat) {
        this.name = name;
        this.interfaceStat = interfaceStat;
    }

    public String getName() {
        return name;
    }

    public int getInterfaceStat() {
        return interfaceStat;
    }

    @Override
    public String toString() {
        return name + " (Interface: " + interfaceStat + ")";
    }
}
