package org.major.Net_Actions;

import org.major.Essentials.FloorNode;
import org.major.Essentials.NetSession;

import java.util.List;

public class Pathfinder {
    public void run(NetSession session, int myRoll) {
        List<FloorNode> floors = session.getNet().getFloors();
        boolean passwdFlag = false;
        boolean checkFailed = false;

        for (int i = 0; i < floors.size(); i++) {
            FloorNode node = floors.get(i);

            if (node.getType().equalsIgnoreCase("PASSWORD")) {
                if (myRoll > node.getDv()) {
                    passwdFlag = false;
                    System.out.println("[LEVEL " + (i + 1) + "]");
                    System.out.println(node.getInfo());
                } else {
                    passwdFlag = true;
                    if (!checkFailed) {
                        if(myRoll > node.getDv()) {
                            System.out.println("Password detected on Floor [LEVEL CODE: " + (i + 1) + "], unable to see past it.");
                        } else {
                            System.out.println("[LEVEL " + (i + 1) + "]\n" +node.getInfo());
                        }
                    }
                    checkFailed = true;
                    continue; // don't show anything past the password
                }
            } else {
                System.out.println("[LEVEL " + (i + 1) + "]\n" +node.getInfo());
            }

            /*
            if (!passwdFlag) {
                System.out.println(node.getAllInfo());
            }

             */
        }

        System.out.println("pf2e.exe finished with exit code 0");
    }
}
