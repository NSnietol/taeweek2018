package managers;

import dataProviders.AutomateConfig;
import org.aeonbits.owner.ConfigFactory;

public class FileReaderManager {

    private static FileReaderManager fileReaderManager = new FileReaderManager();
    private static AutomateConfig automateConfig;

    private FileReaderManager() {
    }

    public static FileReaderManager getInstance( ) {
        return fileReaderManager;
    }

    public AutomateConfig getConfig() {
        if(automateConfig == null) {
            automateConfig = ConfigFactory.create(AutomateConfig.class);
        }
        return automateConfig;
    }
}
