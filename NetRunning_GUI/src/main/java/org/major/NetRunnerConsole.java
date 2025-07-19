package org.major;

import java.util.*;

import org.major.Entities.BlackICE;
import org.major.Essentials.*;
import org.major.Net_Actions.*;

public class NetRunnerConsole {
    private final Scanner scanner = new Scanner(System.in);

    public void run(NetArchitecture net, Player myPlayer) {
        NetSession session = new NetSession(net, myPlayer);
        List<FloorNode> floors = session.getNet().getFloors();

        for (int i = 1; i < floors.size(); i++) {
            FloorNode node = floors.get(i);

            if ("BLACK_ICE".equalsIgnoreCase(node.getType())) {
                BlackICE ice = new BlackICE(
                        node.getIceType(),
                        node.getPerception(),
                        node.getSpeed(),
                        node.getAttack(),
                        node.getDefense(),
                        node.getRez()
                );
                session.addICE(i, ice);
            }
        }

        // Action instances
        Backdoor bd = new Backdoor();
        Cloak cl = new Cloak();
        ControlNodeAction cna = new ControlNodeAction();
        EyeDee eyeDee = new EyeDee();
        MoveFloor movef = new MoveFloor();
        Pathfinder pf = new Pathfinder();
        Scan sc = new Scan();
        Slide slide = new Slide();
        Virus virus = new Virus();

        System.out.println("=== Netrunner Console ===");
        System.out.println("Type HELP for a list of commands.");

        while (true) {
            System.out.print("\n> ");
            String input = scanner.nextLine().trim().toUpperCase();

            switch (input) {
                case "HELP":
                    showHelp();
                    break;
                case "SCAN":
                    sc.run();
                    break;
                case "MOVE":
                    movef.run(session);
                    break;
                case "FLOOR":
                    System.out.println("📡 You are currently on floor: " + session.getCurrentFloor());
                    break;
                case "SLIDE":
                    slide.run(session, promptRoll("Enter your Interface Roll: "));
                    break;
                case "BACKDOOR":
                    bd.run(session, promptRoll("Enter your Interface Roll: "));
                    break;
                case "CONTROL":
                    cna.run(session, promptRoll("Enter your Interface Roll: "));
                    break;
                case "EYEDEE":
                    eyeDee.run(session, promptRoll("Enter your Interface Roll: "));
                    break;
                case "PATHFINDER":
                    pf.run(session, promptRoll("Enter your Interface Roll: "));
                    break;
                case "CLOAK":
                    cl.run(promptRoll("Enter your Interface Roll: "));
                    break;
                case "VIRUS":
                    virus.run(session);
                    break;
                case "EXIT":
                    System.out.println("🛑 Jacking out...");
                    return;
                default:
                    System.out.println("❓ Unknown command. Type HELP to see available commands.");
            }
        }
    }

    private int promptRoll(String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextInt()) {
            System.out.print("Please enter a valid number: ");
            scanner.next();
        }
        int roll = scanner.nextInt();
        scanner.nextLine(); // consume leftover newline
        return roll;
    }

    private void showHelp() {
        System.out.println("\n=== Available Commands ===");
        System.out.println("  BACKDOOR    - Attempt to break through a password lock");
        System.out.println("  CLOAK       - Hide your tracks from ICE and sysadmins");
        System.out.println("  CONTROL     - Take control of cameras, turrets, etc.");
        System.out.println("  EYEDEE      - Identify and analyze data files");
        System.out.println("  MOVE        - Move up or down a floor");
        System.out.println("  FLOOR       - Check which floor you are currently on");
        System.out.println("  PATHFINDER  - Scan ahead to see what's in the Architecture");
        System.out.println("  SCAN        - Check current network conditions");
        System.out.println("  SLIDE       - Slip past active Black ICE undetected");
        System.out.println("  VIRUS       - Leave a malicious virus in the system");
        System.out.println("  HELP        - Show this help message");
        System.out.println("  EXIT        - Jack out of the network");
    }
}
