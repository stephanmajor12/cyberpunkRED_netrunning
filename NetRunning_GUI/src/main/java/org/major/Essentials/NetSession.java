package org.major.Essentials;

import org.major.Entities.BlackICE;

import java.util.HashMap;
import java.util.Map;

public class NetSession {
    private final Player player;
    private final NetArchitecture net;
    private int currentFloor = 1;

    // NEW: Active Black ICE per floor
    private final Map<Integer, BlackICE> activeICE = new HashMap<>();

    public NetSession(NetArchitecture net, Player player) {
        this.net = net;
        this.player = player;
    }

    public Player getPlayer() { return player; }
    public NetArchitecture getNet() { return net; }

    public int getCurrentFloor() { return currentFloor; }
    public void setCurrentFloor(int floor) { this.currentFloor = floor; }

    // === NEW Black ICE Accessors ===

    public void addICE(int floorIndex, BlackICE ice) {
        floorIndex = floorIndex + 1;
        activeICE.put(floorIndex, ice);
    }

    public BlackICE getICE(int floorIndex) {
        floorIndex = floorIndex + 1;
        return activeICE.get(floorIndex);
    }

    public boolean hasICE(int floorIndex) {
        floorIndex = floorIndex + 1;
        return activeICE.containsKey(floorIndex);
    }

    public Map<Integer, BlackICE> getAllActiveICE() {
        return activeICE;
    }
}
