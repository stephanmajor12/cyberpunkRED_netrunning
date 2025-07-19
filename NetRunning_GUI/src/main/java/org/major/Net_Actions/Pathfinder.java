package org.major.Net_Actions;

import org.major.Essentials.FloorNode;
import org.major.Essentials.NetSession;

import java.util.List;

public class Pathfinder {
    public void run(NetSession session, int myRoll) {
        List<FloorNode> floors = session.getNet().getFloors();
        boolean blockedByPassword = false;

        for (int i = 0; i < floors.size(); i++) {
            FloorNode node = floors.get(i);
            int level = i + 1;

            if (blockedByPassword) {
                // Stop revealing anything after the blocked password
                break;
            }

            if ("PASSWORD".equalsIgnoreCase(node.getType())) {
                int dv = node.getDv();
                if (myRoll > dv) {
                    System.out.println("[LEVEL " + level + "]\n" + node.getInfo());
                } else {
                    System.out.println("🔒 Password node on Floor [LEVEL " + level + "] — Access denied. Cannot scan deeper.");
                    blockedByPassword = true;
                }
            } else {
                System.out.println("[LEVEL " + level + "]\n" + node.getInfo());
            }
        }

        System.out.println("pf2e.exe finished with exit code 0");
    }
}
