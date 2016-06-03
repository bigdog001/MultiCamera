package apps.bigdog.com.multicamera.config;

import android.content.Context;

import apps.bigdog.com.multicamera.beans.VariableHolder;

/**
 * Created by jw362j on 6/1/2016.
 */
public class InterfaceGenerator {

    public interface ApplicationGlobalHolder {
        public VariableHolder getVariableHolder();
    }

    public interface AppLifeCycle {
        public void OnStop();
    }

    public interface Initializer {
        public void init(Context context);
    }
    public interface timerAction{
        public boolean isAllowedToExecute();
        public void onTime(Context c,int flag,Object data);
    }

    public interface ICommunicatable{
        public int myIndex();
        public void setCommunicatable(boolean communicatable);
        public void DataIn(Object data);
        public Object DataOut();
    }
}
