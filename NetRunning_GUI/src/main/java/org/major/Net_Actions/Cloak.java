package org.major.Net_Actions;

public class Cloak {
    public void run(int cloakRoll) {
        System.out.println("▶ Executing CLOAK protocol...");
        System.out.println(">>> Hiding your digital footprints and any viruses left behind.");

        try {
            for (int i = 0; i < 5; i++) {
                Thread.sleep(500); // 0.5 second delay
                System.out.print(".");
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("CLOAK.exe finished with exit code 0");
    }
}
