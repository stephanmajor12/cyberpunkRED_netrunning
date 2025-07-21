package org.major.Net_Actions;

import org.major.Entities.BlackICE;
import org.major.Essentials.Dice;
import org.major.Essentials.FloorNode;
import org.major.Essentials.NetSession;

import java.util.Map;
import java.util.Objects;
import java.util.Scanner;

public class MoveFloor {
    private final Scanner scanner = new Scanner(System.in);

    public void run(NetSession session) {
        int currentFloor = session.getCurrentFloor();
        int maxFloors = session.getNet().size();

        System.out.println("=== FLOOR NAVIGATION ===");
        System.out.println("You are currently on floor: " + currentFloor);

        while (true) {
            System.out.print("Choose direction [U/D/E]: ");
            String input = scanner.nextLine().trim().toUpperCase();

            switch (input) {
                case "U":
                    if (currentFloor < maxFloors - 1) {
                        int nextFloor = currentFloor + 1;
                        if (!triggerPasswdWall(session, nextFloor)) {
                            session.moveICE(currentFloor, nextFloor); // 👈 move ICE if active
                            currentFloor = nextFloor;
                            System.out.println("⬆️  Moved up to floor: " + currentFloor);
                            triggerICEIfPresent(session, currentFloor);
                        }
                    } else {
                        System.out.println("🚫 Already on top floor.");
                    }
                    break;

                case "D":
                    if (currentFloor > 0) {
                        int nextFloor = currentFloor - 1;
                        if (!triggerPasswdWall(session, nextFloor)) {
                            session.moveICE(currentFloor, nextFloor); // 👈 move ICE if active
                            currentFloor = nextFloor;
                            System.out.println("⬇️  Moved down to floor: " + currentFloor);
                            triggerICEIfPresent(session, currentFloor);
                        }
                    } else {
                        System.out.println("🚫 Already on bottom floor.");
                    }
                    break;

                case "E":
                    session.setCurrentFloor(currentFloor);
                    System.out.println("Exiting navigation...");
                    return;

                default:
                    System.out.println("Invalid input.");
            }

            System.out.println("Current floor: " + currentFloor);
        }
    }

    private boolean triggerPasswdWall(NetSession session, int floor) {
        FloorNode node = session.getNet().getFloor(floor);

        if (!Objects.equals(node.getType(), "PASSWORD")) {
            return false; // No lock if it's not a PASSWORD node
        }
        boolean locked = node.getLockedState();

        if (locked) {
            System.out.println("🔒 Access denied. This floor is locked by a password. Use BACKDOOR to unlock it.");
            return true;
        }

        return false;
    }

    private void triggerICEIfPresent(NetSession session, int floor) {
        FloorNode node = session.getNet().getFloor(floor);

        if (!Objects.equals(node.getType().toUpperCase(), "BLACK_ICE")) {
            return; // Not a Black ICE floor — skip
        }

        BlackICE ice = session.getICE(floor);
        if (ice == null) {
            System.out.println("⚠️  No Black ICE entity found on floor " + (floor + 1));
            return;
        }

        if (ice.isActive()) {
            System.out.println("👁️  Black ICE is already active on this floor!");
            return;
        }

        int iceRoll = Dice.roll(10) + ice.getPerception();
        ice.setActive(true); // ICE locks onto the player

        System.out.println("💀 BLACK ICE ACTIVATED!");
        System.out.println("🎲 ICE Perception Roll: " + iceRoll);
        System.out.println("🧠 ICE Type: " + ice.getIceType());
        System.out.println(getIceAscii(ice.getIceType()));
        System.out.println("🔥 ICE is now active on floor " + (floor + 1));
    }

    private String getIceAscii(String iceType) {
        switch (iceType.toUpperCase()) {
            case "WISP":
                return """
                .-.
               (o o)  WISP
               | O \\
               \\   \\
                `~~~'
            """;
            case "RAVEN":
                return """
                 \\   /  
                  .-.
               ― (   ) ―  RAVEN
                  `-’  
                 /   \\
            """;
            case "TAR_PIT":
                return """
                ~~~~~~
               ( o_o )  TAR PIT
               /     \\
              |       |
               \\_____/
            """;
            case "HELLHOUND":
                return """
               ,     ,
              (\\____/)
              (_oo_)
                (O)   HELLHOUND
              __||__   
             (______) 
            """;
            default:
                return "[UNKNOWN ICE TYPE]";
        }
    }

}
