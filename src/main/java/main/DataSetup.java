package main;

import datalayer.player.PlayerStorage;
import datalayer.player.PlayerStorageImpl;
import dto.Player;
import servicelayer.player.PlayerService;
import servicelayer.player.PlayerServiceImpl;

import java.util.Collection;

public class DataSetup {

    private static final String conStr = "jdbc:mysql://localhost:3306/tic_tac_toe";
    private static final String user = "dev";
    private static final String pass = "ax2";

    public static void main(String[] args) throws Exception {
        System.out.println("Starting...");

        System.out.println("Setting up storages...");
        PlayerStorage playerStorage = new PlayerStorageImpl(conStr, user, pass);
        System.out.println("Storages created!");

        System.out.println("Setting up services...");
        PlayerService playerService = new PlayerServiceImpl(playerStorage);
        System.out.println("Services created!");

        System.out.println("Setting up test data... - Players");
        Collection<Player> p = playerService.getPlayers();
        if (p.size() < 1) {
            playerService.createPlayer("Hack'n'Slash");
            playerService.createPlayer("X-Rider");
            playerService.createPlayer("TTT Mazter");
            System.out.println("Players test data is created!");

            System.out.println("Retrieving test data... - Players");
            p = playerService.getPlayers();
            System.out.println("Customers is retrieved!");
        } else {
            System.out.println("Test data is already created!");
        }

        System.out.println("Printing all player data...");
        for (Player player : p) {
            System.out.println(toString(player));
        }
        System.out.println("All player data has been printed!");

        System.out.println("The end!");
    }

    public static String toString(Player p) {
        String msg = "{%d, %s, %d, %d, %tF}";
        return String.format(msg, p.getId(), p.getName(), p.getWins(), p.getLoses(), p.getLastPlayed());
    }
}
