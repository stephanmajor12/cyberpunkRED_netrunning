package org.major.Net_Actions;

import org.major.Entities.BlackICE;
import org.major.Essentials.Dice;
import org.major.Essentials.FloorNode;
import org.major.Essentials.NetArchitecture;
import org.major.Essentials.NetSession;
import org.major.Essentials.Player;

import java.util.Scanner;

public class Slide {
    private final Scanner scanner = new Scanner(System.in);
    private boolean usedThisTurn = false;

    public void run(NetSession session, int netrunnerRoll) {
        if (usedThisTurn) {
            System.out.println("🚫 You've already used Slide this turn.");
            return;
        }

        NetArchitecture net = session.getNet();
        int currFloor = session.getCurrentFloor();

        // Check if there is ICE on this floor
        if (!session.hasICE(currFloor)) {
            System.out.println("ℹ️ No Black ICE active on this floor to Slide from.");
            return;
        }

        BlackICE blackICE = session.getICE(currFloor);
        if (!blackICE.isActive()) {
            System.out.println("ℹ️ The ICE here is inactive.");
            return;
        }

        // Begin Slide attempt
        int iceRoll = Dice.roll(10) + blackICE.getPerception();
        System.out.println("▶ Attempting to SLIDE from: " + blackICE.getIceType());
        System.out.println("🎲 Your Interface Roll: " + netrunnerRoll);
        System.out.println("👁️ ICE Perception Roll: " + iceRoll);

        if (netrunnerRoll > iceRoll) {
            System.out.println("✅ Success! You slipped past the ICE.");
            int newFloor = getPlayerDirection(session, net);
            if (newFloor != -1) {
                session.setCurrentFloor(newFloor);
                System.out.println("📡 Escaped to floor: " + (newFloor + 1));
            } else {
                System.out.println("⚠️ You escaped the ICE but couldn’t move due to an obstruction.");
            }
        } else {
            System.out.println("❌ Slide failed. The ICE is still locked on.");
        }

        usedThisTurn = true;
    }

    private int getPlayerDirection(NetSession session, NetArchitecture net) {
        int current = session.getCurrentFloor();

        boolean canGoUp = current < net.size() && !isBlocked(net.getFloor(current)); // zero-indexed
        boolean canGoDown = current - 2 >= 0 && !isBlocked(net.getFloor(current - 2));

        if (!canGoUp && !canGoDown) {
            return -1;
        }

        while (true) {
            System.out.print("⬆️ Slide Up (U) or ⬇️ Slide Down (D)? ");
            String input = scanner.nextLine().trim().toUpperCase();

            if (input.equals("U") && canGoUp) {
                return current + 1;
            } else if (input.equals("D") && canGoDown) {
                return current - 1;
            } else {
                System.out.println("❌ Invalid input or direction blocked.");
            }
        }
    }

    private boolean isBlocked(FloorNode floor) {
        String type = floor.getType().toUpperCase();
        return type.equals("PASSWORD") || type.equals("OBSTRUCTION");
    }

    public void resetTurn() {
        usedThisTurn = false;
    }
}
