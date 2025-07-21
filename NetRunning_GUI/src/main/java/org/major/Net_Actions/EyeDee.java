package org.major.Net_Actions;

import org.major.Essentials.FloorNode;
import org.major.Essentials.NetSession;

public class EyeDee {
    private boolean usedThisTurn = false;

    public void run(NetSession session, int netrunnerRoll) {
        /*
        if (usedThisTurn) {
            System.out.println("🚫 You've already used Eye-Dee this turn.");
            return;
        }
         */

        int currFloor = session.getCurrentFloor(); // Use session's current floor (1-based)
        FloorNode floor = session.getNet().getFloor(currFloor - 1); // Convert to 0-based index
        System.out.println("Current Floor: " + currFloor);

        if (!"DATA".equalsIgnoreCase(floor.getType())) {
            System.out.println("❌ There is no data here to identify.");
            return;
        }

        int dv = floor.getDv();
        System.out.println("▶ Attempting EYE-DEE scan...");
        System.out.println("🎲 Your Interface Roll: " + netrunnerRoll);
        System.out.println("🧠 DV to identify data: " + dv);

        if (netrunnerRoll > dv) {
            System.out.println("✅ You identified the data successfully!");
            System.out.println("📄 Data Details: Worth [" + floor.getWorth() + "], Desc: " + floor.getContent());
        } else {
            System.out.println("❌ Eye-Dee failed. The file remains encrypted and useless to you.");
        }

        usedThisTurn = true;
    }

    public void resetTurn() {
        usedThisTurn = false;
    }
}
