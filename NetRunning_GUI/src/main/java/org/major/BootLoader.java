package org.major;

public class BootLoader {

    public static void main(String[] args) {
        try {
            clearScreen();
            typeOut("\u001B[32m[BOOT] Initializing Mainframe BootLoader v1.3.7...\u001B[0m", 40);
            pause(500);
            typeOut("[SYS] Verifying system integrity...", 30);
            pause(500);
            typeOut("\u001B[32m[OK]\u001B[0m Kernel modules: loaded", 20);
            typeOut("[SYS] Mounting ghost partitions...", 30);
            pause(300);
            typeOut("\u001B[32m[OK]\u001B[0m ShadowFS linked", 20);
            typeOut("[NET] Establishing secure uplink... ", 30);
            pause(700);
            typeOut("\u001B[32m[CONNECTED]\u001B[0m Node: 107.190.99.220 Port: 30000 (AES-256)", 20);
            typeOut("[SIGINT] Scrambling MAC identifiers...", 30);
            pause(300);
            typeOut("[SIGINT] Injecting synthetic traffic...", 30);
            pause(500);
            typeOut("\u001B[32m[STEALTH MODE ACTIVE]\u001B[0m", 25);
            typeOut("[AUTH] Authenticating credentials... ", 30);
            pause(600);
            typeOut("\u001B[32m[ACCESS GRANTED]\u001B[0m User: Badger", 10);
            pause(100);
            typeOut("\u001B[32m[ACCESS GRANTED]\u001B[0m User: Bug", 10);
            pause(100);
            typeOut("\u001B[32m[ACCESS GRANTED]\u001B[0m User: GHØST-IDØL", 10);
            pause(100);
            typeOut("\u001B[32m[ACCESS GRANTED]\u001B[0m User: Mover", 10);
            pause(100);
            typeOut("\u001B[32m[ACCESS GRANTED]\u001B[0m User: Santo Damingo Dealmaker", 10);
            pause(100);
            typeOut("[CORE] Spinning up neural net interface...", 30);
            pause(800);
            typeOut("[CORE] Syncing zero-trust lattice...", 30);
            pause(500);
            typeOut("\u001B[32m[READY]\u001B[0m Cyberpunk_RED engaged", 20);
            typeOut("[ALERT] Valtiel-ICE detected on subnet... \u001B[31m[WARNING]\u001B[0m", 35);
            pause(600);
            typeOut("[FIREWALL] Deploying decoy daemons...", 25);
            pause(300);
            typeOut("[TRACE] Routing signals through darknet nodes [4 hops]", 25);
            pause(300);
            typeOut("\u001B[32m[ANONYMITY CONFIRMED]\u001B[0m", 25);
            pause(500);
            typeOut("\u001B[35m>> LAUNCHING PAYLOAD: [NEVER_FADE_AWAY.dll] <<\u001B[0m", 40);
            pause(500);
            typeOut("[SYS] Injecting runtime hooks...", 25);
            typeOut("[SYS] Finalizing memory sandbox...", 25);
            pause(600);
            typeOut("\u001B[32mSYSTEM ONLINE. WELCOME, OPERATOR.\u001B[0m", 50);
        } catch (InterruptedException e) {
            System.err.println("Boot interrupted.");
        }
    }

    private static void typeOut(String text, int delay) throws InterruptedException {
        for (char c : text.toCharArray()) {
            System.out.print(c);
            System.out.flush();
            Thread.sleep(delay);
        }
        System.out.println();
    }

    private static void pause(int ms) throws InterruptedException {
        Thread.sleep(ms);
    }

    private static void clearScreen() {
        System.out.print("\u001B[2J\u001B[H"); // ANSI escape for clearing screen
        System.out.flush();
    }
}
