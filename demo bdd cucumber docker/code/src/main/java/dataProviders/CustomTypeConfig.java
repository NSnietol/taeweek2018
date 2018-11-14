package dataProviders;

import enums.DriverType;
import enums.EnvironmentType;

public class CustomTypeConfig {

    private final String text;

    public CustomTypeConfig(String text) {
        this.text = text;
    }
    public EnvironmentType getEnvironmentType() {
        if(text == null || text.equalsIgnoreCase("local")) {
            return EnvironmentType.LOCAL;
        }
        return EnvironmentType.REMOTE;
    }

    public String getParam(){
        return text;
    }

    public DriverType getDriverType() {
        String browserName = text;
        if(browserName == null || browserName.equals("chrome")) {
            return DriverType.CHROME;
        }
        return DriverType.FIREFOX;
    }
}
