package servicelayer.player;

import dto.Player;

import java.util.Collection;

public interface PlayerService {
    int createPlayer(String name) throws PlayerServiceException;

    Player getPlayerById(int id) throws PlayerServiceException;

    Collection<Player> getPlayers() throws PlayerServiceException;
}
