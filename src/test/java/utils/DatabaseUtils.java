package utils;

import com.github.javafaker.Faker;
import datalayer.player.PlayerStorage;
import datalayer.player.PlayerStorageException;
import dto.Player;
import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.configuration.FluentConfiguration;

import java.sql.Date;

public class DatabaseUtils {

    public static final int PORT = 3306;
    public static final String URL = "jdbc:mysql://localhost:" + PORT + "/";
    public static final String DB = "tic_tac_toe_test";
    public static final String CON_STR = URL + DB;
    public static final String USER = "dev";
    public static final String PASSWORD = "ax2";

    public static Flyway setupFlyway(String version) {
        Flyway flyway = new Flyway(new FluentConfiguration()
                .defaultSchema(DB)
                .createSchemas(true)
                .schemas(DB)
                .target(version)
                .dataSource(URL, USER, PASSWORD));

        flyway.clean();
        flyway.migrate();

        return flyway;
    }

    public static void addFakePlayers(int numPlayers, PlayerStorage playerStorage) throws PlayerStorageException {
        for (int i = 0; i < numPlayers; i++) {
            playerStorage.createPlayer(createFakePlayer());
        }
    }

    public static Player createFakePlayer() {
        return new Player(getFakerUsername(), 0, 0, new Date(System.currentTimeMillis()));
    }

    public static String getFakerUsername() {
        return new Faker().name().username();
    }
}
