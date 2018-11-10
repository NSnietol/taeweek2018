package dataProviders;

import org.aeonbits.owner.Config;

@Config.Sources("file:configs/AutomateConfig.properties")
public interface  AutomateConfig extends Config {

    CustomTypeConfig environment();

    CustomTypeConfig browser();

    Boolean windowMaximize();

    int implicitlyWait();

    int explicitlytWait();

    String log4jPath();

    String urlFacebook();

    String urlBlogSpot();
}
