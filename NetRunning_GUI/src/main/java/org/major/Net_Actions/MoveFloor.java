package org.major.Net_Actions;

import org.major.Essentials.NetSession;

import java.util.Scanner;

public class MoveFloor {
    private final Scanner scanner = new Scanner(System.in);

    public void run(NetSession session) {
        int currentFloor = session.getCurrentFloor();  // No +1 here!
        int maxFloors = session.getNet().size();

        System.out.println("=== FLOOR NAVIGATION ===");
        System.out.println("You are currently on floor: " + currentFloor);

        while (true) {
            System.out.print("Choose direction [U/D/E]: ");
            String input = scanner.nextLine().trim().toUpperCase();

            switch (input) {
                case "U":
                    if (currentFloor < maxFloors) {
                        currentFloor++;
                        System.out.println("⬆️  Moved up to floor: " + currentFloor);
                    } else {
                        System.out.println("🚫 Already on top floor.");
                    }
                    break;
                case "D":
                    if (currentFloor > 1) {
                        currentFloor--;
                        System.out.println("⬇️  Moved down to floor: " + currentFloor);
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

}
