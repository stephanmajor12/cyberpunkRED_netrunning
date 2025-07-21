package org.major;

import org.major.Entities.BlackICE;
import org.major.Essentials.*;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        List<Player> players = PlayerLoader.loadPlayers("players");
        Player player = players.getFirst();

        System.out.println("===== JACKED-IN USER: " + player.getName() + " =====");
        System.out.println("=== Netrunner Console Initialized ===");

        try {
            // Step 1: Load available seeds from "seeds/" folder
            File seedDir = new File("seeds");
            String[] availableSeeds = seedDir.list((dir, name) -> name.endsWith(".json"));

            if (availableSeeds == null || availableSeeds.length == 0) {
                System.out.println("⚠️ No seeds found in /seeds directory.");
                return;
            }

            System.out.println("\n📂 Available NET Seeds:");
            for (String fileName : availableSeeds) {
                System.out.println("  - " + fileName.replace(".json", ""));
            }

            // Step 2: Prompt user for seed selection
            Scanner scanner = new Scanner(System.in);
            System.out.print("\nEnter NET seed to load: ");
            String seed = scanner.nextLine().trim();

            // Step 3: Load and run the selected seed
            NetArchitecture net = SeedLoader.loadSeed(seed);
            System.out.println("\n[SEED LOADED] " + net.getName());
            NetRunnerConsole console = new NetRunnerConsole();
            console.run(net, player);
        } catch (Exception e) {
            System.out.println("❌ Failed to load seed: " + e.getMessage());
        }
    }
}
