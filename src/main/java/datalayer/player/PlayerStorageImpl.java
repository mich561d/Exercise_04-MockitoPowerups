package datalayer.player;

import dto.Player;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PlayerStorageImpl implements PlayerStorage {
    private final String connectionString;
    private final String username;
    private final String password;

    public PlayerStorageImpl(String conStr, String user, String pass) {
        connectionString = conStr;
        username = user;
        password = pass;
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(connectionString, username, password);
    }

    @Override
    public int createPlayer(Player player) throws PlayerStorageException {
        var sql = "insert into Player(name, wins, loses, lastPlayed) values (?, ?, ?, ?)";
        try (var con = getConnection();
             var stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, player.getName());
            stmt.setInt(2, player.getWins());
            stmt.setInt(3, player.getLoses());
            stmt.setDate(4, player.getLastPlayed());

            stmt.executeUpdate();

            try (var resultSet = stmt.getGeneratedKeys()) {
                resultSet.next();
                return resultSet.getInt(1);
            } catch (SQLException ex) {
                throw new PlayerStorageException(ex.getMessage());
            }
        } catch (SQLException ex) {
            throw new PlayerStorageException(ex.getMessage());
        }
    }

    @Override
    public Player getPlayerWithId(int playerId) throws PlayerStorageException {
        var sql = "select id, name, wins, loses, lastPlayed from Player where id = ?";
        try (var con = getConnection();
             var stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, playerId);

            try (var resultSet = stmt.executeQuery()) {
                if (resultSet.next()) {
                    var id = resultSet.getInt("ID");
                    var name = resultSet.getString("name");
                    var wins = resultSet.getInt("wins");
                    var loses = resultSet.getInt("loses");
                    var lastPlayed = resultSet.getDate("lastPlayed");

                    return new Player(id, name, wins, loses, lastPlayed);
                } else {
                    throw new PlayerStorageException("Player not found!");
                }
            } catch (SQLException ex) {
                throw new PlayerStorageException(ex.getMessage());
            }
        } catch (SQLException ex) {
            throw new PlayerStorageException(ex.getMessage());
        }
    }

    @Override
    public void updatePlayer(Player player) throws PlayerStorageException {
        var sql = "UPDATE Player SET wins = ?, loses = ?, lastPlayed = ? WHERE id = ?";
        try (var con = getConnection();
             var stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, player.getWins());
            stmt.setInt(2, player.getLoses());
            stmt.setDate(3, player.getLastPlayed());
            stmt.setInt(4, player.getId());

            stmt.executeUpdate();
        } catch (SQLException ex) {
            throw new PlayerStorageException(ex.getMessage());
        }
    }

    @Override
    public List<Player> getPlayers() throws PlayerStorageException {
        try (var con = getConnection();
             var stmt = con.createStatement()) {
            var results = new ArrayList<Player>();

            ResultSet resultSet = stmt.executeQuery("select id, name, wins, loses, lastPlayed from Player");

            while (resultSet.next()) {
                var id = resultSet.getInt("ID");
                var name = resultSet.getString("name");
                var wins = resultSet.getInt("wins");
                var loses = resultSet.getInt("loses");
                var lastPlayed = resultSet.getDate("lastPlayed");

                results.add(new Player(id, name, wins, loses, lastPlayed));
            }

            return results;
        } catch (SQLException ex) {
            throw new PlayerStorageException(ex.getMessage());
        }
    }
}
