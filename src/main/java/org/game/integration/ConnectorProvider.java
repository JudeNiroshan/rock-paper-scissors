package org.game.integration;

import org.game.integration.impl.ConsoleConnector;

public class ConnectorProvider {
    private ExternalResourceConnector externalResourceConnector;
    private static ConnectorProvider instance;
    String configuredConnectorName;
    private ConnectorProvider() {}
    public static ConnectorProvider getInstance() {
        if (instance == null) {
            instance = new ConnectorProvider();
        }
        return instance;
    }
    public ExternalResourceConnector getConnector() {
        if (externalResourceConnector == null) {
            if (configuredConnectorName == null) {
                configuredConnectorName = System.getenv("CONNECTOR_NAME");
            }
            if (configuredConnectorName == null) {
                configuredConnectorName = "console";
            }

            if (configuredConnectorName.equals("console")) {
                externalResourceConnector = new ConsoleConnector();
            } else {
                throw new RuntimeException("Unknown connector: " + configuredConnectorName);
            }
        }
        return externalResourceConnector;
    }

    public void setConfiguredConnectorName(String configuredConnectorName) {
        this.configuredConnectorName = configuredConnectorName;
    }

    public void setExternalResourceConnector(ExternalResourceConnector externalResourceConnector) {
        this.externalResourceConnector = externalResourceConnector;
    }
}
