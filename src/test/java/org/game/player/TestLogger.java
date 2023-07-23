package org.game.player;

import org.jboss.logging.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestLogger extends Logger {

    private Map<Level, List<String>> logMessages = new HashMap<>();
    /**
     * Construct a new instance.
     *
     * @param name the logger category name
     */
    protected TestLogger(String name) {
        super(name);
    }

    public TestLogger(){
        super("TestLogger");
    }

    @Override
    protected void doLog(Level level, String loggerClassName, Object message, Object[] parameters, Throwable thrown) {
        List<String> strings = logMessages.get(level);
        if(strings == null) {
            strings = new ArrayList<>();
        }
        strings.add(String.valueOf(message));
        logMessages.put(level, strings);
    }

    @Override
    protected void doLogf(Level level, String loggerClassName, String format, Object[] parameters, Throwable thrown) {
        List<String> strings = logMessages.get(level);
        if(strings == null) {
            strings = new ArrayList<>();
        }
        strings.add(String.valueOf(format));
        logMessages.put(level, strings);
    }

    @Override
    public boolean isEnabled(Level level) {
        return true;
    }

    public Map<Level, List<String>> getLogMessages() {
        return logMessages;
    }
}
