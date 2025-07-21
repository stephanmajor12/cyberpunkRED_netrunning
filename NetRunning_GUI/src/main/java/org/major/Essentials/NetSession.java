package org.major.Essentials;

import org.major.Entities.BlackICE;

import java.util.HashMap;
import java.util.Map;

public class NetSession {
    private final Player player;
    private final NetArchitecture net;
    private int currentFloor = 0;
    private int turnCount = 0;


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

    public int getTurnCount() {
        return turnCount;
    }

    public void incrementTurn() {
        turnCount++;
    }

    public void resetTurnCount() {
        turnCount = 1;
    }

    public void moveICE(int fromFloor, int toFloor) {
        BlackICE ice = getICE(fromFloor);
        if (ice != null && ice.isActive()) {
            addICE(toFloor, ice);
            activeICE.remove(fromFloor + 1); // Remove from old floor (adjusting for +1)
            System.out.println("👁️  Black ICE has followed you to floor " + (toFloor));
        }
    }



}
