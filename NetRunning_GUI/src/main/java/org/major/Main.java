package org.major;

import org.major.Entities.BlackICE;
import org.major.Essentials.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        String seed = "example";
        List<Player> players = PlayerLoader.loadPlayers("players");

        System.out.println("===== JACKED-IN USER: " + players.getFirst().getName() + " =====");
        System.out.println("=== Netrunner Console Initialized ===");
        Player player = players.getFirst();

        try {
            NetArchitecture net = SeedLoader.loadSeed(seed);
            System.out.println("\n[SEED LOADED] " + net.getName());
            NetRunnerConsole console = new NetRunnerConsole();
            console.run(net, player);
        } catch (Exception e) {
            System.out.println("❌ Failed to load seed: " + e.getMessage());
        }
    }
}