package org.major.Net_Actions;

import org.major.Essentials.FloorNode;
import org.major.Essentials.NetSession;
import org.major.Essentials.Dice;

import java.util.Scanner;

public class ControlNodeAction {
    private boolean usedThisTurn = false;
    private boolean hasControl = false;
    private int controlDV = -1;

    private final Scanner scanner = new Scanner(System.in);

    public void run(NetSession session, int netrunnerRoll) {
        if (usedThisTurn) {
            System.out.println("🚫 You've already used Control this turn.");
            return;
        }

        int currFloor = session.getCurrentFloor();

        FloorNode floor = session.getNet().getFloor(currFloor); // 1-based session
        if (!"CONTROL_NODE".equalsIgnoreCase(session.getNet().getFloor(currFloor).getType())) {
            System.out.println("⚠️ No Control Node on this floor.");
            return;
        } else {
            System.out.println("Control Node on this floor.");
        }

        System.out.println("---------");
        System.out.println("controls: " + floor.getControls());

        if (!hasControl) {
            controlDV = floor.getDv();
            System.out.println("🎛️ Attempting to take control of the node (DV: " + controlDV + ")");
            System.out.println("🎲 Your Interface Roll: " + netrunnerRoll);

            if (netrunnerRoll >= controlDV) {
                hasControl = true;
                System.out.println("✅ Control Node hacked! You now control:" + floor.getControls());
            } else {
                System.out.println("❌ Failed to control the node.");
            }

        } else {
            System.out.println("🎮 You already have control of this node.");
            System.out.println("💻 Available Controls: " + String.join(", ", floor.getControls()));

            System.out.print("Which control do you want to activate? ");
            String choice = scanner.nextLine().trim();

            if (floor.getControls().contains(choice)) {
                System.out.println("✅ You activate: " + choice + ". Action successful.");
                // Add logic to trigger the effect of that control (e.g. disable camera)
            } else {
                System.out.println("❌ Invalid control choice.");
            }
        }

        usedThisTurn = true;
    }

    public void resetTurn() {
        usedThisTurn = false;
    }

    public void resetControl() {
        hasControl = false;
        controlDV = -1;
    }

    public boolean isInControl() {
        return hasControl;
    }
}
