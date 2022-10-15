import org.junit.jupiter.api.Test;

import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class MainTest {
    private final int SIZE_OF_CONNECTION_MULTITON = 3;

    private final Supplier<IConnection> supplier = Base::getConnection;

    @Test
    public void multitonConnectionTestLite() {
        IConnection[] connections = Stream.generate(supplier)
                .limit(SIZE_OF_CONNECTION_MULTITON + 1)
                .toArray(IConnection[]::new);

        assertEquals(connections[0], connections[SIZE_OF_CONNECTION_MULTITON]);
    }

    @Test
    public void multitonConnectionTestFull() {
        final int surplus = 2 * SIZE_OF_CONNECTION_MULTITON;

        IConnection[] connections = Stream.generate(supplier)
                .limit(SIZE_OF_CONNECTION_MULTITON + surplus)
                .toArray(IConnection[]::new);

        for (int i = 0; i < connections.length; i++)
            connections[i] = Base.getConnection();

        for (int i = SIZE_OF_CONNECTION_MULTITON; i < connections.length; i++)
            assertEquals(connections[i - SIZE_OF_CONNECTION_MULTITON], connections[i]);
    }

    @Test
    public void singletonBaseTest() {

        IConnection[] connections = new IConnection[SIZE_OF_CONNECTION_MULTITON * 2];

        for (int i = 0; i < connections.length; i++)
            connections[i] = Base.getConnection();

        Random random = new Random();

        char randomCharacter;
        int randomIndex;
        for (int i = 0; i < SIZE_OF_CONNECTION_MULTITON; i++) {

            randomIndex = Math.abs(random.nextInt()) % connections[i].length();
            randomCharacter = (char) randomIndex;

            connections[i].set(randomIndex, randomCharacter);

            for (int j = 0; j < SIZE_OF_CONNECTION_MULTITON; j++)
                assertEquals(connections[j].get(randomIndex), randomCharacter);

        }

    }

}
