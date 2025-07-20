package org.major.Net_Actions;

import org.json.JSONObject;
import org.major.Essentials.*;

import java.util.List;
import java.util.Scanner;

public class Backdoor {
    private final Scanner scanner = new Scanner(System.in);

    public void run(NetSession session, int myRoll) {
        List<FloorNode> floors = session.getNet().getFloors();

        System.out.println("=== RUNNING BACKDOOR.exe ===");
        int currFloor = session.getCurrentFloor();

        if(floors.get(currFloor).getType().equals("PASSWORD")) {
            if(myRoll > floors.get(currFloor).getDv()) {
                System.out.println("=== BACKDOOR SUCCESS WELCOME IN HACKER ===");
                floors.get(currFloor).getLockedState();
                System.out.println(floors.get(currFloor).getAllInfo());
            } else {
                System.out.println("=== BACKDOOR FAILED ===");
            }

        }
        System.out.println("BACKDOOR.exe finished with exit code 0");
    }
}
