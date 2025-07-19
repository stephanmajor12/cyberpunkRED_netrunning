package org.major.Net_Actions;

import org.major.Entities.BlackICE;
import org.major.Essentials.Dice;
import org.major.Essentials.FloorNode;
import org.major.Essentials.NetSession;

import java.util.Objects;
import java.util.Scanner;

public class MoveFloor {
    private final Scanner scanner = new Scanner(System.in);

    public void run(NetSession session) {
        int currentFloor = session.getCurrentFloor();
        System.out.println("CURRENT FLOOR ISSSSSSS" + currentFloor);
        int maxFloors = session.getNet().size();
        //int prettyFloor = currentFloor - 1;

        System.out.println("=== FLOOR NAVIGATION ===");
        System.out.println("You are currently on floor: " + currentFloor);

        while (true) {
            System.out.print("Choose direction [U/D/E]: ");
            String input = scanner.nextLine().trim().toUpperCase();

            switch (input) {
                case "U":
                    if (currentFloor < maxFloors) {
                        currentFloor = currentFloor + 1;
                        System.out.println("⬆️  Moved up to floor: " + currentFloor);
                        /*
                        if (!triggerPasswdWall(session, targetFloor)) {
                            currentFloor = targetFloor;
                            System.out.println("⬆️  Moved up to floor: " + currentFloor);
                        }
                         */
                        triggerICEIfPresent(session, currentFloor);
                    } else {
                        System.out.println("🚫 Already on top floor.");
                    }
                    break;
                case "D":
                    if (currentFloor > 1) {
                        int targetFloor = currentFloor - 1;
                        if (triggerPasswdWall(session, targetFloor)) break;
                        currentFloor = targetFloor;
                        System.out.println("⬇️  Moved down to floor: " + currentFloor);
                        triggerICEIfPresent(session, currentFloor);
                    } else {
                        System.out.println("🚫 Already on bottom floor.");
                    }
                    break;
                case "E":
                    session.setCurrentFloor(currentFloor); // Save globally
                    System.out.println("Exiting navigation...");
                    return;
                default:
                    System.out.println("Invalid input.");
            }

            System.out.println("Current floor: " + currentFloor);
        }
    }

    private boolean triggerPasswdWall(NetSession session, int floor) {
        int floorIndex = floor - 1;
        FloorNode node = session.getNet().getFloor(floorIndex);

        if(Objects.equals(node.getType(), "PASSWORD")) {
            System.out.println("🔒 Access denied. This floor is locked by a password. Use BACKDOOR to unlock it.");
            return true; // Block movement
        }

        return false; // Movement allowed
    }

    private void triggerICEIfPresent(NetSession session, int floor) {
        int floorIndex = floor - 1;

        if (!session.hasICE(floorIndex)) return;

        BlackICE ice = session.getICE(floorIndex);
        if (ice.isActive()) {
            System.out.println("⚠️  Black ICE already active on this floor!");
            return;
        }

        System.out.print("🧠 Enter your Interface roll to avoid ICE: ");
        int netrunnerRoll = scanner.nextInt();
        scanner.nextLine(); // consume newline

        int iceRoll = Dice.roll(10) + ice.getPerception();
        System.out.println("🎲 ICE Perception Roll: " + iceRoll);

        if (netrunnerRoll <= iceRoll) {
            System.out.println("💀 ICE has locked on! Activating...");
            ice.setActive(true);
        } else {
            System.out.println("✅ You moved undetected past the ICE... for now.");
        }
    }
}
