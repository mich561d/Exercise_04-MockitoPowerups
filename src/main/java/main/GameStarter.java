package main;

import datalayer.player.PlayerStorage;
import datalayer.player.PlayerStorageImpl;
import dto.Player;
import functionlayer.game.GameBoard;
import servicelayer.player.PlayerService;
import servicelayer.player.PlayerServiceException;
import servicelayer.player.PlayerServiceImpl;

import java.sql.Date;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class GameStarter {

    public static void main(String[] args) throws PlayerServiceException, InterruptedException {
        PlayerStorage playerStorage = new PlayerStorageImpl("jdbc:mysql://localhost:3306/tic_tac_toe", "dev", "ax2");
        PlayerService playerService = new PlayerServiceImpl(playerStorage);
        GameBoard gameBoard = new GameBoard();
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        System.out.println("Welcome to Tic-Tac-Toe");
        System.out.println("\nRules:");
        System.out.println("3 in a row = wins (both vertical, horizontal and diagonal)");

        int maxNumOfTopPlayers = 10;
        System.out.println("\nTop " + maxNumOfTopPlayers + " - Highscores:");
        List<Player> players = playerService.getPlayers();
        players.sort((o1, o2) -> {
            int equalWins = o2.getWins() - o1.getWins();
            if (equalWins == 0) {
                return o1.getLoses() - o2.getLoses();
            }
            return equalWins;
        });
        int numOfPlayers = Math.min(players.size(), maxNumOfTopPlayers);
        for (int i = 0; i < numOfPlayers; i++) {
            Player player = players.get(i);
            System.out.format("%d. | %45s | %d | %d | %tD%n", (i + 1), player.getName(), player.getWins(), player.getLoses(), player.getLastPlayed());
        }

        System.out.println("\nUse your personal id or create a new player (write 0)");
        int id = scanner.nextInt();
        Player player;

        if (id == 0) {
            System.out.println("\nCreating a new user...");
            System.out.println("Write a cool nickname:");
            String name = scanner.nextLine();
            id = playerService.createPlayer(name);
            System.out.println("Player created! This is your personal id (write it down) - Id: " + id);
            player = playerService.getPlayerById(id);
        } else {
            System.out.println("\nRetrieve player by personal id...");
            player = playerService.getPlayerById(id);
            System.out.println("Welcome back " + player.getName() + "\n");
        }

        gameBoard.createBoard();
        boolean activeGame = true;
        boolean playerTurn = random.nextBoolean();

        while (activeGame) {
            Thread.sleep(2000);
            System.out.println(gameBoard.printBoard());

            if (playerTurn) {
                System.out.println("\nYour turn!");
                System.out.println("choose X:");
                int x = scanner.nextInt();
                System.out.println("choose Y:");
                int y = scanner.nextInt();
                boolean validMove = gameBoard.makeMove(1, x, y);
                while (!validMove) {
                    System.out.println("\nInvalid move try again..");
                    System.out.println("choose X:");
                    x = scanner.nextInt();
                    System.out.println("choose Y:");
                    y = scanner.nextInt();
                    validMove = gameBoard.makeMove(1, x, y);
                }
            } else {
                System.out.println("\nOpponents turn!");
                int x = random.nextInt(3) + 1;
                int y = random.nextInt(3) + 1;
                boolean validMove = gameBoard.makeMove(2, x, y);
                while (!validMove) {
                    x = random.nextInt(3) + 1;
                    y = random.nextInt(3) + 1;
                    validMove = gameBoard.makeMove(2, x, y);
                }
                System.out.println("Opponents places on " + x + "," + y + "\n");
            }

            playerTurn = !playerTurn;

            if (gameBoard.checkWin(1)) {
                player.addWin();
                player.setLastPlayed(new Date(System.currentTimeMillis()));
                playerService.updatePlayer(player);
                System.out.println("\nCongratz " + player.getName() + ", you won!");
                activeGame = false;
            } else if (gameBoard.checkWin(2)) {
                player.addLoses();
                player.setLastPlayed(new Date(System.currentTimeMillis()));
                playerService.updatePlayer(player);
                System.out.println("\nSadness overload " + player.getName() + ", you lost!");
                activeGame = false;
            } else if (gameBoard.checkForFullBoard()) {
                player.setLastPlayed(new Date(System.currentTimeMillis()));
                playerService.updatePlayer(player);
                System.out.println("\nListen " + player.getName() + ", it was a draw!");
                activeGame = false;
            }
        }
    }
}
