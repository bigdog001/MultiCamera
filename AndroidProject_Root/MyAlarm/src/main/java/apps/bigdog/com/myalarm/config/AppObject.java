package apps.bigdog.com.myalarm.config;

import apps.bigdog.com.myalarm.app.LocalApplication;

/**
 * Created by jw362j on 6/1/2016.
 */
public abstract class AppObject implements InterfaceGenerator.AppLifeCycle{
    public AppObject(){
        LocalApplication.addAppLifeCycle(this);
    }
}
