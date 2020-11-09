package dto;

import java.sql.Date;

public class Player {

    private final String name;
    private int id, wins, loses;
    private Date lastPlayed;

    public Player(int id, String name, int wins, int loses, Date lastPlayed) {
        this.id = id;
        this.name = name;
        this.wins = wins;
        this.loses = loses;
        this.lastPlayed = lastPlayed;
    }

    public Player(String name, int wins, int loses, Date lastPlayed) {
        this.name = name;
        this.wins = wins;
        this.loses = loses;
        this.lastPlayed = lastPlayed;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public int getWins() {
        return wins;
    }

    public void addWin() {
        wins++;
    }

    public int getLoses() {
        return loses;
    }

    public void addLoses() {
        loses++;
    }

    public Date getLastPlayed() {
        return lastPlayed;
    }

    public void setLastPlayed(Date lastPlayed) {
        this.lastPlayed = lastPlayed;
    }
}
