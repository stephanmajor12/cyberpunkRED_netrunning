package org.major.Net_Actions;

import org.major.Essentials.FloorNode;
import org.major.Essentials.NetSession;

import java.util.List;
import java.util.Scanner;

public class Backdoor {
    private final Scanner scanner = new Scanner(System.in);

    public void run(NetSession session, int myRoll) {
        List<FloorNode> floors = session.getNet().getFloors();

        System.out.println("\u001B[36m=== RUNNING BACKDOOR.exe ===\u001B[0m");
        int currFloor = session.getCurrentFloor();
        int nextFloor = currFloor + 1;

        if (nextFloor >= floors.size()) {
            System.out.println("\u001B[31m[ERROR]\u001B[0m No floor exists above the current one. Aborting BACKDOOR.exe.");
            return;
        }

        FloorNode targetNode = floors.get(nextFloor);
        String nodeType = targetNode.getType();

        if (!"PASSWORD".equalsIgnoreCase(nodeType)) {
            System.out.println("\u001B[33m[SKIP]\u001B[0m Floor " + nextFloor + " is not a PASSWORD node. It is: " + nodeType);
            System.out.println("\u001B[36mBACKDOOR.exe finished with exit code 0\u001B[0m");
            return;
        }

        int dv = targetNode.getDv();
        System.out.printf("[INFO] Attempting to crack PASSWORD node (DV: %d) with roll: %d%n", dv, myRoll);

        if (myRoll > dv) {
            System.out.println("\u001B[32m=== BACKDOOR SUCCESS: ACCESS GRANTED ===\u001B[0m");
            targetNode.setLockedState(false);
            System.out.println("\u001B[32m[UNLOCKED] Password wall status: " + targetNode.getLockedState() + "\u001B[0m");
            System.out.println("[NODE INFO]\n" + targetNode.getAllInfo());
        } else {
            System.out.println("\u001B[31m=== BACKDOOR FAILED: ACCESS DENIED ===\u001B[0m");
        }

        System.out.println("\u001B[36mBACKDOOR.exe finished with exit code 0\u001B[0m");
    }
}
