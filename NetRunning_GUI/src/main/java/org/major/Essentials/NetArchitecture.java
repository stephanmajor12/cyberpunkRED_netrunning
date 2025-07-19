package org.major.Essentials;

import java.util.List;

public class NetArchitecture {
    private final String name;
    private final List<FloorNode> floors;

    public NetArchitecture(String name, List<FloorNode> floors) {
        this.name = name;
        this.floors = floors;
    }

    public String getName() {
        return name;
    }

    public List<FloorNode> getFloors() {
        return floors;
    }

    public FloorNode getFloor(int index) {
        return (index >= 0 && index < floors.size()) ? floors.get(index) : null;
    }

    public int size() {
        return floors.size();
    }
}
