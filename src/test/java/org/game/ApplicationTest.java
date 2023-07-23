package org.game;

import io.quarkus.test.junit.QuarkusTest;
import org.game.common.GameBuilder;
import org.game.controls.Choice;
import org.game.integration.ConnectorProvider;
import org.game.integration.ExternalResourceConnector;
import org.game.player.TestConsole;
import org.game.player.TestLogger;
import org.game.util.GameLogger;
import org.jboss.logging.Logger;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.io.InputStream;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertTrue;

@QuarkusTest
class ApplicationTest {
    private static final InputStream systemIn = System.in;
    private final Application classUnderTest = new Application();

    @AfterAll
    public static void restoreSystemInput() {
        System.setIn(systemIn);
    }

    @Test
    @Timeout(value = 100, unit = TimeUnit.MILLISECONDS)
    public void testFullApplication() throws Exception {
        TestLogger testLogger = new TestLogger();
        GameLogger.setLogger(testLogger);
        classUnderTest.gameBuilder = new GameBuilder();
        TestConsole testConsole = new TestConsole();
        testConsole.setChoice(Choice.ROCK);
        testConsole.setNumberOfGames(1);
        ConnectorProvider.getInstance().setExternalResourceConnector(testConsole);

        // test
        int result = classUnderTest.run();

        // assert
        Assertions.assertEquals(0, result);
        List<String> logs = testLogger.getLogMessages().get(Logger.Level.INFO);
        ExternalResourceConnector connector = ConnectorProvider.getInstance().getConnector();
        Optional<String> summaryLogMsg = logs.stream()
                .filter(e -> e.contains(connector.getNumberOfGames() + "] times!")).findAny();
        assertTrue(summaryLogMsg.isPresent());
    }

}