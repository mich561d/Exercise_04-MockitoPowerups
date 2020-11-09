package servicelayer.player;

import datalayer.player.PlayerStorageException;
import dto.Player;

import java.util.Collection;

public interface PlayerService {
    int createPlayer(String name) throws PlayerServiceException;

    Player getPlayerById(int id) throws PlayerServiceException;

    void updatePlayer(Player player) throws PlayerServiceException;

    Collection<Player> getPlayers() throws PlayerServiceException;
}
