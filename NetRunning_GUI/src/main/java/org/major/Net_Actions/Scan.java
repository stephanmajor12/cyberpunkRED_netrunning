package org.major.Net_Actions;

public class Scan {
    public void run() {
        System.out.println(">>> Scanning area for access points");

        try {
            for (int i = 0; i < 5; i++) {
                Thread.sleep(500); // 0.5 second delay
                System.out.print(".");
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("\n>>> Scan complete. Access points identified.");
        System.out.println("SCAN.exe finished with exit code 0");
    }
}
