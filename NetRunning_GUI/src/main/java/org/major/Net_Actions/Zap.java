package org.major.Net_Actions;

public class Zap {
    public void run() {
        System.out.println(">>> Executing ZAP! protocol.");

        try {
            for (int i = 0; i < 3; i++) {
                Thread.sleep(500); // 0.5 second delay
                System.out.print(".");
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("\n>>> ZAPPED!");
        System.out.println("ZAP.exe finished with exit code 0");
    }
}
