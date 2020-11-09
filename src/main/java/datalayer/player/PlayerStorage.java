package datalayer.player;

import dto.Player;

import java.util.List;

public interface PlayerStorage {
    int createPlayer(Player player) throws PlayerStorageException;

    Player getPlayerById(int playerId) throws PlayerStorageException;

    void updatePlayer(Player player) throws PlayerStorageException;

    List<Player> getPlayers() throws PlayerStorageException;
}
