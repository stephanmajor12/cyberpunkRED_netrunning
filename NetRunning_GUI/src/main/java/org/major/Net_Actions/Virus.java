package org.major.Net_Actions;

import org.major.Essentials.NetSession;

public class Virus {
    public void run(NetSession session) {
        int currentFloor = session.getCurrentFloor();
        int maxFloor = session.getNet().size();

        System.out.println("Max Floor: " + maxFloor);
        if (currentFloor != maxFloor) {
            System.out.println("⚠️ You must be on the lowest floor of the Architecture to plant a Virus.");
            return;
        }

        System.out.println("🦠 You created a Virus! Its effect will persist after you Jack Out.");
    }
}
