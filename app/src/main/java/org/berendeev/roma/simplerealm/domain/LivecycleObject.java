package org.berendeev.roma.simplerealm.domain;

import java.util.ArrayList;
import java.util.List;

public class LivecycleObject {

    private List<LivecycleListener> listeners;

    public LivecycleObject() {
        listeners = new ArrayList<>();
    }

    public void addListener(LivecycleListener listener){
        listeners.add(listener);
    }

    public void active() {
        for (LivecycleListener listener : listeners) {
            listener.onActive();
        }
    }

    public void disable(){
        for (LivecycleListener listener : listeners) {
            listener.onDisable();
        }
    }

    public interface LivecycleListener{
        void onActive();

        void onDisable();
    }
}
