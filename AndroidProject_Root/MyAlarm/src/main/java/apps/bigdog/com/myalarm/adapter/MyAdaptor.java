package apps.bigdog.com.myalarm.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import apps.bigdog.com.myalarm.app.LocalApplication;
import apps.bigdog.com.myalarm.beans.EmergencyContactor;
import apps.bigdog.com.myalarm.util.SystemUtils;

/**
 * Created by jw362j on 8/30/2016.
 */
public class MyAdaptor extends BaseAdapter {
    List<EmergencyContactor> contactors;
    public MyAdaptor() {
        contactors = (List<EmergencyContactor>) LocalApplication.getInstance().getVariableHolder().getContactors().values();
    }

    @Override
    public int getCount() {
        return contactors.size();
    }

    @Override
    public Object getItem(int i) {
        return contactors.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        return null;
    }
}
