package servicelayer.player;

import datalayer.player.PlayerStorage;
import datalayer.player.PlayerStorageException;
import dto.Player;

import java.sql.Date;
import java.util.Collection;

public class PlayerServiceImpl implements PlayerService {

    private final PlayerStorage playerStorage;

    public PlayerServiceImpl(PlayerStorage playerStorage) {
        this.playerStorage = playerStorage;
    }

    @Override
    public int createPlayer(String name) throws PlayerServiceException {
        try {
            return playerStorage.createPlayer(new Player(name, 0, 0, new Date(System.currentTimeMillis())));
        } catch (PlayerStorageException ex) {
            throw new PlayerServiceException(ex.getMessage());
        }
    }

    @Override
    public Player getPlayerById(int id) throws PlayerServiceException {
        try {
            return playerStorage.getPlayerWithId(id);
        } catch (PlayerStorageException ex) {
            throw new PlayerServiceException(ex.getMessage());
        }
    }

    @Override
    public Collection<Player> getPlayers() throws PlayerServiceException {
        try {
            return playerStorage.getPlayers();
        } catch (PlayerStorageException ex) {
            throw new PlayerServiceException(ex.getMessage());
        }
    }
}
